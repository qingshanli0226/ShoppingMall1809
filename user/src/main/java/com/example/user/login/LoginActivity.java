package com.example.user.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.blankj.utilcode.util.LogUtils;
import com.example.common.Constants;
import com.example.common.SpUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.LoginManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.user.R;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ToolBar.OnClickListener,ILoginView {


    private ToolBar toolbar;
    private android.widget.EditText loginUsername;
    private android.widget.EditText loginPassword;
    private android.widget.CheckBox passwordInvisible;
    private android.widget.Button login;
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
        passwordInvisible = (CheckBox) findViewById(R.id.password_invisible);
        login = (Button) findViewById(R.id.login);
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


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = loginUsername.getText().toString().trim();
                String pass = loginPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass)){
                    mPresenter.getLogin(name,pass);
                }else {
                   Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();

                }
            }
        });

        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_REGISTER).navigation();
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
        CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
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
            SpUtil.putString(this,loginBean.getResult().getToken());
            LogUtils.json(loginBean.getResult().getToken()+"Login");
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();

            LoginManager.getInstance().setLoginstate(true);
//            SpUtil.putString(this, CommonConstant.SP_TOKEN,loginBean.getResult().getToken());
//            //跳到主页面返回
//            CacheUserManager.getInstance().setLoginBean(loginBean);
//            Bundle bundle = new Bundle();
//            bundle.putInt("page",0);
//            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).with(bundle).navigation();
        } else{
            LoginManager.getInstance().setLoginstate(false);
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Boolean loginstate = LoginManager.getInstance().getLoginstate();
            if (loginstate!=null){
                if (!loginstate){
                    CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
                }else {
                }
            }else {
                CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void showError(String error) {
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
        LogUtils.json(error);
    }
}
