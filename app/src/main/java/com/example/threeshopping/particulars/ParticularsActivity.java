package com.example.threeshopping.particulars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.threeshopping.R;

public class ParticularsActivity extends BaseActivity {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.ImageView paricularsImg;
    private android.widget.TextView paricularsName;
    private android.widget.TextView paricularsPrice;
    private android.widget.ImageView shopcar;
    private android.widget.Button particularsJoin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        paricularsImg = (ImageView) findViewById(R.id.pariculars_img);
        paricularsName = (TextView) findViewById(R.id.pariculars_name);
        paricularsPrice = (TextView) findViewById(R.id.pariculars_price);
        shopcar = (ImageView) findViewById(R.id.shopcar);
        particularsJoin = (Button) findViewById(R.id.particulars_join);
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

    }

    @Override
    public void onClickRight() {

    }
}
