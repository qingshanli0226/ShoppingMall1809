package com.example.shoppingcar.shoppingtrolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.shoppingcar.R;

@Route(path = "/shoppingCar/ShoppingCarActivity")
public class ShoppingCarActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_car;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}