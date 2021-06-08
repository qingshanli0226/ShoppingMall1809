package com.fiannce.live.pushlive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.common.LogUtil;
import com.example.framework.BaseActivity;
import com.fiannce.live.R;
import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePusher;
import com.tencent.live2.impl.V2TXLivePusherImpl;
import com.tencent.rtmp.ui.TXCloudVideoView;

import static com.tencent.live2.V2TXLiveCode.V2TXLIVE_ERROR_INVALID_LICENSE;

public class PushLiveActivity extends BaseActivity {

    private V2TXLivePusher mLivePusher;
    private TXCloudVideoView mPusherView;
    private Button pushLiveStart;
    private Button pushLiveEnd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_push_live;
    }

    @Override
    public void initView() {
        //该对象负责完成推流的主要工作
        mLivePusher = new V2TXLivePusherImpl(this, V2TXLiveDef.V2TXLiveMode.TXLiveMode_RTMP); //指定对应的直播协议为 RTMP
        mPusherView = (TXCloudVideoView) findViewById(R.id.pusher_tx_cloud_view);

        pushLiveStart = (Button) findViewById(R.id.pushLiveStart);
        pushLiveEnd = (Button) findViewById(R.id.pushLiveEnd);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //启动本地摄像头预览
        mLivePusher.setRenderView(mPusherView);
        mLivePusher.startCamera(true);

        pushLiveStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动推流
                String rtmpURL = "rtmp://3891.livepush.myqcloud.com/live/3891_user_a3954fab_d1eb?bizid=3891&txSecret=73e289071ee163c678c8b370c763b98d&txTime=60BDB6FA"; //此处填写您的 rtmp 推流地址
                int ret = mLivePusher.startPush(rtmpURL.trim());
                if (ret == V2TXLIVE_ERROR_INVALID_LICENSE) {
                    LogUtil.d("检验失败");
                }
            }
        });

        pushLiveEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束推流
                mLivePusher.stopPush();
            }
        });
    }

    @Override
    public void onClickLeft() {
        super.onClickLeft();
    }
}