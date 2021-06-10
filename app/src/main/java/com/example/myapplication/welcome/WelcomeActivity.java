package com.example.myapplication.welcome;

import androidx.annotation.NonNull;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.net.bean.HomeBean;

import com.example.framework.manager.CaCheMannager;
import com.example.framework.BaseActivity;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private final int HANDLER_SIGN = 0;//handler发送消息的标志
    private final int HANDLER_HOMEDATE=1;//获取到首页数据的时候
    private final int HANDLER_COUNTDOWN=2;//倒计时标识

    private boolean isHome=false;
    private boolean isCountDown=false;
    private android.widget.TextView coundDownTv;
    private String timeTv;

    @Override
    public int bandLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        //权限
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            },100);
        }
        coundDownTv = (TextView) findViewById(R.id.coundDownTv);
    }

    @Override
    public void initPresenter() {
        mPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.getHome();//获取首页数据
        //给倒计时初始值
        timeTv=getString(R.string.welcomeCoundDownNum);
        coundDownTv.setText(timeTv);
        handler.sendEmptyMessageDelayed(HANDLER_COUNTDOWN,1000);//倒计时
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_HOMEDATE://首页数据
                    handler.sendEmptyMessage(HANDLER_SIGN);
                    break;
                case HANDLER_COUNTDOWN:
                    int num=Integer.parseInt(coundDownTv.getText().toString());
                    num--;
                    if (num<0){
                        isCountDown=true;
                        handler.sendEmptyMessage(HANDLER_SIGN);
                    }else {
                        coundDownTv.setText(num+"");
                        handler.sendEmptyMessageDelayed(HANDLER_COUNTDOWN,1000);
                    }
                    break;
                case HANDLER_SIGN:
                    if (isHome&&isCountDown){
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));//跳转
                        finish();//关闭欢迎页页面
                    }
            }
        }
    };

    //欢迎页不可退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, getString(R.string.welcomeNotExit), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onWelcome(HomeBean homeBean) {
        CaCheMannager.getInstance().setHomeBean(homeBean);
        //将获取首页数据标识变为true
        isHome = true;
        //通知handler
        handler.sendEmptyMessage(HANDLER_HOMEDATE);
        loadingPage.showSuccessView();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
        destroy();
    }
}