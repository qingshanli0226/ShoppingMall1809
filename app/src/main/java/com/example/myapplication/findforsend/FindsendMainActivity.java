package com.example.myapplication.findforsend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.framework.manager.PaySendCacheManager;
import com.example.myapplication.R;
import com.example.myapplication.findforpay.FindpayPresenter;
import com.example.myapplication.findforpay.IFindPayView;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

import java.util.List;

public class FindsendMainActivity extends BaseActivity<FindpayPresenter> implements IFindPayView {


    private androidx.recyclerview.widget.RecyclerView rv;
    private FindSendAdapter findSendAdapter;

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

        rv = findViewById(R.id.rv);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        List<FindForSendBean.ResultBean> sendList = PaySendCacheManager.getInstance().getSendList();
        findSendAdapter=new FindSendAdapter();
        findSendAdapter.updataData(sendList);
        rv.setAdapter(findSendAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Toast.makeText(this, "sendList:" + sendList, Toast.LENGTH_SHORT).show();
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