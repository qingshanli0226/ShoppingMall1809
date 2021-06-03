package com.example.electricityproject.person.zhibo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class SinatvActivity extends BaseActivity {


    private android.widget.Button btnPush;
    private android.widget.Button btnOver;
    private com.tencent.rtmp.ui.TXCloudVideoView pusherTxCloudView;
    private android.widget.Button btnLa;
    private com.tencent.rtmp.ui.TXCloudVideoView videoView;

    private TXLivePlayer mLivePlayer;
    private TXCloudVideoView mView;
    private TXLivePusher mLivePusher;

    @Override
    protected void initData() {

        //动态权限
        String[] permission = {
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,

        };
        requestPermissions(permission, 200);

        TXLivePushConfig mLivePushConfig = new TXLivePushConfig();
        mLivePusher = new TXLivePusher(this);

        // 一般情况下不需要修改 config 的默认配置
        mLivePusher.setConfig(mLivePushConfig);

        //启动本地摄像头预览
        TXCloudVideoView mPusherView = (TXCloudVideoView) findViewById(R.id.pusher_tx_cloud_view);
        mLivePusher.startCameraPreview(mPusherView);


        //mPlayerView 即 step1 中添加的界面 view
        mView = (TXCloudVideoView) findViewById(R.id.video_view);
        //创建 player 对象
        mLivePlayer = new TXLivePlayer(this);
        //关键 player 对象与界面 view
        mLivePlayer.setPlayerView(mView);



    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        btnPush = (Button) findViewById(R.id.btn_push);
        btnOver = (Button) findViewById(R.id.btn_over);
        pusherTxCloudView = (TXCloudVideoView) findViewById(R.id.pusher_tx_cloud_view);
        btnLa = (Button) findViewById(R.id.btn_la);
        videoView = (TXCloudVideoView) findViewById(R.id.video_view);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_push:
                String rtmpURL = "rtmp://124113.livepush.myqcloud.com/live/aaaaa?txSecret=f4ad22f72d4cc9cd9f4fe7d2bb438fc2&txTime=60D53356"; //此处填写您的 rtmp 推流地址
                int ret = mLivePusher.startPusher(rtmpURL.trim());
                if (ret == -5) {
                    Toast.makeText(this, "License 校验失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_over:
                mLivePusher.stopPusher();
                mLivePusher.stopCameraPreview(true); //如果已经启动了摄像头预览，请在结束推流时将其关闭。
                break;
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
//        mView.onDestroy();
//    }
}