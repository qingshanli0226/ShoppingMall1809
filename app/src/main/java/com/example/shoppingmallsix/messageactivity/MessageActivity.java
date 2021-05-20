package com.example.shoppingmallsix.messageactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.shoppingmallsix.R;

public class MessageActivity extends BaseActivity implements ToolBar.IToolbarListener{

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
        return R.layout.activity_message;
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
