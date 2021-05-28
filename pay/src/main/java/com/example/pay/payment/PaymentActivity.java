package com.example.pay.payment;

import android.widget.Button;
import android.widget.CheckBox;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.pay.R;

public class PaymentActivity extends BaseActivity {

    private com.example.framework.view.ToolBar toolbar;
    private android.widget.CheckBox weiCheck;
    private android.widget.CheckBox zfbCheck;
    private android.widget.Button pay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        weiCheck = (CheckBox) findViewById(R.id.weiCheck);
        zfbCheck = (CheckBox) findViewById(R.id.zfbCheck);
        pay = (Button) findViewById(R.id.pay);
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
