package com.example.shoppingmallsix.goods;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.shoppingmallsix.R;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.umeng.message.PushAgent;

public class VideoActivity extends AppCompatActivity {

    private TXCloudVideoView mView;
    private TXLivePlayer mLivePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //mPlayerView 即 step1 中添加的界面 view
        mView = (TXCloudVideoView) findViewById(R.id.video_view);
//创建 player 对象
        mLivePlayer = new TXLivePlayer(VideoActivity.this);
//关键 player 对象与界面 view
        mLivePlayer.setPlayerView(mView);

        String flvUrl = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8";
        mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_FLV); //推荐 FLV



        // 设置填充模式
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
// 设置画面渲染方向
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);

        PushAgent.getInstance(this).onAppStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        mView.onDestroy();

    }
}
