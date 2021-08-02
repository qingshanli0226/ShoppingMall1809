package com.example.electricityproject.details;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.electricityproject.R;
import com.example.electricityproject.view.VideoView;
import com.example.framework.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 播放视频Activity用来继承基类
 */
public class PlayVideoActivity extends BaseActivity {

    private VideoView playVideo;
    private RelativeLayout re;
    private TextView tvStart;
    private TextView tvFull;
    private TextView tvCurrent;
    private SeekBar bar;
    private TextView tvDuration;

    @Override
    protected void initData() {
        /**
         * 设置播放源
         */
        playVideo.setData(getResources().getString(R.string.details_videoUrl));
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo.start();
            }
        });

        /**
         * 倒计时采用Timer计时器
         */
         Timer timer = new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         int max = playVideo.getMaxTime();
                         int least = playVideo.getCurrentTime();
                         String duration = playVideo.getDuration();
                         String current = playVideo.getCurrent();
                         bar.setProgress(least);
                         bar.setMax(max);
                         tvCurrent.setText(current+"");
                         tvDuration.setText(duration+"");
                     }
                 });
             }
         },0,100);

         bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 if (fromUser){
                     playVideo.pro(progress);
                 }
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });

         tvFull.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int width = getWindow().getDecorView().getWidth();
                 int height = getWindow().getDecorView().getHeight();
                 if (height > width){//竖屏切换成横屏
                     setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                     //修改相对布局高度
                     ViewGroup.LayoutParams layoutParams = re.getLayoutParams();
                     layoutParams.height=ViewGroup.LayoutParams.MATCH_PARENT;
                     re.setLayoutParams(layoutParams);
                 }else {
                     setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                     //修改相对布局高度
                     ViewGroup.LayoutParams layoutParams = re.getLayoutParams();
                     layoutParams.height=700;
                     re.setLayoutParams(layoutParams);
                 }
             }
         });

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        playVideo = (VideoView) findViewById(R.id.playVideo);
        re = (RelativeLayout) findViewById(R.id.re);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvFull = (TextView) findViewById(R.id.tv_full);
        tvCurrent = (TextView) findViewById(R.id.tv_current);
        bar = (SeekBar) findViewById(R.id.bar);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_video;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}