package com.example.user.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.login.LoginActivity;


public class RegisterActivity extends BaseActivity<UserPresenter> implements ToolBar.OnClickListener,IUserView {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText registerUsername;
    private android.widget.EditText registerPassword;
    private android.widget.CheckBox passwordInvisible;
    private android.widget.EditText registerPasswordAgain;
    private android.widget.CheckBox passwordInvisibleAgain;
    private android.widget.Button register;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        registerUsername = (EditText) findViewById(R.id.register_username);
        registerPassword = (EditText) findViewById(R.id.register_password);
        passwordInvisible = (CheckBox) findViewById(R.id.password_invisible);
        registerPasswordAgain = (EditText) findViewById(R.id.register_password_again);
        passwordInvisibleAgain = (CheckBox) findViewById(R.id.password_invisible_again);
        register = (Button) findViewById(R.id.register);
        toolbar.setToolbarOnClickLisenter(this);
    }

    @Override
    public void initPresenter() {
        mPresenter = new UserPresenter(this);
    }

    @Override
    public void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = registerUsername.getText().toString().trim();
                String pass = registerPassword.getText().toString().trim();
                String again = registerPasswordAgain.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(again)){
                    if (pass.equals(again)){
                        LogUtils.json(name+":"+pass);
                        mPresenter.getRegister(name,pass);
                    }else {
                        Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();

                }
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
    public void onRegister(RegisterBean registerBean) {
        loadPage.showSuccessLayout();
        if(registerBean.getCode().equals("200")){
            Toast.makeText(this, ""+registerBean.getResult(), Toast.LENGTH_SHORT).show();
            finish();
//            SpUtil.putString(this, CommonConstant.SP_TOKEN,loginBean.getResult().getToken());
//            //跳到主页面返回
//            CacheUserManager.getInstance().setLoginBean(loginBean);
//            Bundle bundle = new Bundle();
//            bundle.putInt("page",0);
//            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).with(bundle).navigation();
        } else{
            Toast.makeText(this, ""+registerBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {

    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

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
