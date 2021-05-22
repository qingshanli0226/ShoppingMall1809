package com.shoppingmall.framework.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.util.LruCache;
import com.jakewharton.disklrucache.DiskLruCache;
import com.shoppingmall.net.model.RetrofitCreate;

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

public class ShopMallGlide {
    private LruCache<String, Bitmap> memCache;
    private DiskLruCache diskLruCache;
    //目录
    private File cacheFileDir;

    private static ShopMallGlide instance;//使用单例

    //创建一个缓存线程池，读写Bitmap，还有网络下载图片
    private ExecutorService executorService = Executors.newCachedThreadPool();
    //通过handler实现在主线程里返回数据
    private Handler mainHandler = new Handler();

    private boolean isInited;
    private ShopMallGlide(){
    }

    //单例使用双判断，主要是防止使用锁带来的性能损耗
    public static ShopMallGlide getInstance() {
        if (instance==null) {
            synchronized (ShopMallGlide.class) {
                if (instance == null) {
                    instance = new ShopMallGlide();
                }
            }
        }
        return instance;
    }
    //加载框架只可以初始化一次
    public boolean isInited() {
        return isInited;
    }

    public void init(Context context) {
        if (isInited) {
            return;
        }
        memCache = new LruCache<String,Bitmap>(((int)Runtime.getRuntime().maxMemory())/8) {
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        //在sd卡上存储本地图片
        cacheFileDir = new File(context.getExternalCacheDir().getAbsolutePath()+"/shopmall/");
        if (!cacheFileDir.exists()) {
            //如果该目录不存在，则创建它
            cacheFileDir.mkdir();
        }
        try {
            diskLruCache = DiskLruCache.open(cacheFileDir,1,1,1024*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isInited = true;
    }

    //从内存缓存中读取Bitmap   通过MD5 Hash算法生成一个key
    public Bitmap getFromMem(String url) {
        synchronized (memCache) {
            String key = generateCacheKey(url);
            return memCache.get(key);
        }
    }
    //向内存中写入Bitmap
    public void setBitmapToMem(String url,Bitmap bitmap) {
        synchronized (memCache) {
            String key = generateCacheKey(url);
            memCache.put(key,bitmap);
        }
    }

    //向磁盘中存储Bitmap
    public void setBitmapToDisk(String url, Bitmap bitmap) {
        //使用线程池
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (diskLruCache) {
                    String key = generateCacheKey(url);
                    try {
                        DiskLruCache.Editor editor = diskLruCache.edit(key);
                        OutputStream outputStream = editor.newOutputStream(0);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        editor.commit();
                        diskLruCache.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //从磁盘中读取Bitmap
    public void getBitmapFromDisk(String url, IBitmapReceivedListener listener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (diskLruCache) {
                    String key = generateCacheKey(url);
                    try {
                        DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                        if (snapshot == null) {
                            //如果为空证明磁盘里没有该文件
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onBitmap(url, null);
                                }
                            });
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

    //二次采样
    public void getBitmapFromServer(String url, IBitmapReceivedListener listener, ImageView imageView) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call call = RetrofitCreate.getShoppingMallApiService().downloadFile(url);
                try {
                    Response<ResponseBody> response = call.execute();
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
                                }
                            });
                        } else {
                            Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
                            Bitmap sBitmap = sampleBitmap(imageView,originalBitmap);
                            originalBitmap.recycle();
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

    public interface IBitmapReceivedListener {
        void onBitmap(String url, Bitmap bitmap);
    }
    //通过图片的地址生成一个32位的key
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

    public static GlideRequest with(Context context) {
        return new GlideRequest(context);
    }

    public static class GlideRequest {
        private String picUrl;
        private ImageView imageView;
        private ShopMallGlide shopmallGlide;

        public GlideRequest(Context context) {
            shopmallGlide = ShopMallGlide.getInstance();
            shopmallGlide.init(context);
        }

        public GlideRequest load(String url) {
            this.picUrl = url;
            return this;
        }

        public void into(final ImageView imageView) {
            this.imageView = imageView;
            String key = ShopMallGlide.getInstance().generateCacheKey(picUrl);
            //先从内存中获取Bitmap
            Bitmap bitmap = null;
            bitmap = shopmallGlide.getFromMem(picUrl);
            if (bitmap!=null) {
                imageView.setImageBitmap(bitmap);
                Log.d("hqy", "内存获取");
                return;
            }
            //从内存中没有获取到Bitmap，下面从本地获取bitmap
            shopmallGlide.getBitmapFromDisk(picUrl, new ShopMallGlide.IBitmapReceivedListener() {
                @Override
                public void onBitmap(String url,Bitmap bitmap) {
                    if (bitmap!=null) {
                        Log.d("hqy", "本地获取");
                        imageView.setImageBitmap(bitmap);
                        return;
                    }
                    //如果从本地没有获取到bitmap,只能从网络获取
                    shopmallGlide.getBitmapFromServer(picUrl,  new ShopMallGlide.IBitmapReceivedListener() {
                        @Override
                        public void onBitmap(String url, Bitmap bitmap) {
                            if (bitmap!=null) {
                                Log.d("hqy", "服务端获取");
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                    },imageView);
                }
            });
        }
        }
    }

