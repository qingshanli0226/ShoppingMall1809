package com.example.electricityproject.welcome;

import android.content.Intent;
import android.widget.Toast;

import com.example.common.bean.LogBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.electricityproject.main.MainActivity;
import com.example.framework.BaseActivity;
import com.example.manager.BusinessBuyCarManger;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {
    private int time;


    @Override
    protected void initData() {

        httpPresenter.getShortProductsData();

        time=3;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                if (time<=0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timer.cancel();
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                }
            }
        },0,1000);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }


    @Override
    public void onLoginChange(LogBean isLog) {

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
    public void getShortProductData(ShortcartProductBean shortcartProductBean) {
        if (shortcartProductBean.getCode().equals("200")){
            Toast.makeText(this, "欢迎页面加载完成数据", Toast.LENGTH_SHORT).show();
            BusinessBuyCarManger.getInstance().setShortcartProductBean(shortcartProductBean);
        }
    }
}