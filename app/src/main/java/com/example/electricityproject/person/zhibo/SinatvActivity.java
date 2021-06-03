package com.example.electricityproject.person.zhibo;

import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.view.ToolBar;

public class SinatvActivity extends BaseActivity {


    private com.example.view.ToolBar toolbar;
//    private com.tencent.rtmp.ui.TXCloudVideoView videoView;

    @Override
    protected void initData() {

//        TXLivePlayer mLivePlayer = new TXLivePlayer(this);
//关键 player 对象与界面 view
//        mLivePlayer.setPlayerView(videoView);


        String flvUrl = "http://ivi.bupt.edu.cn/hls/cctv9hd.m3u8";
//        mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_FLV); //推荐 FLV

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
//        videoView = (TXCloudVideoView) findViewById(R.id.video_view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sinatv;
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



//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
//        mView.onDestroy();
//    }
}