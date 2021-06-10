package com.example.myapplication.live;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.tencent.live2.V2TXLivePlayer;
import com.tencent.live2.impl.V2TXLivePlayerImpl;
import com.tencent.rtmp.ui.TXCloudVideoView;

import static com.tencent.live2.V2TXLiveDef.V2TXLiveFillMode.V2TXLiveFillModeFit;
import static com.tencent.live2.V2TXLiveDef.V2TXLiveRotation.V2TXLiveRotation0;

public class LiveunActivity extends AppCompatActivity {

    private TXCloudVideoView mView;
    private V2TXLivePlayer mLivePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liveun);
        //mPlayerView 即 step1 中添加的界面 view
         mView = (TXCloudVideoView) findViewById(R.id.video_viewa);
//创建 player 对象
        mLivePlayer = new V2TXLivePlayerImpl(this);
//关键 player 对象与界面 view
        mLivePlayer.setRenderView(mView);
        String flvUrl = "http://3891.liveplay.myqcloud.com/live/3891_user_792abdac_f8b1.flv";
        mLivePlayer.startPlay(flvUrl);
        mLivePlayer.setRenderFillMode(V2TXLiveFillModeFit);
// 设置画面渲染方向
        mLivePlayer.setRenderRotation(V2TXLiveRotation0);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay();


    }

    private void initView() {

    }
}