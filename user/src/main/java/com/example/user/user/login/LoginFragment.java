package com.example.user.user.login;


import android.os.Bundle;
import android.os.UserManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.common.Constants;
import com.example.common.SpUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseFragment;


import com.example.framework.manager.CacheUserManager;


import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.manager.CacheConnectManager;

import com.example.framework.view.ToolBar;
import com.example.net.bean.EventBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.user.IUserView;
import com.example.user.user.UserPresenter;

import org.greenrobot.eventbus.EventBus;

import retrofit2.http.HEAD;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<UserPresenter> implements ToolBar.OnClickListener, IUserView {


    private EditText loginUsername;
    private EditText loginPassword;
    private CheckBox passwordInvisible;
    private Button login;
    private TextView loginRegister;
    private TextView forgetPassword;
    private ImageView weibo;
    private ImageView qq;
    private ImageView weixin;
    private int page;
    private ToolBar toolbar;
    String name, pass;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        loginUsername = (EditText) findViewById(R.id.login_username);
        loginPassword = (EditText) findViewById(R.id.login_password);
        passwordInvisible = (CheckBox) findViewById(R.id.password_invisible);
        login = (Button) findViewById(R.id.login);
        loginRegister = (TextView) findViewById(R.id.login_register);
        forgetPassword = (TextView) findViewById(R.id.forget_password);
        weibo = (ImageView) findViewById(R.id.weibo);
        qq = (ImageView) findViewById(R.id.qq);
        weixin = (ImageView) findViewById(R.id.weixin);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
    }

    @Override
    protected void initPrensenter() {
        mPresenter = new UserPresenter(this);
    }

    @Override
    protected void initData() {
        if (CacheConnectManager.getInstance().isConnect()) {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = loginUsername.getText().toString().trim();
                    pass = loginPassword.getText().toString().trim();
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass)) {
                        mPresenter.getLogin(name, pass);
                    } else {
                        Toast.makeText(getActivity(), "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getActivity(), "网络走丢了", Toast.LENGTH_SHORT).show();
        }


        Bundle bundle = CommonArouter.getInstance().getBundle();
        page = bundle.getInt("page");


        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBean eventBean = new EventBean(0,1,"登录event");
                EventBus.getDefault().post(eventBean);
            }
        });


        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "" + getString(R.string.user_login_forget), Toast.LENGTH_SHORT).show();
            }
        });
        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "" + getString(R.string.user_login_weibo), Toast.LENGTH_SHORT).show();
            }
        });
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "" + getString(R.string.user_login_weibo), Toast.LENGTH_SHORT).show();
            }
        });
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "" + getString(R.string.user_login_weibo), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        super.onClickLeft();
        Bundle bundle = new Bundle();
        bundle.putInt("page", 0);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
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
        if (loginBean.getCode().equals("200")) {

            SpUtil.putString(getActivity(), loginBean.getResult().getToken());

            CacheUserManager.getInstance().setLoginBean(loginBean);

            getActivity().finish();

            Bundle bundle = new Bundle();
            bundle.putInt("page", page);
            CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();

            CacheAwaitPaymentManager.getInstance().getAwaitPay();
        } else {
            Toast.makeText(getActivity(), "" + loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegister(RegisterBean registerBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        super.onDisConnect();
    }

    @Override
    public void onConect() {
        super.onConect();
        mPresenter.getLogin(name, pass);
        Toast.makeText(getActivity(), "正在缓冲...", Toast.LENGTH_SHORT).show();
    }
}
