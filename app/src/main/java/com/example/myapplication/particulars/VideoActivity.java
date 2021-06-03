package com.example.myapplication.particulars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.framework.BaseActivity;
import com.example.myapplication.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class VideoActivity extends BaseActivity {

    private com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer myVideo;

    @Override
    protected int bandLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        myVideo = (StandardGSYVideoPlayer) findViewById(R.id.myVideo);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        myVideo.setUp("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4",true,"");
    }
}