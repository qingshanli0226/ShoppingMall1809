package com.shoppingmall.welcome;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.shoppingmall.main.MainActivity;
import com.shoppingmall.R;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.HomeBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {
    private final int GET_DATA_OK = 0;
    private final int GET_DATA_NO = 1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_welcom;
    }

    @Override
    public void initView() {
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        httpPresenter.getHomeData();
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        //单例存储数据
        CacheManager.getInstance().setHomeBean(homeBean);
        LogUtils.json(homeBean);
        handler.sendEmptyMessageDelayed(GET_DATA_OK,1000);
        if (homeBean==null){
            handler.sendEmptyMessageDelayed(GET_DATA_NO,1000);
        }
    }
    private final Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == GET_DATA_OK){
                Toast.makeText(WelcomeActivity.this, "加载数据成功！跳转主页面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                initData();
                Toast.makeText(WelcomeActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
            }
        }
    };
}