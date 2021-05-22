package com.example.framework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.Toast;

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

import com.example.net.RetrofitCreator;
import com.jakewharton.disklrucache.DiskLruCache;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ShopmallGlide {
    // 内存存储集合  LruCache 数据结构的特点 1.初始化时可以可以指定最大值，数据超过最大值时会删除最早的图片，继续存储新的图片
    private LruCache<String, Bitmap> memCache;
    //在磁盘中存储图片的数据结构，他的逻辑和LruCache类似
    private DiskLruCache diskLruCache;
    //磁盘储存时 diskLruCache 使用的目录
    private File cacheFileDir;
    //判断是否初始化
    private boolean isInited = false;
    //短期的线程池 负责写入Bitmap ，下载网络图片
    private ExecutorService executorService = Executors.newCachedThreadPool();
    //转换线程
    private Handler mainhandler = new Handler();

    //初始化
    public void init(Context context) {
        //只能初始化一次
        if (isInited) {
            return;
        }
        memCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        //在SD卡上的应用程序上存储本地的图片
        cacheFileDir = new File(context.getExternalCacheDir() + "/shopmall/");
        if (cacheFileDir.exists()) {//目录不存在
            cacheFileDir.mkdir();//创建目录
        }

        try {
            diskLruCache = DiskLruCache.open(cacheFileDir, 1, 1, 1024 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isInited = true;
    }

    //从内存缓存中读取Bitmap
    public Bitmap getFromMem(String url) {
        synchronized (memCache) {
            String key = generateCacheKey(url);//通过MD5 hash算法生成一个key
            return memCache.get(key);
        }
    }

    //向内存缓存中写入Bitmap
    public void setBitmapToMem(String url, Bitmap bitmap) {
        synchronized (memCache) {
            String key = generateCacheKey(url);//通过MD5 hash算法生成一个key
            memCache.put(key, bitmap);
        }
    }

    //向磁盘中写入Bitmap
    public void setBitmapToDisk(String url, Bitmap bitmap) {
        //io流放到子线程中,使用线程池
        executorService.execute(() -> {
            synchronized (diskLruCache) {
                String key = generateCacheKey(url);//通过MD5 hash算法生成一个key
                try {
                    DiskLruCache.Editor edit = diskLruCache.edit(key);
                    OutputStream outputStream = edit.newOutputStream(0);//文件输出流，可以吧Bitmap输出到一个文件里
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);//Bitmap文件格式JPG格式，并且不压缩质量
                    edit.commit();
                    diskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //从磁盘中读取Bitmap
    public void getBitmapFromDisk(String url, IBitmapReceivedListener listener) {
        //io流放到子线程中,使用线程池
        executorService.execute(() -> {
            synchronized (diskLruCache) {
                String key = generateCacheKey(url);//通过MD5 hash算法生成一个key
                try {
                    DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                    if (snapshot == null) {//如果磁盘里面没有该文件
                        mainhandler.post(() -> {
                            listener.onBitmap(url, null);
                        });
                    } else {
                        InputStream inputStream = snapshot.getInputStream(0);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        setBitmapToMem(url, bitmap);
                        mainhandler.post(() -> {
                            listener.onBitmap(url, bitmap);
                        });
                    }

                    diskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //从网络读取一个数据

    /**
     * @param url
     * @param listener
     * @param imageView 对下载的文件做二次采样，避免占用太多的内存而OM异常
     */
    public void getBitmapFromServer(String url, IBitmapReceivedListener listener, ImageView imageView) {
        executorService.execute(() -> {
            String key = generateCacheKey(url);
            Call call = RetrofitCreator.getShopApiService().downloadFile(url);
            try {
                Response<ResponseBody> response = call.execute();
                if (response.body() == null) {
                    mainhandler.post(() -> {
                        listener.onBitmap(url, null);
                    });
                } else {
                    InputStream inputStream = response.body().byteStream();
                    if (inputStream == null) {
                        mainhandler.post(() -> {
                            listener.onBitmap(url, null);
                        });
                    } else {
                        Bitmap originalbitmap = BitmapFactory.decodeStream(inputStream);
                        //因为原生图片的Bitmap比较耗费内存，直接设置到ImageView上，容易OM异常，所以二次采样
                        Bitmap bitmap = sampleBitmap(imageView, originalbitmap);
                        originalbitmap.recycle();//已经有采样后的图片，前面的可以销毁了
                        originalbitmap=null;
                        setBitmapToMem(url,bitmap);
                        setBitmapToDisk(url,bitmap);
                        mainhandler.post(() -> {
                            listener.onBitmap(url, bitmap);
                        });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    //二次采样
    public Bitmap sampleBitmap(ImageView imageView, Bitmap originalbitmap) {
        int originalWidth = originalbitmap.getWidth();
        int originalHeight = originalbitmap.getHeight();
        //控件的宽高
        int imageViewWidth = imageView.getMeasuredWidth();
        int imageViewHeight = imageView.getMeasuredHeight();
        //采样因子
        int samleSize = 1;
        while (originalWidth / samleSize > imageViewWidth && originalHeight / samleSize > imageViewHeight) {
            samleSize += samleSize;
        }
        //二次采样
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = samleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        originalbitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap sampleBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

        return sampleBitmap;
    }

    //读取Bitmap是异步方法，所以需要定义一个接口，通过接口返回Bitmap
    public interface IBitmapReceivedListener {
        void onBitmap(String key, Bitmap bitmap);
    }

    //通过图片的地址生成一个32位的Hash key作为内存缓存和本地缓存的key，这个是唯一的，地址不同，生成的key肯定不同，并且Hash key里面没有乱码
    public String generateCacheKey(String url) {
        byte[] hash = new byte[0];
        try {
            hash = MessageDigest.getInstance("MD5").digest(url.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuffer hex = new StringBuffer(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    //使用单例单例使用双判断 防止性能损耗
    private static ShopmallGlide instance;
    private ShopmallGlide(){
    }

    public static ShopmallGlide getInstance() {
        if (instance == null) {
            synchronized (ShopmallGlide.class) {
                if (instance == null) {
                    instance = new ShopmallGlide();
                }
            }
        }
        return instance;
    }


    public static GlideRequest with(Context context){
        return  new GlideRequest(context);
    }

    public static  class GlideRequest{
        private String picUrl;
        private ImageView imageView;
        private ShopmallGlide shopmallGlide;

        public GlideRequest(Context context){
            shopmallGlide = ShopmallGlide.getInstance();
            shopmallGlide.init(context);
        }

        public GlideRequest load(String url){
            this.picUrl = url;
            return this;
        }

        public void into(final ImageView imageView){
            this.imageView =imageView;
            imageView.setTag(picUrl);
            imageView.setImageResource(android.R.mipmap.sym_def_app_icon);
            String key = ShopmallGlide.getInstance().generateCacheKey(picUrl);
            Bitmap bitmap = shopmallGlide.getFromMem(key);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                Log.d("LQS", "内存中命中 效率最高。。。。");
                return;
            }
            shopmallGlide.getBitmapFromDisk(picUrl, new IBitmapReceivedListener() {
                @Override
                public void onBitmap(String key, Bitmap bitmap) {
                    if (bitmap != null) {
                        if (imageView.getTag()!=key){
                            Log.d("LQS", "被拦截");
                            return;
                        }
                        imageView.setImageBitmap(bitmap);
                        Log.d("LQS", "本地中命中 效率中间。。。。");
                        return;
                    }
                    shopmallGlide.getBitmapFromServer(picUrl, new IBitmapReceivedListener() {
                        @Override
                        public void onBitmap(String key, Bitmap bitmap) {
                            if (imageView.getTag()!=key){
                                Log.d("LQS", "被拦截");
                                return;
                            }
                            imageView.setImageBitmap(bitmap);
                            Log.d("LQS", "服务端命中 效率最低。。。。");
                        }
                    },imageView );
                }
            });
        }



    }

}
