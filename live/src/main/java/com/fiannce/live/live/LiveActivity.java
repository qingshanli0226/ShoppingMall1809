package com.fiannce.live.live;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.fiannce.live.R;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class LiveActivity extends BaseActivity {


    private TXCloudVideoView videoView;
    private Button pushLive;

    @Override
    public int getLayoutId() {
        return R.layout.activity_live;
    }

    @Override
    public void initView() {

        videoView = (TXCloudVideoView) findViewById(R.id.video_view);
        pushLive = (Button) findViewById(R.id.pushLive);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
//创建 player 对象
        TXLivePlayer mLivePlayer = new TXLivePlayer(this);
//关键 player 对象与界面 view
        mLivePlayer.setPlayerView(videoView);


        String flvUrl = "http://ivi.bupt.edu.cn/hls/cctv9hd.m3u8";
        mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_FLV); //推荐 FLV
        // 设置填充模式
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
        // 设置画面渲染方向
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);

        pushLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_PUSH_LIVE).navigation();
            }
        });
    }
}
