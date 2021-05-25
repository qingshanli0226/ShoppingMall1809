package com.example.myapplication.findforpay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.framework.BaseActivity;
import com.example.myapplication.R;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

public class FindPayMainActivity extends BaseActivity<FindpayPresenter> implements IFindPayView {


    @Override
    protected int bandLayout() {
        return R.layout.activity_find_pay_main;
    }

    @Override
    public void onFindPay(FindForPayBean findForPayBean) {

    }

    @Override
    public void onFindSend(FindForSendBean findForSendBean) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}