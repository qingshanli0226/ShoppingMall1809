package com.example.framework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.framework.R;
import com.example.net.RetrofitManager;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ShopmallGlide {
    //内存
    private LruCache<String, Bitmap> memCache;
    //本地
    private DiskLruCache diskLruCache;
    //存储的目录
    private File cacheFileDir;
    //缓存线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();
    //切换线程
    private Handler handler = new Handler();

    private static ShopmallGlide shopmallGlide;
    private boolean isInstance = false;

    private ShopmallGlide() {

    }

    public GlideRequest with(Context context){
        return new GlideRequest(context);

    }

    public static ShopmallGlide getInstance() {
        if (shopmallGlide == null) {
            synchronized (ShopmallGlide.class) {
                if (shopmallGlide == null) {
                    shopmallGlide = new ShopmallGlide();
                }
            }
        }

        return shopmallGlide;
    }

    public void init(Context context) {
        if (isInstance) {
            return;
        }
        memCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        cacheFileDir = new File(context.getExternalCacheDir().getAbsolutePath() + "/shopmall/");
        if (!cacheFileDir.exists()) {
            cacheFileDir.mkdirs();
        }
        try {
            diskLruCache = DiskLruCache.open(cacheFileDir, 1, 1, 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isInstance = true;
    }

    //md5
    public String generateCacheKey(String url) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(url.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();

    }


    //从内存获取数据  存放必须保证数据安全

    public Bitmap getFromMem(String url) {
        synchronized (memCache) {
            String key = generateCacheKey(url);
            return memCache.get(key);
        }
    }

    //放入内存中
    public void setBitmapToMem(String url, Bitmap bitmap) {
        synchronized (memCache) {
            String key = generateCacheKey(url);
            memCache.put(key,bitmap);
        }
    }

    //存入到磁盘
    public void setBitmapToDisk(String url, Bitmap bitmap){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (diskLruCache) {
                    String key = generateCacheKey(url);
                    try {
                        DiskLruCache.Editor edit = diskLruCache.edit(key);
                        OutputStream outputStream = edit.newOutputStream(0);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        edit.commit();
                        diskLruCache.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //获得磁盘数据
    public void getBitmapFromDisk(String url,IBitmapReceivedListener listener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (diskLruCache) {
                    String key = generateCacheKey(url);
                    try {
                        DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                        if (snapshot == null) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onBitmap(url, null);
                                }
                            });
                        } else {
                            InputStream inputStream = snapshot.getInputStream(0);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            setBitmapToMem(url, bitmap);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onBitmap(url, bitmap);
                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    //二次采样
    public Bitmap smapleBitmap(ImageView imageView,Bitmap originalBitmap){
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();
        int measuredWidth = imageView.getMeasuredWidth();
        int measuredHeight = imageView.getMeasuredHeight();
        int smaleSize = 1;
        while(originalWidth / smaleSize > measuredWidth && originalHeight/smaleSize >measuredHeight){
            smaleSize += 1;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = smaleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        originalBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return bitmap;

    }


    //从服务端获取
    public void getBitmapFromServer(String url, ImageView imageView, IBitmapReceivedListener listener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<ResponseBody> call = RetrofitManager.getHttpApiService().downloadFile(url);
                Response<ResponseBody> response = null;
                try {
                    response = call.execute();
                    if(response.body() == null){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onBitmap(url,null);
                            }
                        });
                    } else{
                        InputStream inputStream = response.body().byteStream();
                        if (inputStream == null) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onBitmap(url,null);
                                }
                            });
                        } else{
                            Bitmap original = BitmapFactory.decodeStream(inputStream);
                            //进行二次采用
                            Bitmap bitmap = smapleBitmap(imageView, original);
                            original.recycle();
                            original = null;
                            setBitmapToDisk(url,bitmap);
                            setBitmapToMem(url,bitmap);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onBitmap(url,bitmap);

                                }
                            });

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public interface  IBitmapReceivedListener{
        void onBitmap(String url,Bitmap bitmap);
    }

    public static class GlideRequest{
        private String pathUrl;
        private ImageView imageView;
        private ShopmallGlide glide;

        public GlideRequest(Context context) {
            glide = ShopmallGlide.getInstance();
            glide.init(context);
        }

        public GlideRequest load(String url){
            this.pathUrl = url;
            return this;
        }

        public void into(final ImageView imageView){
            this.imageView = imageView;
            imageView.setTag(pathUrl);
            imageView.setImageResource(R.drawable.wc_ac_01);

            Bitmap bitmap = null;
            bitmap = glide.getFromMem(pathUrl);
            if(bitmap != null){
                Log.i("ZYB", "into: 内存");
                imageView.setImageBitmap(bitmap);
                return;
            }

            glide.getBitmapFromDisk(pathUrl, new IBitmapReceivedListener() {
                @Override
                public void onBitmap(String url, Bitmap bitmap) {
                    if (bitmap != null) {
                        Log.i("ZYB", "into: 硬盘");
                        if(imageView.getTag().equals(pathUrl)){
                            imageView.setImageBitmap(bitmap);

                        }

                        return;
                    }
                    glide.getBitmapFromServer(pathUrl, imageView, new IBitmapReceivedListener() {
                        @Override
                        public void onBitmap(String url, Bitmap bitmap) {
                            if (bitmap != null) {
                                Log.i("ZYB", "into: 服务");
                                if(imageView.getTag().equals(pathUrl)) {
                                    imageView.setImageBitmap(bitmap);

                                }
                                return;
                            }
                        }
                    });
                }
            });
        }
    }
}
