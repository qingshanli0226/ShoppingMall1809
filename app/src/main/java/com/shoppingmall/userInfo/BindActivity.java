package com.shoppingmall.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shoppingmall.R;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.SelectBean;

public class BindActivity extends BaseActivity<BindPresenter> implements IBindView {

    private android.widget.ImageView detailBack;
    private android.widget.ImageView detailMenu;
    private android.widget.EditText number;
    private android.widget.Button bindNumber;
    private android.widget.EditText location;
    private android.widget.Button bindLocation;

    private boolean oneFlag = false;
    private boolean twoFlag = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind;
    }

    @Override
    public void initView() {

        detailBack = (ImageView) findViewById(R.id.detailBack);
        detailMenu = (ImageView) findViewById(R.id.detailMenu);
        number = (EditText) findViewById(R.id.number);
        bindNumber = (Button) findViewById(R.id.bind_number);
        location = (EditText) findViewById(R.id.location);
        bindLocation = (Button) findViewById(R.id.bind_location);
    }

    @Override
    public void initPresenter() {
        httpPresenter = new BindPresenter(this);
    }

    @Override
    public void initData() {
        //绑定电话
        bindNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(number.getText().toString())){
                    //为空
                    Toast.makeText(BindActivity.this, "电话为空", Toast.LENGTH_SHORT).show();
                } else{
                    httpPresenter.setPhone(number.getText().toString());
                }
            }
        });
        //绑定地址
        bindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(location.getText().toString())){
                    //为空
                    Toast.makeText(BindActivity.this, "地址为空", Toast.LENGTH_SHORT).show();
                } else{
                    httpPresenter.setAddress(location.getText().toString());

                }
            }
        });
    }

    @Override
    public void onPhone(SelectBean selectBean) {
        twoFlag = true;
        Toast.makeText(this, "电话成功", Toast.LENGTH_SHORT).show();
        if(oneFlag && twoFlag){
            ShopMallUserManager.getInstance().setBind(true);
            finish();
        }
    }

    @Override
    public void onAddress(SelectBean selectBean) {
        oneFlag = true;
        Toast.makeText(this, "地址成功", Toast.LENGTH_SHORT).show();
        if(oneFlag && twoFlag){
            ShopMallUserManager.getInstance().setBind(true);
            finish();
        }
    }
}