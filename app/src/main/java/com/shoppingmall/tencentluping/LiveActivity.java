package com.shoppingmall.tencentluping;

import android.view.View;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.shoppingmall.R;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;

@Route(path = "/Live/LiveActivity")
public class LiveActivity extends BaseActivity {


    private com.tencent.rtmp.ui.TXCloudVideoView videoView;
    private android.widget.Button btLa;
    private android.widget.Button btStop;
    private TXLivePlayer txLivePlayer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_live;
    }

    @Override
    public void initView() {

        videoView = findViewById(R.id.video_view);
        btLa = findViewById(R.id.bt_la);
        btStop = findViewById(R.id.bt_stop);

         txLivePlayer = new TXLivePlayer(this);

         txLivePlayer.setPlayerView(videoView);

         btLa.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String flvUrl="http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
                 txLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_HLS); //推荐 FLV
             }
         });
         btStop.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 txLivePlayer.stopPlay(true);
                 videoView.onDestroy();
             }
         });
        txLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
// 设置画面渲染方向：横屏
        txLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}