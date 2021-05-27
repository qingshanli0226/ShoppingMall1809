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

//实现一个Glide加载框架，理解里面使用的技术
public class ShopmallGlide {
    //使用该数据结构在内存中缓存图片。该数据结构的特点：1，初始化时可以指定它占用内存最大值，当该数据结构存储的数据超过最大值时，该数据结构将会
    // 删除最早存储的图片，然后再存储新的图片。
    private LruCache<String, Bitmap> memCache;
    //在磁盘中存储图片的数据结构，它的逻辑和LruCache类似
    private DiskLruCache diskLruCache;
    private File cacheFileDir;//磁盘存储图片时，diskLruCache使用的目录

    private static ShopmallGlide instance;//使用单例
    //创建一个缓存线程池，来负责从磁盘中读取或者写入Bitmap，还有网络下载图片
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private Handler mainHandler = new Handler();

    private boolean isInited;

    public ShopmallGlide() {
    }
    //单例使用双判断，主要是防止使用锁带来的性能损耗
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
    //加载框架只可以初始化一次
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
        //在sd卡上应用程序空间里的shopmall目录里存储本地图片
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
    //从内存缓存中读取Bitmap
    public Bitmap getFromMen(String url){

        synchronized (memCache){
            String key = generateCacheKey(url);
            return memCache.get(key);
        }

    }
    //向内存中写入Bitmap
    public void setBitmapToMem(String url,Bitmap bitmap){

        synchronized (memCache){

            String key = generateCacheKey(url);
            memCache.put(key,bitmap);
        }

    }
    //向磁盘中存储Bitmap
    public void setBitmapToDisk(String url,Bitmap bitmap){
        //凡是IO操作都必须放到子线程中,使用线程池
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

//对下载的文件做好二次采样，避免占用太多内存而出现OOM问题
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
                            //因为原生图片的Bitmap比较耗费内存，直接设置到ImageView上，容易OOM问题，所有进行二次采样
                            Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
                            Bitmap sBitmap = sampleBitmap(imageView,originalBitmap);
                            originalBitmap.recycle();////已经有了新的采样的Bitmap，原生的占用内存的Bitmap就可以释放了
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
                //一次采样
                BitmapFactory.Options options = new BitmapFactory.Options();
                //求出采样因子
                int samleSize = 1;
                while (originalWidth/samleSize > imageViewWidht && originalHeight/samleSize > imageViewHeight) {
                        samleSize = samleSize+1;
                    }

                //进行二次采样
                options.inJustDecodeBounds = false;
                options.inSampleSize = samleSize;
                options.inPreferredConfig = Bitmap.Config.RGB_565;

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
               originalBitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);
                byte[] bytes = baos.toByteArray();
                Bitmap sampleBitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length, options);
                return sampleBitmap;
            }


    //因为读取Bitmap是异步方法，所以需要定义一个接口，通过该接口返回Bitmap
            public interface IBitmapReceivedListener {
                void onBitmap(String url, Bitmap bitmap);
             }


    //通过图片的地址生成一个32位的Hash key作为内存缓存和本地缓存的key，这个是唯一的，地址不同，生成的key肯定不同，并且Hash key里面没有乱码
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
                    //从内存中没有获取到Bitmap，下面从本地获取bitmap
               shopmallGlide.getBitmapFromDisk(picUrl, new IBitmapReceivedListener() {
                @Override
                public void onBitmap(String url,Bitmap bitmap) {
                    if (bitmap!=null) {

                        imageView.setImageBitmap(bitmap);
                        return;
                    }
                    //如果从本地没有获取到bitmap,只能从网络获取
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
