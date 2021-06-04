package com.example.shoppingmall1809.main.particulars.streaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.shoppingmall1809.R;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

@Route(path = "/main/StreamingActivity")
public class StreamingActivity extends BaseActivity {

    private TXLivePlayer mLivePlayer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_streaming;
    }
//
    @Override
    protected void initData() {
        String flvUrl = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8";
        mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_FLV); //推荐 FLV
        // 设置填充模式
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
// 设置画面渲染方向
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
//mPlayerView 即 step1 中添加的界面 view
        TXCloudVideoView mView = (TXCloudVideoView) findViewById(R.id.video_view);
//创建 player 对象
        mLivePlayer = new TXLivePlayer(this);
//关键 player 对象与界面 view
        mLivePlayer.setPlayerView(mView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true);
    }
}