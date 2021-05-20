package com.example.shoppingmallsix.obligationactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.framework.BaseActivity;
import com.example.shoppingmallsix.R;

public class ObligationActivity extends BaseActivity {

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_obligation;
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
