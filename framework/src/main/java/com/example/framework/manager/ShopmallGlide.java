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

    public void getBitmapFromDisk(String url, IBitmapReceivedListener listener) {
                executorService.execute(new Runnable() {
            @Override
            public void run() {
                               synchronized (diskLruCache) {
                                        String key = generateCacheKey(url);
                                        try {
                                                DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                                                if (snapshot == null) {//濡傛灉涓虹┖璇佹槑纾佺洏閲屾病鏈夎¯ユ枃浠
                                                        mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                                                        listener.onBitmap(url, null);
                                                                    }
                            });
                                                    } else {
                                                        InputStream inputStream = snapshot.getInputStream(0);
                                                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);//浠庤緭鍏ユ祦閲岃¯诲嚭Bitmap
                                                        setBitmapToMem(url, bitmap);//鍏堝瓨鍒板唴瀛橀噷涓€浠
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
                                                                    }
                            });
                                                    } else {
                                                        Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
                                                        //鍥犱负鍘熺敓鍥剧墖鐨凚itmap姣旇緝鑰楄垂鍐呭瓨锛岀洿鎺ヨ®剧疆鍒癐mageView涓婏紝瀹规槗OOM闂®棰橈紝鎵€鏈夎繘琛屼簩娆￠噰鏍
                                                                Bitmap sBitmap = sampleBitmap(imageView,originalBitmap);
                                                        originalBitmap.recycle();//宸茬粡鏈変簡鏂扮殑閲囨牱鐨凚itmap锛屽師鐢熺殑鍗犵敤鍐呭瓨鐨凚itmap灏卞彲浠ラ噴鏀句簡
                                                       originalBitmap=null;
                                                        setBitmapToDisk(url,sBitmap);//瀛樺埌sd鍗
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
                //涓€娆￠噰鏍
                        BitmapFactory.Options options = new BitmapFactory.Options();
               /* options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile("");*/

                                //姹傚嚭閲囨牱鍥犲
                int samleSize = 1;
                while (originalWidth/samleSize > imageViewWidht && originalHeight/samleSize > imageViewHeight) {
                        samleSize = samleSize+1;
                    }

                       //杩涜¡屼簩娆￠噰鏍
                                options.inJustDecodeBounds = false;
                options.inSampleSize = samleSize;
                options.inPreferredConfig = Bitmap.Config.RGB_565;

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
               originalBitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);
                byte[] bytes = baos.toByteArray();
                Bitmap sampleBitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length, options);
                return sampleBitmap;
            }


            //鍥犱负璇诲彇Bitmap鏄¯寮傛­ユ柟娉曪紝鎵€浠ラ渶瑕佸畾涔変竴涓ª鎺ュ彛锛岄€氳繃璇ユ帴鍙ｈ繑鍥濨itmap
            public interface IBitmapReceivedListener {
        void onBitmap(String url, Bitmap bitmap);
    }



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

                public GlideRequest load(String url) {
                        this.picUrl = url;
                        return this;
                    }

                public void into(final ImageView imageView) {
                        this.imageView = imageView;
                        String key = ShopmallGlide.getInstance().generateCacheKey(picUrl);
                        //鍏堜粠鍐呭瓨涓­鑾峰彇Bitmap
                                Bitmap bitmap = null;
                        bitmap = shopmallGlide.getFromMen(picUrl);
                        if (bitmap!=null) {
                                imageView.setImageBitmap(bitmap);
                                Log.d("LQS", "鍐呭瓨涓­鍛戒腑 鏁堢巼鏈€楂樸€傘€傘€傘€");
                                return;
                            }

                                shopmallGlide.getBitmapFromDisk(picUrl, new IBitmapReceivedListener() {
                @Override
                public void onBitmap(String url,Bitmap bitmap) {
                                                if (bitmap!=null) {
                                                       Log.d("LQS", "鏈¬鍦颁腑鍛戒腑 鏁堢巼涓­闂淬€傘€傘€傘€");
                                                       imageView.setImageBitmap(bitmap);
                                                        return;
                                                    }

                                                        shopmallGlide.getBitmapFromServer(picUrl,  new IBitmapReceivedListener() {
                        @Override
                        public void onBitmap(String url, Bitmap bitmap) {
                                                                        if (bitmap!=null) {
                                                                                Log.d("LQS", "鏈嶅姟绔¯鍛戒腑 鏁堢巼鏈€浣庛€傘€傘€傘€");
                                                                               imageView.setImageBitmap(bitmap);
                                                                            }
                                                                    }
                   },imageView);
                                           }
            });
                   }
    }

}
