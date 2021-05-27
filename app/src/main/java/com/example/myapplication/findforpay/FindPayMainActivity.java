package com.example.myapplication.findforpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.framework.BaseActivity;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.PaySendCacheManager;
import com.example.myapplication.R;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class FindPayMainActivity extends BaseActivity<FindpayPresenter> implements IFindPayView {



    private androidx.recyclerview.widget.RecyclerView rv;
    private FindPayAdapter findPayAdapter;

    @Override
    protected int bandLayout() {
        return R.layout.activity_find_pay_main;
    }



    @Override
    public void initView() {
        rv = findViewById(R.id.rv);
        findPayAdapter=new FindPayAdapter();

    }

    @Override
    public void initPresenter() {
//        findPayAdapter.setRecyclerItemClickListener(new BaseRecyclerViewAdapter.IRecyclerItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
//            }
//
//            @Override
//            public void onItemLongClick(int position) {
//
//            }
//        });

    }

    @Override
    public void initData() {
         mPresenter=new FindpayPresenter(this);
         mPresenter.onFindPay();

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


    @Override
    public void destroy() {
        super.destroy();

    }

    @Override
    public void onFindPay(FindForPayBean findForPayBean) {
        findPayAdapter.updataData(findForPayBean.getResult());
        rv.setAdapter(findPayAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onFindSend(FindForSendBean findForSendBean) {

    }
}