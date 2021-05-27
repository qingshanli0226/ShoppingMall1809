package com.example.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.framework.R;
import com.example.net.RetrofitCreate;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class ShopGlide {
    // 删除最早存储的图片，然后再存储新的图片。
    private LruCache<String, Bitmap> menCache;
    //在磁盘中存储图片的数据结构，它的逻辑和LruCache类似
    private com.jakewharton.disklrucache.DiskLruCache disCache;
    //磁盘存储图片时，diskLruCache使用的目录
    private File cacheFileDir;

    private static ShopGlide shopGlide;
    private Handler handler=new Handler();
    //创建一个缓存线程池，来负责从磁盘中读取或者写入Bitmap，还有网络下载图片
    private ExecutorService executorService= Executors.newCachedThreadPool();
    private boolean isInit;

    public static ShopGlide getInstance() {
        if (shopGlide==null){
            synchronized (ShopGlide.class){
                if (shopGlide==null){
                    shopGlide=new ShopGlide();
                }
            }
        }
        return shopGlide;
    }
    public GlideRequest with(Context context){
        return new GlideRequest(context);
    }
    public boolean isInit() {
        return isInit;
    }
    public void init(Context context){
        if (isInit){
            return;
        }
        menCache =new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()/8)){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        cacheFileDir=new File(context.getExternalCacheDir().getAbsolutePath()+"/shopGlideImg/");
        if (!cacheFileDir.exists()){
            cacheFileDir.mkdir();
        }
        try {
            disCache = DiskLruCache.open(cacheFileDir,1,1,1*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isInit=true;
    }
    //从内存缓存中读取Bitmap
    public Bitmap getFromMen(String url){
        synchronized (menCache){
            String key = generateCacheKey(url);
            return menCache.get(key);
        }
    }
    //向内存中存入Bitmap
    public void setBitmapToMen(String url,Bitmap bitmap){
        synchronized (menCache) {
            String key = generateCacheKey(url);
            menCache.put(key,bitmap);
        }
    }

    //向磁盘中存储Bitmap
    public void setBitmapToDisk(String url,Bitmap bitmap){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (disCache){
                    String key = generateCacheKey(url);
                    try {
                        DiskLruCache.Editor edit = disCache.edit(key);
                        OutputStream outputStream = edit.newOutputStream(0);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                        edit.commit();
                        disCache.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //从磁盘读取Bitmap
    public void getBitmapFromDisk(String url,IBitmapReceivedListener iBitmapReceivedListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (disCache){
                    String key = generateCacheKey(url);
                    try {
                        DiskLruCache.Snapshot snapshot = disCache.get(key);
                        if (snapshot==null){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    iBitmapReceivedListener.onBitmap(null,null);
                                }
                            });
                        }else {
                            InputStream inputStream = snapshot.getInputStream(0);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            setBitmapToMen(url,bitmap);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    iBitmapReceivedListener.onBitmap(url,bitmap);
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
    //从服务端拿图片
    public void getBitmapFromServer(String url, IBitmapReceivedListener iBitmapReceivedListener, ImageView imageView){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<ResponseBody> call = RetrofitCreate.getFiannceApiService().DownFile(url);
                Response<ResponseBody> response=null;
                try {
                    response = call.execute();
                    if (response.body()==null){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iBitmapReceivedListener.onBitmap(null,null);
                            }
                        });
                    }else {
                        InputStream inputStream = response.body().byteStream();
                        if (inputStream==null){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    iBitmapReceivedListener.onBitmap(null,null);
                                }
                            });
                        }else {
                            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                            Bitmap sampleBitmap = sampleBitmap(imageView, decodeStream);
                            decodeStream.recycle();
                            decodeStream=null;
                            setBitmapToMen(url,sampleBitmap);
                            setBitmapToDisk(url,sampleBitmap);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    iBitmapReceivedListener.onBitmap(url,sampleBitmap);
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
    public Bitmap sampleBitmap(ImageView imageView,Bitmap originBitmap){
        int originBitmapHeight = originBitmap.getHeight();
        int originBitmapWidth = originBitmap.getWidth();
        int imageViewMeasuredWidth = imageView.getMeasuredWidth();
        int imageViewMeasuredHeight = imageView.getMeasuredHeight();

        BitmapFactory.Options options = new BitmapFactory.Options();
        int samleSize = 1;
        while (originBitmapWidth/samleSize>=imageViewMeasuredWidth && originBitmapHeight/samleSize>=imageViewMeasuredHeight){
            samleSize++;
        }
        //进行二次采样
        options.inJustDecodeBounds = false;
        options.inSampleSize = samleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        originBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return bitmap;
    }
    //因为读取Bitmap是异步方法，所以需要定义一个接口，通过该接口返回Bitmap
    public interface IBitmapReceivedListener {
        void onBitmap(String url, Bitmap bitmap);
    }

    //通过图片的地址生成一个32位的Hash key作为内存缓存和本地缓存的key，这个是唯一的，地址不同，生成的key肯定不同，并且Hash key里面没有乱码
    public String generateCacheKey(String url) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    url.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
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
    public static class GlideRequest{
        private String picUrl;
        private ImageView imageView;
        private ShopGlide shopGlide;

        public GlideRequest(Context context) {
            shopGlide=ShopGlide.getInstance();
            shopGlide.init(context);
        }
        public GlideRequest load(String url){
            this.picUrl=url;
            return this;
        }
        public void init(final ImageView imageView){
            this.imageView=imageView;
            imageView.setTag(picUrl);
            imageView.setImageResource(R.mipmap.new_img_loading_2);
            String key = ShopGlide.getInstance().generateCacheKey(picUrl);
            Bitmap bitmap=null;
            bitmap=shopGlide.getFromMen(picUrl);
            if (bitmap!=null){
                imageView.setImageBitmap(bitmap);
                Log.i("zx", "init: 本地中命中 效率中间。。。。");
                return;
            }
            shopGlide.getBitmapFromDisk(picUrl, new IBitmapReceivedListener() {
                @Override
                public void onBitmap(String url, Bitmap bitmap) {
                    if (bitmap!=null){
                        Log.i("zx", "onBitmap: 本地中命中 效率中间。。。。");
                        if (imageView.getTag().equals(picUrl)){
                            imageView.setImageBitmap(bitmap);
                        }else {
                            Log.i("zx", "onBitmap: 因为没有绑定,所以不显示");
                        }
                        return;
                    }
                    shopGlide.getBitmapFromServer(picUrl, new IBitmapReceivedListener() {
                        @Override
                        public void onBitmap(String url, Bitmap bitmap) {
                            if (bitmap!=null){
                                Log.i("zx", "onBitmap: 服务端命中 效率最低。。。。");
                                if (imageView.getTag().equals(picUrl)){
                                    imageView.setImageBitmap(bitmap);
                                }else {
                                    Log.i("zx", "onBitmap: 因为没有绑定,所以不显示");
                                }
                            }
                        }
                    },imageView);
                }
            });

        }
    }
}
