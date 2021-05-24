package com.example.shoppingcar.user.findforpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.shoppingcar.R;

@Route(path = "shoppingCar/FindForPayActivity")
public class FindForPayActivity extends BaseActivity{

    private RecyclerView shopActPayRv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_for_pay;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        shopActPayRv = (RecyclerView) findViewById(R.id.shop_act_pay_rv);
    }
}