package com.example.user.login;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.TokenSPUtility;
import com.example.common.bean.LogBean;
import com.example.framework.BaseActivity;
import com.example.manager.BusinessARouter;
import com.example.manager.BusinessUserManager;
import com.example.manager.ShopCacheManger;
import com.example.user.R;
import com.example.user.register.RegisterActivity;
import com.example.view.ToolBar;


public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView,BusinessUserManager.IUserLoginChanged {
    private com.example.view.ToolBar toolbar;
    private android.widget.EditText loginUsername;
    private android.widget.EditText loginPwd;
    private android.widget.ImageView passwordImage;
    private android.widget.TextView goRegister;
    private android.widget.Button login;
    private boolean is_Show = false;

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

        passwordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_Show){
                    loginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    is_Show = false;
                    passwordImage.setImageDrawable(getResources().getDrawable(R.drawable.new_password_drawable_invisible));
                }else {
                    loginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    is_Show = true;
                    passwordImage.setImageDrawable(getResources().getDrawable(R.drawable.new_password_drawable_visible));
                }
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
            //如果手动登录成功把当前token存入sp
            TokenSPUtility.putString(LoginActivity.this,logBean.getResult().getToken());

            ShopCacheManger.getInstance().requestShortProductData();
            BusinessUserManager.getInstance().setLogList(logBean.getResult());
            BusinessUserManager.getInstance().setIsLog(logBean);
            ShopCacheManger.getInstance().isBind(logBean);
            BusinessARouter.getInstance().getAppManager().OpenMainActivity(LoginActivity.this,null);

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
