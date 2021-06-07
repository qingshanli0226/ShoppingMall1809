package com.fiance.user.registers;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.fiance.user.R;
import com.fiance.user.logins.LoginActivity;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.RegisterBean;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView {


    private LinearLayout bar;
    private android.widget.ImageView back;
    private android.widget.ImageView tx;
    private android.widget.EditText username;
    private android.widget.EditText password;
    private android.widget.ImageView invisi;
    private android.widget.ImageView visi;
    private android.widget.EditText password1;
    private android.widget.ImageView invisi1;
    private android.widget.ImageView visi1;
    private android.widget.Button btn;


    @Override
    public void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String pwd1 = password1.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegisterActivity.this, "填写信息不能为空", Toast.LENGTH_SHORT).show();
                } else if (!pwd.equals(pwd1)){
                    Toast.makeText(RegisterActivity.this, "俩次密码输入不一致", Toast.LENGTH_SHORT).show();
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
        invisi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisi1.setVisibility(View.INVISIBLE);
                visi1.setVisibility(View.VISIBLE);
                password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });
        visi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisi1.setVisibility(View.VISIBLE);
                visi1.setVisibility(View.INVISIBLE);
                password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });



    }
    @Override
    public void onRegisterData(RegisterBean registerBean) {
        LogUtils.json(registerBean);
        if (registerBean.getCode().equals("200")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
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
        password1 = findViewById(R.id.password1);
        invisi1 = findViewById(R.id.invisi1);
        visi1 = findViewById(R.id.visi1);
        btn = findViewById(R.id.btn);

    }

    @Override
    public void initPresenter() {
        httpPresenter = new RegisterPresenter(this);
    }


}