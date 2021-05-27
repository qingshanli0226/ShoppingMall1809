package com.example.threeshopping.shipments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.threeshopping.R;

public class ShipmentsActivity extends BaseActivity {


    private com.example.framework.view.ToolBar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shipments;
    }

    @Override
    public void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toolbar.setToolbarOnClickLisenter(this);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        finish();
    }

    @Override
    public void onClickRight() {

    }
}
