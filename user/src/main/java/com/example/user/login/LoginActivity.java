package com.example.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commened.FiannceContants;
import com.example.commened.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.SoppingCartMemoryDataManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.user.LoginBean;
import com.example.user.R;
import com.example.user.register.RegisterActivity;

import java.net.Proxy;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    private ToolBar toolbar;
    private EditText etLoginNumber;
    private EditText etLoginPwd;
    private Button btnLogin;
    private String name1;
    private String password1;
    private SharedPreferences sharedPreferences;
    private android.widget.TextView registName;
    private ImageView passwordImg;
    private boolean isHideFirst = true;

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
        passwordImg = findViewById(R.id.password_img);
        registName = findViewById(R.id.regist_name);

        passwordImg.setImageResource(R.drawable.new_password_drawable_invisible);

        registName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        passwordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHideFirst == true) {
                    passwordImg.setImageResource(R.drawable.new_password_drawable_visible);
                    //密文
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    etLoginPwd.setTransformationMethod(method1);
                    isHideFirst = false;
                } else {
                    passwordImg.setImageResource(R.drawable.new_password_drawable_invisible);
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    etLoginPwd.setTransformationMethod(method);
                    isHideFirst = true;

                }
                // 光标的位置
                int index = etLoginPwd.getText().toString().length();
                etLoginPwd.setSelection(index);
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
            SoppingCartMemoryDataManager.getShoppingData();
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

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
