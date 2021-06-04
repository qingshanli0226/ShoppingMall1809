package com.example.electricityproject.person.zhibo;

import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.view.ToolBar;

public class SinatvActivity extends BaseActivity {


    private com.example.view.ToolBar toolbar;
//    private com.tencent.rtmp.ui.TXCloudVideoView videoView;
//    private android.widget.Button push;
//    private android.widget.Button stop;
//    private TXCloudVideoView pusherTxCloudView;
//    private android.widget.Button la;
//    private TXLivePusher mLivePusher;

    @Override
    protected void initData() {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[]{
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.CALL_PHONE,
//                    Manifest.permission.READ_CONTACTS,
//                    Manifest.permission.WRITE_CONTACTS
//            }, 100);
//        }
//
//        TXLivePushConfig mLivePushConfig  = new TXLivePushConfig();
//        mLivePusher = new TXLivePusher(this);//推流对象
//// 一般情况下不需要修改 config 的默认配置
//        mLivePusher.setConfig(mLivePushConfig);
//        mLivePusher.startCameraPreview(pusherTxCloudView);//将流预览到videoview
//
//
//        TXLivePlayer mLivePlayer = new TXLivePlayer(this);
////关键 player 对象与界面 view
//        mLivePlayer.setPlayerView(videoView);
//
//
//        push.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String rtmpURL =
//                        "rtmp://124113.livepush.myqcloud.com/live/strename?txSecret=876c9291113f40a873215d5f245fad76&txTime=60DD3454"; //此处填写您的 rtmp 推流地址
//                int ret = mLivePusher.startPusher(rtmpURL.trim());
//                Toast.makeText(SinatvActivity.this, ""+ret, Toast.LENGTH_SHORT).show();
//                if (ret == -5) {
//                    Toast.makeText(SinatvActivity.this, "License 校验失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        la.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String flvUrl = "http://ivi.bupt.edu.cn/hls/cctv9hd.m3u8";
//                mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_FLV); //推荐 FLV
//            }
//        });
//
//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mLivePusher.stopPusher();
//                mLivePusher.stopCameraPreview(true); //如果已经启动了摄像头预览，请在结束推流时将其关闭。
//            }
//        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
//        videoView = (TXCloudVideoView) findViewById(R.id.video_view);
//        push = (Button) findViewById(R.id.push);
//        stop = (Button) findViewById(R.id.stop);
//        pusherTxCloudView = (TXCloudVideoView) findViewById(R.id.pusher_tx_cloud_view);
//        la = (Button) findViewById(R.id.la);
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