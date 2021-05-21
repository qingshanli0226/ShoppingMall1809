package com.example.threeshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;

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
        toolbar.setToolbarOnClickLisenter(this);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        Bundle bundle = CommonArouter.getInstance().getBundle();
        String title = bundle.getString("title");
        String pic = bundle.getString("pic");
        String price = bundle.getString("price");
        Glide.with(this).load(pic).into(paricularsImg);
        paricularsName.setText(""+title);
        paricularsPrice.setText("￥"+price);

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
