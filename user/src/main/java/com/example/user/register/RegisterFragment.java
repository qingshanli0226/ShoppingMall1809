package com.example.user.register;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment<RegisterPresenter> implements ToolBar.OnClickListener, IRegisterView {


    private ToolBar toolbar;
    private EditText registerUsername;
    private EditText registerPassword;
    private CheckBox passwordInvisible;
    private EditText registerPasswordAgain;
    private CheckBox passwordInvisibleAgain;
    private Button register;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {

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
    protected void initPrensenter() {
        mPresenter = new RegisterPresenter(this);
    }

    @Override
    protected void initData() {
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
                        Toast.makeText(getActivity(), "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "用户名和密码不能为空", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        EventBus.getDefault().postSticky(0);
    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRegister(RegisterBean registerBean) {
        if(registerBean.getCode().equals("200")){
            Toast.makeText(getActivity(), ""+registerBean.getResult(), Toast.LENGTH_SHORT).show();
            EventBus.getDefault().postSticky(0);
        } else{
            Toast.makeText(getActivity(), ""+registerBean.getResult(), Toast.LENGTH_SHORT).show();
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
