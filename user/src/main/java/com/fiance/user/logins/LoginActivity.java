package com.fiance.user.logins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.fiance.user.R;
import com.fiance.user.registers.RegisterActivity;
import com.shoppingmall.framework.manager.FiannceArouter;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.LoginBean;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    private android.widget.Toolbar bar;
    private android.widget.ImageView back;
    private android.widget.ImageView tx;
    private android.widget.EditText username;
    private android.widget.EditText password;
    private android.widget.ImageView invisi;
    private android.widget.ImageView visi;
    private android.widget.Button btn;
    private android.widget.TextView reguser;
    private android.widget.TextView wjpwd;
    private android.widget.ImageView i1;
    private android.widget.ImageView i2;
    private android.widget.ImageView i3;

    @Override
    public void initData() {
     back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             LoginActivity.this.finish();
         }
     });
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String name = username.getText().toString().trim();
             String pwd = password.getText().toString().trim();
             if (TextUtils.isEmpty(name) && TextUtils.isEmpty(pwd)){
                 Toast.makeText(LoginActivity.this, "填写信息不能为空", Toast.LENGTH_SHORT).show();
             }else{
                 httpPresenter.getLoginData(name,pwd);
             }
         }
     });
     invisi.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             invisi.setVisibility(View.INVISIBLE);
             visi.setVisibility(View.VISIBLE);
             password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
         }
     });
     visi.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             invisi.setVisibility(View.VISIBLE);
             visi.setVisibility(View.INVISIBLE);
             password.setTransformationMethod(PasswordTransformationMethod.getInstance());
         }
     });
    wjpwd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(LoginActivity.this, "功能暂未开放,请重新注册一个吧", Toast.LENGTH_SHORT).show();
        }
    });
    i1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(LoginActivity.this, "功能暂未开放,敬请期待", Toast.LENGTH_SHORT).show();
        }
    });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "功能暂未开放,敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "功能暂未开放,敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        reguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        bar = findViewById(R.id.bar);
        back = findViewById(R.id.back);
        tx = findViewById(R.id.tx);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        invisi = findViewById(R.id.invisi);
        visi = findViewById(R.id.visi);
        btn = findViewById(R.id.btn);
        reguser = findViewById(R.id.reguser);
        wjpwd = findViewById(R.id.wjpwd);
        i1 = findViewById(R.id.i1);
        i2 = findViewById(R.id.i2);
        i3 = findViewById(R.id.i3);
    }

    @Override
    public void initPresenter() {
        httpPresenter = new LoginPresenter(this);
    }
    @Override
    public void onLoginData(LoginBean loginBean) {
        LogUtils.json(loginBean);
        if (loginBean.getCode().equals("200")){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            FiannceArouter.getInstance().getIAppInterface().openMainActivity(this,null);
        }else{
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}