package com.example.electricityproject.person.findforpay;

import android.widget.Toast;

import com.example.common.bean.FindForPayBean;
import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;

public class FindForPayActivity extends BaseActivity<FindForPayPresenter> implements IFindForPayView {


    @Override
    public void getFindForPayData(FindForPayBean findForPayBean) {
        if (findForPayBean.getCode().equals("200")){
            loadingPage.showSuccessView();
            Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {
        httpPresenter.getForPayData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new FindForPayPresenter(this);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_for_pay;
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        loadingPage.showErrorView();

    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }
}