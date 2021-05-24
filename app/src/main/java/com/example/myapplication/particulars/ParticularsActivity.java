package com.example.myapplication.particulars;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.framework.BaseActivity;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CacheUserManager;
import com.example.myapplication.R;


public class ParticularsActivity extends BaseActivity {

    private ImageView particularsCommodityImage;
    private TextView particularsCommodityName;
    private TextView particularsCommodityPresenter;
    private TextView particularsCommodityPrice;
    private android.widget.RadioButton particularsCommodityCollect;
    private android.widget.RadioButton particularsCommodityShoppingCart;
    private android.widget.Button particularsCommodityAddShoppingCart;
    private boolean isLogin;

    @Override
    public int bandLayout() {
        return R.layout.activity_particulars;
    }

    @Override
    public void initView() {
        particularsCommodityImage = (ImageView) findViewById(R.id.particularsCommodityImage);
        particularsCommodityName = (TextView) findViewById(R.id.particularsCommodityName);
        particularsCommodityPresenter = (TextView) findViewById(R.id.particularsCommodityPrice);
        particularsCommodityPrice = (TextView) findViewById(R.id.particularsCommodityPrice);
        particularsCommodityCollect = (RadioButton) findViewById(R.id.particularsCommodityCollect);
        particularsCommodityShoppingCart = (RadioButton) findViewById(R.id.particularsCommodityShoppingCart);
        particularsCommodityAddShoppingCart = (Button) findViewById(R.id.particularsCommodityAddShoppingCart);
        isLogin= CacheUserManager.getInstance().getIsLogin();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //获取传过来的值
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras!=null){
            String pic = extras.getString("pic");
            String name = extras.getString("name");
            String price = extras.getString("price");
            //赋值
            Glide.with(this).load("http://49.233.0.68:8080"+"/atguigu/img"+pic).into(particularsCommodityImage);
            particularsCommodityName.setText(name);
            particularsCommodityPrice.setText(price);
        }
        //收藏点击
        particularsCommodityCollect.setOnClickListener(v -> {
            if (!isLogin){
                //详情页面跳转
                Bundle bundle = new Bundle();
                bundle.putString("falg","2");
                ToLoginType.getInstance().setActivityType(TypeString.PARTICALARS_TYPE);
                CaCheArote.getInstance().getUserInterface().openLoginActivity(this,bundle);
            }else {
            }
        });
        //购物车点击
        particularsCommodityShoppingCart.setOnClickListener(v -> {
            if (!isLogin){
                //详情页面跳转
                Bundle bundle = new Bundle();
                bundle.putString("falg","2");
                ToLoginType.getInstance().setActivityType(TypeString.PARTICALARS_TYPE);
                CaCheArote.getInstance().getUserInterface().openLoginActivity(this,bundle);
            }else {

            }
        });
        //加入购物车点击
        particularsCommodityAddShoppingCart.setOnClickListener(v -> {
            if (!isLogin){
                //详情页面跳转
                Bundle bundle = new Bundle();
                bundle.putString("falg","2");
                ToLoginType.getInstance().setActivityType(TypeString.PARTICALARS_TYPE);
                CaCheArote.getInstance().getUserInterface().openLoginActivity(this,bundle);
            }else {
//                PopupWindow popupWindow = new PopupWindow();
//                LayoutInflater.from(this).inflate(R.layout.)
//                popupWindow.setContentView();
            }
        });
    }
}