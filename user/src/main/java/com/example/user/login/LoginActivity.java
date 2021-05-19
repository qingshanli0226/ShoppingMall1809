package com.example.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commened.FiannceContants;
import com.example.commened.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.user.R;
import com.example.user.register.RegisterActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    private ToolBar toolbar;
    private EditText etLoginNumber;
    private EditText etLoginPwd;
    private Button btnLogin;
    private String name1;
    private String password1;
    private SharedPreferences sharedPreferences;
    private android.widget.TextView registName;

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        name1 = intent.getStringExtra(getString(R.string.small_name));
        password1 = intent.getStringExtra(getString(R.string.small_password));
        etLoginNumber.setText(name1);
        etLoginPwd.setText(password1);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1 = etLoginNumber.getText().toString().trim();
                password1 = etLoginPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name1) && TextUtils.isEmpty(password1)) {
                    Toast.makeText(LoginActivity.this, getString(R.string.nameOrpasswordNull), Toast.LENGTH_SHORT).show();
                } else {
                    httpPresenter.getLoginData(name1, password1);
                }
            }
        });
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        etLoginNumber = findViewById(R.id.et_login_number);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);

        registName = findViewById(R.id.regist_name);

        registName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginData(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")) {
            SpUtil.putString(this, FiannceContants.TOKEN_KEY, loginBean.getResult().getToken());
            //跳到主页面返回
            CacheUserManager.getInstance().setLoginBean(loginBean);
//            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
            ARouter.getInstance().build("/main/MainActivity").navigation();
        } else {
            Toast.makeText(this, "失败：" + loginBean, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }


}
