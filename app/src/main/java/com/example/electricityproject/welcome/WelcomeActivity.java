package com.example.electricityproject.welcome;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.electricityproject.main.MainActivity;
import com.example.framework.BaseActivity;
import com.example.manager.BusinessBuyCarManger;
import com.example.manager.BusinessUserManager;
import com.example.view.ToolBar;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {
    private int time;
    private int START=1;
    private Timer timer;
    private TextView skip;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==START){
                countDown();
            }

        }
    };
    private android.widget.RelativeLayout aaaaa;
    private com.example.view.ToolBar toolbar;

    @Override
    protected void initData() {


        //判断是否已经登录(已经登录获取购物车页面数据,没有登录发送一个handler)
        if (BusinessUserManager.getInstance().getIsLog()!=null){
            httpPresenter.getShortProductsData();
        }else {
            handler.sendEmptyMessage(START);
        }
        //跳过
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                timer.cancel();
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initView() {

        skip = (TextView) findViewById(R.id.skip);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
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
        loadingPage.showError(error);
    }

    //登录成功后获取购物车的数据
    @Override
    public void getShortProductData(ShortcartProductBean shortcartProductBean) {
        Log.i("zx", "getShortProductData: "+shortcartProductBean.toString());
        if (shortcartProductBean.getCode().equals("200")) {

            Toast.makeText(this, "欢迎页面加载完成数据", Toast.LENGTH_SHORT).show();

            BusinessBuyCarManger.getInstance().setShortcartProductBean(shortcartProductBean);

            handler.sendEmptyMessage(START);

        }
    }

    //倒计时
    public void countDown(){
        time = 8;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                //右上角显示跳过
                if (time==5){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            skip.setVisibility(View.VISIBLE);
                        }
                    });
                }
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