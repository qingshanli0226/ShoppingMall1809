package com.example.threeshopping.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheHomeManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;

public class WelActivity extends BaseActivity<HomePresenter>  implements IHomeView {


    @Override
    public int getLayoutId() {
        return R.layout.activity_wel;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void initData() {
//        CountDownTimer countDownTimer = new CountDownTimer(1000 * 3, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
//            }
//        };
//
//        countDownTimer.start();

        mPresenter.getHome();
    }

    @Override
    public void onHome(HomeBean homeBean) {
        CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
        CacheHomeManager.getInstance().setHomeBean(homeBean);
    }


    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "错误"+error, Toast.LENGTH_SHORT).show();
    }
}