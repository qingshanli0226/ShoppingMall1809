package com.example.electricityproject.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class VideoView extends SurfaceView implements SurfaceHolder.Callback {

    private MediaPlayer mediaPlayer;

    //播放地址
    public void setData(String path){
        if (mediaPlayer!=null){
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        }
    }
    
    //视频最大值
    public int getMaxTime(){
        if (mediaPlayer!=null){
            int maxTime = mediaPlayer.getDuration();
            return maxTime;
        }
        return 0;
    }

    //视频当前值
    public int getCurrentTime(){
        if (mediaPlayer!=null){
            int leastTime = mediaPlayer.getCurrentPosition();
            return leastTime;
        }
        return 0;
    }

    //播放
    public void start(){
        if (mediaPlayer!=null){
            //是否在播放
            if (mediaPlayer.isPlaying()){
                //播放就暂停
                mediaPlayer.pause();
            }else {
                //暂停就播放
                mediaPlayer.start();
            }
        }
    }

    //当前进度条进度
    public void pro(int pro){
        if (mediaPlayer!=null){
            mediaPlayer.seekTo(pro);
        }
    }

    //当前进度
   public String getCurrent(){
        if (mediaPlayer!=null){
            //播放当前进度
            int currentTime = mediaPlayer.getCurrentPosition();
            //分
            int minute=currentTime/1000/60;
            //秒
            int second=currentTime/1000%60;

            String current="";
            if (minute<10){
                current +="0"+minute+":";
            }else {
                current +=minute+"";
            }
            if (second<10){
                current +="0"+second+"";
            }else {
                current +=second+"";
            }
            return current;
        }
        return "00:00";
   }

   public String getDuration(){
       if (mediaPlayer!=null){
           //播放当前进度
           int durationTime = mediaPlayer.getDuration();
           //分
           int minute=durationTime/1000/60;
           //秒
           int second=durationTime/1000%60;

           String duration="";
           if (minute<10){
               duration +="0"+minute+":";
           }else {
               duration +=minute+"";
           }
           if (second<10){
               duration +="0"+second+"";
           }else {
               duration +=second+"";
           }
           return duration;
       }
       return "00:00";
   }

    public VideoView(Context context) {
        super(context);
        if (mediaPlayer==null){
            mediaPlayer=new MediaPlayer();
        }
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        if (mediaPlayer==null){
            mediaPlayer=new MediaPlayer();
        }
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //播放
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mediaPlayer!=null){
            mediaPlayer.setDisplay(holder);
        }
    }

    //更改
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    //销毁
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}
