package com.example.user.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.user.R;

@Route(path = "/user/RegisterActivity")
public class RegisterActivity extends BaseActivity {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText registerUsername;
    private android.widget.EditText registerPassword;
    private android.widget.RadioButton passwordInvisible;
    private android.widget.EditText registerPasswordAgain;
    private android.widget.RadioButton passwordInvisibleAgain;
    private android.widget.Button loginLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        registerUsername = (EditText) findViewById(R.id.register_username);
        registerPassword = (EditText) findViewById(R.id.register_password);
        passwordInvisible = (RadioButton) findViewById(R.id.password_invisible);
        registerPasswordAgain = (EditText) findViewById(R.id.register_password_again);
        passwordInvisibleAgain = (RadioButton) findViewById(R.id.password_invisible_again);
        loginLogin = (Button) findViewById(R.id.login_login);
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
