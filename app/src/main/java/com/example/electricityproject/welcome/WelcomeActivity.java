package com.example.electricityproject.welcome;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.electricityproject.main.MainActivity;
import com.example.framework.BaseActivity;
import com.example.manager.BusinessBuyCarManger;
import com.example.manager.BusinessUserManager;
import com.example.manager.ShopCacheManger;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {
    private int time;
    private int START=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==START){
                countDown();
            }

        }
    };

    @Override
    protected void initData() {
        if (BusinessUserManager.getInstance().getIsLog()!=null){
            httpPresenter.getShortProductsData();
        }else {
            handler.sendEmptyMessage(START);
        }


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
        Log.i("zx", "getShortProductData: "+shortcartProductBean.toString());
        if (shortcartProductBean.getCode().equals("200")) {
            Toast.makeText(this, "欢迎页面加载完成数据", Toast.LENGTH_SHORT).show();
            BusinessBuyCarManger.getInstance().setShortcartProductBean(shortcartProductBean);
            List<ShortcartProductBean.ResultBean> list=new ArrayList<>();
            list.addAll(shortcartProductBean.getResult());
            ShopCacheManger.getInstance().setShortBeanList(list);
            handler.sendEmptyMessage(START);
        }
    }
    public void countDown(){
        Log.i("zx", "countDown: 进入");
        time = 3;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                if (time <= 0) {
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
        }, 0, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}