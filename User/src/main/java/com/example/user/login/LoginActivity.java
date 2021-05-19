package com.example.user.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.bean.LogBean;
import com.example.common.call.BusinessUserManager;
import com.example.framework.BaseActivity;
import com.example.user.R;
import com.example.user.register.RegisterActivity;
import com.example.view.ToolBar;


public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {
    private com.example.view.ToolBar toolbar;
    private android.widget.EditText loginUsername;
    private android.widget.EditText loginPwd;
    private android.widget.ImageView passwordImage;
    private android.widget.TextView goRegister;
    private android.widget.Button login;

    @Override
    protected void initData() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = loginUsername.getText().toString().trim();
                String pwd = loginPwd.getText().toString().trim();
                httpPresenter.postLogin(name,pwd);
            }
        });

        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        loginUsername = (EditText) findViewById(R.id.login_username);
        loginPwd = (EditText) findViewById(R.id.login_pwd);
        passwordImage = (ImageView) findViewById(R.id.password_image);
        goRegister = (TextView) findViewById(R.id.go_register);
        login = (Button) findViewById(R.id.login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginData(LogBean logBean) {
        if (logBean.getCode().equals("200")){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            BusinessUserManager.getInstance().setIsLog(logBean);
            finish();
        }
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
        loadingPage.showErrorView();
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }
}
