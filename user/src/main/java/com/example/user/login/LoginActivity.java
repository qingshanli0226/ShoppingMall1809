package com.example.user.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.user.R;

@Route(path = "/user/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements ToolBar.OnClickListener,ILoginView {


    private ToolBar toolbar;
    private android.widget.EditText loginUsername;
    private android.widget.EditText loginPassword;
    private android.widget.RadioButton passwordInvisible;
    private android.widget.Button loginLogin;
    private android.widget.TextView loginRegister;
    private android.widget.TextView forgetPassword;
    private android.widget.ImageView weibo;
    private android.widget.ImageView qq;
    private android.widget.ImageView weixin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        loginUsername = (EditText) findViewById(R.id.login_username);
        loginPassword = (EditText) findViewById(R.id.login_password);
        passwordInvisible = (RadioButton) findViewById(R.id.password_invisible);
        loginLogin = (Button) findViewById(R.id.login_login);
        loginRegister = (TextView) findViewById(R.id.login_register);
        forgetPassword = (TextView) findViewById(R.id.forget_password);
        weibo = (ImageView) findViewById(R.id.weibo);
        qq = (ImageView) findViewById(R.id.qq);
        weixin = (ImageView) findViewById(R.id.weixin);
        toolbar.setToolbarOnClickLisenter(this);
    }

    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void initData() {

      

        loginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = loginUsername.getText().toString().trim();
                String pass = loginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
//                    Toast.makeText(LoginActivity.this, "2", Toast.LENGTH_SHORT).show();
                    mPresenter.getLogin(name,pass);
                }
            }
        });

        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/user/RegisterActivity").withInt("",1).navigation();
            }
        });


        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, ""+getString(R.string.user_login_forget), Toast.LENGTH_SHORT).show();
            }
        });
        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, ""+getString(R.string.user_login_weibo), Toast.LENGTH_SHORT).show();
            }
        });
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, ""+getString(R.string.user_login_weibo), Toast.LENGTH_SHORT).show();
            }
        });
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, ""+getString(R.string.user_login_weibo), Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLogin(LoginBean loginBean) {
        loadPage.showSuccessLayout();
        LogUtils.json(loginBean);
        if(loginBean.getCode().equals("200")){
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
//            SpUtil.putString(this, CommonConstant.SP_TOKEN,loginBean.getResult().getToken());
//            //跳到主页面返回
//            CacheUserManager.getInstance().setLoginBean(loginBean);
//            Bundle bundle = new Bundle();
//            bundle.putInt("page",0);
//            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).with(bundle).navigation();
        } else{
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {
        loadPage.showTransparentLoadLayout();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
        LogUtils.json(error);
    }
}
