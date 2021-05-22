package com.example.myapplication.welcomem;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.net.bean.HomeBean;

import mvp.CaCheMannager;
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
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        coundDownTv = (TextView) findViewById(R.id.coundDownTv);
    }

    @Override
    public void initPresenter() {
        rootPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        rootPresenter.getHome();//获取首页数据
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onWelcome(HomeBean homeBean) {
        Toast.makeText(this, "获取到首页数据", Toast.LENGTH_SHORT).show();
        Log.d("WelcomeActivity", homeBean.toString());
        CaCheMannager.getInstance().setHomeBean(homeBean);

        isHome = true;
        handler.sendEmptyMessage(HANDLER_HOMEDATE);
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