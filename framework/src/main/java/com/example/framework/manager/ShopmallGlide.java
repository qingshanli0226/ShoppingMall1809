package com.example.framework.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.net.RetrofitCreator;
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


public class ShopmallGlide {

    private LruCache<String, Bitmap> memCache;
    private DiskLruCache diskLruCache;
    private File cacheFileDir;

    private static ShopmallGlide instance;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private Handler mainHandler = new Handler();

    private boolean isInited;

    public ShopmallGlide() {
    }

    public static ShopmallGlide getInstance() {

        if (instance == null){
            synchronized (ShopmallGlide.class){
                if (instance == null){
                    instance = new ShopmallGlide();
                }
            }
        }

        return instance;
    }

    public  boolean isInited(){
        return isInited;
    }

    public void init(Context context){
        if (isInited){
            return;
        }

        memCache = new LruCache<String, Bitmap>((int) Runtime.getRuntime().maxMemory()/8){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        cacheFileDir = new File(context.getExternalCacheDir().getAbsolutePath()+"/shopmal");

        if (!cacheFileDir.exists()){

            cacheFileDir.mkdir();

        }

        try {
            diskLruCache = DiskLruCache.open(cacheFileDir,1,1,1024*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isInited=true;
    }

    public Bitmap getFromMen(String url){

        synchronized (memCache){
            String key = generateCacheKey(url);
            return memCache.get(key);
        }

    }

    public void setBitmapToMem(String url,Bitmap bitmap){

        synchronized (memCache){

            String key = generateCacheKey(url);
            memCache.put(key,bitmap);
        }

    }

    public void setBitmapToDisk(String url,Bitmap bitmap){

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                synchronized (diskLruCache){

                    try {
                        DiskLruCache.Editor edit = diskLruCache.edit(url);

                        OutputStream outputStream = edit.newOutputStream(0);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                        edit.commit();
                        diskLruCache.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }
    //从磁盘读
    public void getBitmapFromDisk(String url, IBitmapReceivedListener listener) {
                executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (diskLruCache) {
                    String key = generateCacheKey(url);
                    try {
                        DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                        if (snapshot == null) {
                            mainHandler.post(new Runnable() {
                        @Override
                          public void run() {
                            listener.onBitmap(url, null);
                        }});
                        } else {
                            InputStream inputStream = snapshot.getInputStream(0);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            setBitmapToMem(url, bitmap);
                            mainHandler.post(new Runnable() {
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


    public void getBitmapFromServer(String url, IBitmapReceivedListener listener, ImageView imageView) {
                executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<ResponseBody> responseBodyCall = RetrofitCreator.getFiannceApiService().downloadFile(url);
                try {
                    Response<ResponseBody> response = responseBodyCall.execute();
                    if (response.body() == null) {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onBitmap(url,null);
                            }
                        });
                    } else {
                        InputStream inputStream = response.body().byteStream();
                        if (inputStream==null) {
                            mainHandler.post(new Runnable() {
                        @Override
                         public void run() {
                            listener.onBitmap(url,null);
                        }});
                        } else {
                            Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
                            Bitmap sBitmap = sampleBitmap(imageView,originalBitmap);
                            originalBitmap.recycle();//
                            originalBitmap=null;
                            setBitmapToDisk(url,sBitmap);
                            setBitmapToMem(url,sBitmap);
                            mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onBitmap(url,sBitmap);
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

            public Bitmap sampleBitmap(ImageView imageView, Bitmap originalBitmap) {
                int originalWidth = originalBitmap.getWidth();
                int originalHeight = originalBitmap.getHeight();
                int imageViewWidht = imageView.getMeasuredWidth();
                int imageViewHeight = imageView.getMeasuredHeight();

                BitmapFactory.Options options = new BitmapFactory.Options();

                int samleSize = 1;
                while (originalWidth/samleSize > imageViewWidht && originalHeight/samleSize > imageViewHeight) {
                        samleSize = samleSize+1;
                    }


                options.inJustDecodeBounds = false;
                options.inSampleSize = samleSize;
                options.inPreferredConfig = Bitmap.Config.RGB_565;

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
               originalBitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);
                byte[] bytes = baos.toByteArray();
                Bitmap sampleBitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length, options);
                return sampleBitmap;
            }



            public interface IBitmapReceivedListener {
                void onBitmap(String url, Bitmap bitmap);
             }



            public String generateCacheKey(String url) {
                byte[] hash;

                try {
                    hash = MessageDigest.getInstance("MD5").digest(url.getBytes("UTF-8"));
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

            public static GlideRequest with(Context context) {
                return new GlideRequest(context);
            }
            public static class GlideRequest {
        private String picUrl;
        private ImageView imageView;
        private ShopmallGlide shopmallGlide;

                public GlideRequest(Context context) {
                        shopmallGlide = ShopmallGlide.getInstance();
                        shopmallGlide.init(context);
                    }

                public GlideRequest load(String url) {
                        this.picUrl = url;
                        return this;
                    }

                public void into(final ImageView imageView) {
                        this.imageView = imageView;
                        String key = ShopmallGlide.getInstance().generateCacheKey(picUrl);

                                Bitmap bitmap = null;
                        bitmap = shopmallGlide.getFromMen(picUrl);
                        if (bitmap!=null) {
                                imageView.setImageBitmap(bitmap);

                                return;
                            }
               shopmallGlide.getBitmapFromDisk(picUrl, new IBitmapReceivedListener() {
                @Override
                public void onBitmap(String url,Bitmap bitmap) {
                    if (bitmap!=null) {

                        imageView.setImageBitmap(bitmap);
                        return;
                    }

                    shopmallGlide.getBitmapFromServer(picUrl,  new IBitmapReceivedListener() {
                        @Override
                        public void onBitmap(String url, Bitmap bitmap) {
                            if (bitmap!=null) {

                                imageView.setImageBitmap(bitmap);
                            }
                        }
                   },imageView);
                }
            });
                }
    }

}
