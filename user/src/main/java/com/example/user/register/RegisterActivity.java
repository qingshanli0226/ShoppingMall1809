package com.example.user.register;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.net.bean.user.LoginBean;
import com.example.net.bean.user.RegisterBean;
import com.example.user.R;
import com.example.user.login.LoginActivity;

public class RegisterActivity extends BaseActivity<UserPresenter> implements IUserView {

    private EditText etRegisterName;
    private EditText etRegisterPwd;
    private EditText etRegisterPwdagain;
    private Button btnRegister;
    private String name;
    private String password;
    private ImageView registerImg;
    private boolean isHideFirst;
    private ImageView registerImgAgain;

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
        etRegisterPwd = findViewById(R.id.et_regist_pwd);
        etRegisterPwdagain = findViewById(R.id.et_regist_again);
        btnRegister = findViewById(R.id.btn_regist);
        registerImg = findViewById(R.id.registerImg);
        registerImgAgain = findViewById(R.id.registerImgAgain);

        registerImg.setImageResource(R.drawable.new_password_drawable_invisible);
        registerImgAgain.setImageResource(R.drawable.new_password_drawable_invisible);

        registerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHideFirst == true) {
                    registerImg.setImageResource(R.drawable.new_password_drawable_visible);
                    //密文
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    etRegisterPwd.setTransformationMethod(method1);
                    isHideFirst = false;
                } else {
                    registerImg.setImageResource(R.drawable.new_password_drawable_invisible);
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    etRegisterPwd.setTransformationMethod(method);
                    isHideFirst = true;

                }
                // 光标的位置
                int index = etRegisterPwd.getText().toString().length();
                etRegisterPwd.setSelection(index);
            }
        });

        registerImgAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHideFirst == true) {
                    registerImgAgain.setImageResource(R.drawable.new_password_drawable_visible);
                    //密文
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    etRegisterPwdagain.setTransformationMethod(method1);
                    isHideFirst = false;
                } else {
                    registerImgAgain.setImageResource(R.drawable.new_password_drawable_invisible);
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    etRegisterPwdagain.setTransformationMethod(method);
                    isHideFirst = true;

                }
                // 光标的位置
                int index = etRegisterPwdagain.getText().toString().length();
                etRegisterPwdagain.setSelection(index);
            }
        });

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

