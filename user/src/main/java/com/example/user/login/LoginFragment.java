package com.example.user.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.framework.SpUtil;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.LoginBean;
import com.example.user.R;

import org.greenrobot.eventbus.EventBus;


public class LoginFragment extends BaseFragment<LoginPresenter> implements ILoginView {

    private EditText editname;
    private EditText editpwd;
    private Button loginBtn;
    private Button toRegister;


    @Override
    public int bandLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {
        editname = (EditText) findViewById(R.id.editname);
        editpwd = (EditText) findViewById(R.id.editpwd);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        toRegister = (Button) findViewById(R.id.toRegister);

    }

    @Override
    public void initData() {
        //登陆按钮点击
        loginBtn.setOnClickListener(v -> {
            String ename = editname.getText().toString().trim();
            String epwd = editpwd.getText().toString().trim();
            if (TextUtils.isEmpty(ename) || TextUtils.isEmpty(epwd)) {
                Toast.makeText(getActivity(), getString(R.string.notNull), Toast.LENGTH_SHORT).show();
                return;
            }
            //登陆
            mPresenter.getLogin(ename, epwd);
        });
        //注册页面
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("0");
            }
        });
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        String code = loginBean.getCode();
        if (code.equals("200")) {
            SpUtil.setString(getActivity(),"token",loginBean.getResult().getToken());
            Toast.makeText(getActivity(), getString(R.string.loginYes), Toast.LENGTH_SHORT).show();
            CacheUserManager.getInstance().setLoginBean(true);//修改登录状态
            EventBus.getDefault().post("2");
        }else {
            Toast.makeText(getActivity(), loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

    }


    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter(this);
    }

}