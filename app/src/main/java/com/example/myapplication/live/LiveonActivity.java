package com.example.myapplication.live;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePusher;
import com.tencent.live2.impl.V2TXLivePusherImpl;
import com.tencent.rtmp.ui.TXCloudVideoView;

import static com.tencent.live2.V2TXLiveCode.V2TXLIVE_ERROR_INVALID_LICENSE;

public class LiveonActivity extends AppCompatActivity {

    private TXCloudVideoView pusherTxCloudView;
    private V2TXLivePusher mLivePusher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liveon);
        initView();
         mLivePusher = new V2TXLivePusherImpl(this, V2TXLiveDef.V2TXLiveMode.TXLiveMode_RTMP); //指定对应的直播协议为 RTMP

        //启动本地摄像头预览
        mLivePusher.setRenderView(pusherTxCloudView);
        mLivePusher.startCamera(true);
        //启动推流
        String rtmpURL = "rtmp://3891.liveplay.myqcloud.com/live/3891_user_66f076f6_096a"; //此处填写您的 rtmp 推流地址
        int ret = mLivePusher.startPush(rtmpURL.trim());

        if (ret == V2TXLIVE_ERROR_INVALID_LICENSE) {
            Log.i("TAG", "startRTMPPush: license 校验失败");
        }
    }

    private void initView() {
        pusherTxCloudView = findViewById(R.id.pusher_tx_cloud_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束推流
        mLivePusher.stopPush();
    }

}