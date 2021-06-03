package com.example.myapplication.personalcenter.findforsend;

import com.example.framework.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.personalcenter.findforpay.FindpayPresenter;
import com.example.myapplication.personalcenter.findforpay.IFindPayView;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

public class FindsendMainActivity extends BaseActivity<FindpayPresenter> implements IFindPayView {


    @Override
    protected int bandLayout() {
        return R.layout.activity_findsend_main;
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