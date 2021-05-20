package com.example.user.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.common.bean.LogBean;
import com.example.common.bean.RegBean;
import com.example.framework.BaseActivity;
import com.example.user.R;
import com.example.user.login.LoginActivity;
import com.example.view.ToolBar;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView {


    private com.example.view.ToolBar toolbar;
    private android.widget.EditText registerUsername;
    private android.widget.EditText registerPwd;
    private android.widget.ImageView passwordImage;
    private android.widget.EditText registerCmPwd;
    private android.widget.ImageView registerPasswordImage;
    private android.widget.Button register;

    private boolean is_Show = false;
    private boolean is_shows = false;


    protected void initData() {

        passwordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_Show){
                    registerPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    is_Show = false;
                    passwordImage.setImageDrawable(getResources().getDrawable(R.drawable.new_password_drawable_invisible));
                }else {
                    registerPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    is_Show = true;
                    passwordImage.setImageDrawable(getResources().getDrawable(R.drawable.new_password_drawable_visible));
                }
            }
        });

        registerPasswordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_shows){
                    registerCmPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    is_shows = false;
                    registerPasswordImage.setImageDrawable(getResources().getDrawable(R.drawable.new_password_drawable_invisible));
                }else {
                    registerCmPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    is_shows = true;
                    registerPasswordImage.setImageDrawable(getResources().getDrawable(R.drawable.new_password_drawable_visible));
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = registerUsername.getText().toString().trim();
                String pwd = registerPwd.getText().toString().trim();
                httpPresenter.postRegister(name,pwd);
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RegisterPresenter(this);
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        registerUsername = (EditText) findViewById(R.id.register_username);
        registerPwd = (EditText) findViewById(R.id.register_pwd);
        passwordImage = (ImageView) findViewById(R.id.password_image);
        registerCmPwd = (EditText) findViewById(R.id.register_cm_pwd);
        registerPasswordImage = (ImageView) findViewById(R.id.register_password_image);
        register = (Button) findViewById(R.id.register);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void onRegisterData(RegBean regBean) {
        if (regBean.getCode().equals("200")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}