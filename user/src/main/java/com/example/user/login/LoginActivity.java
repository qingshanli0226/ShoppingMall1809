package com.example.user.login;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commom.ShopConstants;
import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.LoginBean;
import com.example.user.R;

@Route(path = "/user/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresneter> implements ILoginView {

    private ToolBar toolbar;
    private EditText actLogPassword;
    private TextView actLogBut;
    private EditText actLogUser;
    private TextView toRegister;
    private TextView forgetThePassword;
    private android.widget.ImageView weibo;
    private android.widget.ImageView shareQq;
    private android.widget.ImageView weixin;
    private boolean isConceal=false;
    private ImageView loginConceal;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String buser = bundle.getString("user");
            String bpwd = bundle.getString("pwd");
            if (!TextUtils.isEmpty(buser) && !TextUtils.isEmpty(bpwd)) {
                actLogUser.setText(buser);
                actLogPassword.setText(bpwd);
            }
        }

        actLogBut.setOnClickListener(view -> {
            String user = actLogUser.getText().toString().trim();
            String pwd = actLogPassword.getText().toString().trim();
            if (user.equals("") || pwd.equals("")) {
                Toast.makeText(this, R.string.theUsernameAndPasswordCannotBeEmpty, Toast.LENGTH_SHORT).show();
                return;
            }
            httpPresenter.getRegisterData(user,pwd);

        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ShopConstants.REGISTER_PATH).navigation();
            }
        });

        forgetThePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "本功能暂未开放，重新注册一个吧，敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "本功能暂未开放,合作没谈拢，敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        shareQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "本功能暂未开放,合作没谈拢，敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "本功能暂未开放,合作没谈拢，敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        loginConceal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConceal){
                    loginConceal.setImageResource(R.drawable.new_password_drawable_visible);
                    actLogPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isConceal=true;
                }else {
                    loginConceal.setImageResource(R.drawable.new_password_drawable_invisible);
                    actLogPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isConceal=false;
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresneter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        actLogPassword = (EditText) findViewById(R.id.act_log_password);
        actLogBut = (TextView) findViewById(R.id.act_log_but);
        actLogUser = (EditText) findViewById(R.id.act_log_user);
        toRegister = (TextView) findViewById(R.id.toRegister);
        forgetThePassword = (TextView) findViewById(R.id.forgetThePassword);
        weibo = (ImageView) findViewById(R.id.weibo);
        shareQq = (ImageView) findViewById(R.id.share_qq);
        weixin = (ImageView) findViewById(R.id.weixin);
        loginConceal = (ImageView) findViewById(R.id.loginConceal);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void onLoginData(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")) {
            FiannceUserManager.getInstance().setLoginBean(loginBean);
            SpUtil.setString(this, ShopConstants.TOKEN_KEY,loginBean.getResult().getToken());
            ARouter.getInstance().build(ShopConstants.MAIN_PATH).navigation();
        }
    }
}