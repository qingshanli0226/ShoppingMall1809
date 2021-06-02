package com.example.framework.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.R;
import com.example.net.retrofit.RetrofitManager;
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

public class ShopmallGlide  {

    private LruCache<String,Bitmap> memCache;
    //private HashMap<String,Bitmap> memCache = new HashMap<>();
    private DiskLruCache diskLruCache;//在磁盘中存储图片的数据结构，它的逻辑和LruCache类似
    private File cacheFileDir;//磁盘存储图片时，diskLruCache使用的目录

    private static ShopmallGlide  instance;//使用单例

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private Handler mainHandler = new Handler();//通过handler实现在主线程里返回数据

    private boolean isInited;
    private ShopmallGlide (){
    }

    //单例使用双判断，主要是防止使用锁带来的性能损耗
    public static ShopmallGlide  getInstance() {
        if (instance==null) {
            synchronized (ShopmallGlide .class) {
                if (instance == null) {
                    instance = new ShopmallGlide();
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
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        //在sd卡上应用程序空间里的shopmall目录里存储本地图片
        cacheFileDir = new File(context.getExternalCacheDir().getAbsolutePath()+"/shopmall/");
        if (!cacheFileDir.exists()) {//如果该目录不存在，则创建它
            cacheFileDir.mkdir();
        }
        try {
            diskLruCache = DiskLruCache.open(cacheFileDir,1,1,1024*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isInited = true;
    }

    //从内存缓存中读取Bitmap
    public Bitmap getFromMem(String url) {
        synchronized (memCache) {
            String key = generateCacheKey(url);//通过MD5 Hash算法生成一个key
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
        executorService.execute(() -> {
            synchronized (diskLruCache) {
                String key = generateCacheKey(url);
                try {
                    DiskLruCache.Editor editor = diskLruCache.edit(key);
                    OutputStream outputStream = editor.newOutputStream(0);//文件输出流,通过该输出流可以把bitmap写到一个文件里
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);//bitmap产生的文件时JPEG的格式，且质量没有压缩
                    editor.commit();
                    diskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //从磁盘中读取Bitmap
    public void getBitmapFromDisk(String url, ShopmallGlide.IBitmapReceivedListener listener) {
        executorService.execute(() -> {
            synchronized (diskLruCache) {
                String key = generateCacheKey(url);
                try {
                    DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                    if (snapshot == null) {//如果为空证明磁盘里没有该文件
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onBitmap(url, null);
                            }
                        });
                    } else {
                        InputStream inputStream = snapshot.getInputStream(0);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);//从输入流里读出Bitmap
                        setBitmapToMem(url, bitmap);//先存到内存里一份
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
        });
    }

    public void getBitmapFromServer(String url, ShopmallGlide.IBitmapReceivedListener listener, ImageView imageView) {
        executorService.execute(() -> {
            Call call = RetrofitManager.getApi().downloadFile(url);
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
                        //因为原生图片的Bitmap比较耗费内存，直接设置到ImageView上，容易OOM问题，所有进行二次采样
                        Bitmap sBitmap = sampleBitmap(imageView,originalBitmap);
                        originalBitmap.recycle();//已经有了新的采样的Bitmap，原生的占用内存的Bitmap就可以释放了
                        originalBitmap=null;
                        setBitmapToDisk(url,sBitmap);//存到sd卡
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
        });
    }

    //对bitmap二次采样
    public Bitmap sampleBitmap(ImageView imageView, Bitmap originalBitmap) {
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();
        int imageViewWidht = imageView.getMeasuredWidth();
        int imageViewHeight = imageView.getMeasuredHeight();
        //一次采样
        BitmapFactory.Options options = new BitmapFactory.Options();
       /* options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile("");*/

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
        private ShopmallGlide shopmallGlide;

        public GlideRequest(Context context) {
            shopmallGlide = ShopmallGlide.getInstance();
            shopmallGlide.init(context);
        }

        public ShopmallGlide.GlideRequest load(String url) {
            this.picUrl = url;
            return this;
        }

        public void into(final ImageView imageView) {
            this.imageView = imageView;
            imageView.setTag(picUrl);//让imageView去和要下载的地址进行绑定.
            imageView.setImageResource(R.mipmap.ic_launcher);
            String key = ShopmallGlide.getInstance().generateCacheKey(picUrl);
            //先从内存中获取Bitmap
            Bitmap bitmap = null;
            bitmap = shopmallGlide.getFromMem(picUrl);
            if (bitmap!=null) {
                imageView.setImageBitmap(bitmap);
                LogUtils.d("内存中命中 效率最高");
                return;
            }
            //从内存中没有获取到Bitmap，下面从本地获取bitmap
            shopmallGlide.getBitmapFromDisk(picUrl, new ShopmallGlide.IBitmapReceivedListener() {
                @Override
                public void onBitmap(String url,Bitmap bitmap) {
                    if (bitmap!=null) {
                        LogUtils.d( "本地中命中 效率中间。。。。");
                        if (imageView.getTag().equals(picUrl)) {//只有和imageVIew绑定的图片才能显示出来
                            imageView.setImageBitmap(bitmap);
                        } else {
                            LogUtils.d( "因为没有绑定，不显示。。。。");
                        }
                        return;
                    }
                    //如果从本地没有获取到bitmap,只能从网络获取
                    shopmallGlide.getBitmapFromServer(picUrl,  new ShopmallGlide.IBitmapReceivedListener() {
                        @Override
                        public void onBitmap(String url, Bitmap bitmap) {
                            if (bitmap!=null) {
                                Log.d("LQS", "服务端命中 效率最低。。。。");
                                if (imageView.getTag().equals(picUrl)) {
                                    imageView.setImageBitmap(bitmap);
                                } else {
                                    Log.d("LQS", "因为没有绑定，不显示。。。。");
                                }
                            }
                        }
                    },imageView);
                }
            });
        }
    }

}

