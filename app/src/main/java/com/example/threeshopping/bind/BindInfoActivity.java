package com.example.threeshopping.bind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.threeshopping.R;

public class BindInfoActivity extends BaseActivity {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText number;
    private android.widget.Button bindNumber;
    private android.widget.EditText location;
    private android.widget.Button bindLocation;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_info;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        number = (EditText) findViewById(R.id.number);
        bindNumber = (Button) findViewById(R.id.bind_number);
        location = (EditText) findViewById(R.id.location);
        bindLocation = (Button) findViewById(R.id.bind_location);
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