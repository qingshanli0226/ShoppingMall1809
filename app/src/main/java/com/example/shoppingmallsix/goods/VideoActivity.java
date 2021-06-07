package com.example.shoppingmallsix.goods;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingmallsix.R;
//import com.tencent.rtmp.TXLiveConstants;
//import com.tencent.rtmp.TXLivePlayConfig;
//import com.tencent.rtmp.TXLivePlayer;
//import com.tencent.rtmp.ui.TXCloudVideoView;
import com.umeng.message.PushAgent;

public class VideoActivity extends AppCompatActivity {

//    private TXCloudVideoView mView;
//    private TXLivePlayer mLivePlayer;
//    private TXCloudVideoView videoView;
//    private Button pause;
//    private Button go;
//    private TXLivePlayConfig mPlayConfig;
//    private Button delayed;
//    private Button quan;
//    private RelativeLayout re;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
//        mPlayConfig = new TXLivePlayConfig();
//        //mPlayerView 即 step1 中添加的界面 view
//        mView = (TXCloudVideoView) findViewById(R.id.video_view);
////创建 player 对象
//        mLivePlayer = new TXLivePlayer(VideoActivity.this);
////关键 player 对象与界面 view
//        mLivePlayer.setPlayerView(mView);
//
//        String flvUrl = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8";
//        mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_FLV); //推荐 FLV
//
//        pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 暂停
//                mLivePlayer.pause();
//            }
//        });
//
//        // 设置填充模式
//        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_ROTATION_PORTRAIT);
//// 设置画面渲染方向
//        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
//
//        PushAgent.getInstance(this).onAppStart();
//
//
//        quan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int width = getWindow().getDecorView().getWidth();
//                int height = getWindow().getDecorView().getHeight();
//                if (height > width) {//竖屏切换成横屏
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    //修改相对布局高度
//                    ViewGroup.LayoutParams layoutParams = re.getLayoutParams();
//                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//                    re.setLayoutParams(layoutParams);
//                } else {
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    //修改相对布局高度
//                    ViewGroup.LayoutParams layoutParams = re.getLayoutParams();
//                    layoutParams.height = 700;
//                    re.setLayoutParams(layoutParams);
//                }
//            }
//        });
//
//        delayed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                PopupWindow popupWindow = new PopupWindow();
//
//                popupWindow.setHeight(300);
//                popupWindow.setWidth(200);
//                View inflate = LayoutInflater.from(VideoActivity.this).inflate(R.layout.layout, null);
//                inflate.findViewById(R.id.zhi).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //自动模式  网络自适应 混合场景
//                        mPlayConfig.setAutoAdjustCacheTime(true);
//                        mPlayConfig.setMinAutoAdjustCacheTime(1);
//                        mPlayConfig.setMaxAutoAdjustCacheTime(5);
//                        //观众端的网络越好，延迟就越低；观众端网络越差，延迟就越高
//                        mLivePlayer.setConfig(mPlayConfig);
//                        //设置完成之后再启动播放
//                        popupWindow.dismiss();
//                    }
//                });
//
//                inflate.findViewById(R.id.ji).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //极速模式 卡顿率最低 游戏直播（企鹅电竞）
//                        mPlayConfig.setAutoAdjustCacheTime(true);
//                        mPlayConfig.setMinAutoAdjustCacheTime(1);
//                        mPlayConfig.setMaxAutoAdjustCacheTime(1);
//                        //对于超大码率的游戏直播（例如绝地求生）非常适合，卡顿率最低
//                        mLivePlayer.setConfig(mPlayConfig);
//                        //设置完成之后再启动播放
//                        popupWindow.dismiss();
//                    }
//                });
//
//                inflate.findViewById(R.id.liu).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //流畅模式 较流畅偏高 美女秀场（冲顶大会）
//                        mPlayConfig.setAutoAdjustCacheTime(false);
//                        mPlayConfig.setMinAutoAdjustCacheTime(5);
//                        mPlayConfig.setMaxAutoAdjustCacheTime(5);
//                        //在延迟控制上有优势，适用于对延迟大小比较敏感的场景
//                        mLivePlayer.setConfig(mPlayConfig);
//                        //设置完成之后再启动播放
//                        popupWindow.dismiss();
//
//                    }
//                });
//
//                popupWindow.setContentView(inflate);
//
//                popupWindow.setOutsideTouchable(true);
//                popupWindow.showAsDropDown(delayed, 0, 0, Gravity.TOP);
//            }
//        });

//        //Android 示例代码
//        mTXLivePlayer.setPlayListener(new ITXLivePlayListener() {
//            @Override
//            public void onPlayEvent(int event, Bundle param) {
//                if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT) {
//                    roomListenerCallback.onDebugLog("[AnswerRoom] 拉流失败：网络断开");
//                    roomListenerCallback.onError(-1, "网络断开，拉流失败");
//                }
//                else if (event == TXLiveConstants.PLAY_EVT_GET_MESSAGE) {
//                    String msg = null;
//                    try {
//                        msg = new String(param.getByteArray(TXLiveConstants.EVT_GET_MSG), "UTF-8");
//                        roomListenerCallback.onRecvAnswerMsg(msg);
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            @Override
//            public void onNetStatus(Bundle status) {
//            }
//        });


//        go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // 继续
//                mLivePlayer.resume();
//
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
//        mView.onDestroy();

    }

    private void initView() {
//        pause = (Button) findViewById(R.id.pause);
//        go = (Button) findViewById(R.id.go);
//        delayed = (Button) findViewById(R.id.delayed);
//        quan = (Button) findViewById(R.id.quan);
//        re = (RelativeLayout) findViewById(R.id.re);
    }
}
