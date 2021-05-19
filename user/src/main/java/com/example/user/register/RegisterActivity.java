package com.example.user.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.login.LoginActivity;

public class RegisterActivity extends BaseActivity<UserPresenter> implements IUserView {

    private EditText etRegisterName;
    private EditText etRegisterPwd;
    private EditText etRegisterPwdagain;
    private Button btnRegister;
    private String name;
    private String password;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserPresenter(this);
    }

    @Override
    protected void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断不为空
                name = etRegisterName.getText().toString().trim();
                password = etRegisterPwd.getText().toString().trim();
                String yesPwd = etRegisterPwdagain.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(yesPwd)) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.nameOrpasswordNull), Toast.LENGTH_SHORT).show();
                } else {
                    //判断两次密码不一样
                    if (password.equals(yesPwd)) {
                        //一致
                        httpPresenter.getRegister(name, password);
                    } else {
                        //不一致
                        Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    protected void initView() {
        etRegisterName = findViewById(R.id.et_regist_number);
        etRegisterPwd = findViewById(R.id.et_login_pwd);
        etRegisterPwdagain = findViewById(R.id.et_regist_again);
        btnRegister = findViewById(R.id.btn_login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }

    @Override
    public void onRegister(RegisterBean registerBean) {
        if (registerBean.getCode().equals(getString(R.string.two))) {
            //跳转登录页面
//            FrameArouter.getInstance().build(CommonConstant.USER_LOGIN_PATH).navigation();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra(getString(R.string.small_name), name);
            intent.putExtra(getString(R.string.small_password), password);
            startActivity(intent);
        } else {
            Toast.makeText(this, "" + registerBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {

    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

    }
}

