package com.example.electricityproject.details;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.electricityproject.R;
import com.example.electricityproject.view.VideoView;
import com.example.framework.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

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
        playVideo.setData("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4");
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo.start();
            }
        });
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
}