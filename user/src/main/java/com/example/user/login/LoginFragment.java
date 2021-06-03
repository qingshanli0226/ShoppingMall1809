package com.example.user.login;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commom.ShopConstants;
import com.example.commom.SpUtil;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.TokenInterceptor;
import com.example.net.model.LoginBean;
import com.example.user.R;


public class LoginFragment extends BaseFragment<LoginPresneter> implements ILoginView {

    private ToolBar toolbar;
    private EditText actLogPassword;
    private TextView actLogBut;
    private EditText actLogUser;
    private TextView toRegister;
    private TextView forgetThePassword;
    private android.widget.ImageView weibo;
    private android.widget.ImageView shareQq;
    private android.widget.ImageView weixin;
    private boolean isConceal = false;
    private ImageView loginConceal;

    private IShowRegisterInterface iShowRegisterInterface;

    public interface IShowRegisterInterface {
        void onShowRegister();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
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
                Toast.makeText(getActivity(), R.string.theUsernameAndPasswordCannotBeEmpty, Toast.LENGTH_SHORT).show();
                return;
            }
            httpPresenter.getRegisterData(user, pwd);

        });

        //立即注册
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iShowRegisterInterface = (IShowRegisterInterface) getActivity();
                iShowRegisterInterface.onShowRegister();
            }
        });

        forgetThePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), getResources().getString(R.string.unopen), Toast.LENGTH_SHORT).show();
            }
        });

        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), getResources().getString(R.string.comingSoon), Toast.LENGTH_SHORT).show();
            }
        });

        shareQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), getResources().getString(R.string.comingSoon), Toast.LENGTH_SHORT).show();
            }
        });

        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), getResources().getString(R.string.comingSoon), Toast.LENGTH_SHORT).show();
            }
        });

        loginConceal.setOnClickListener(new View.OnClickListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onClick(View view) {
                if (!isConceal) {
                    loginConceal.setImageResource(R.drawable.new_password_drawable_visible);
                    actLogPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isConceal = true;
                } else {
                    loginConceal.setImageResource(R.drawable.new_password_drawable_invisible);
                    actLogPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isConceal = false;
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
            SpUtil.setString(getContext(), ShopConstants.TOKEN_KEY, loginBean.getResult().getToken());
            ShopeUserManager.getInstance().setLoginBean(loginBean);
            if (CacheManager.getInstance().decideARoutPage.equals(ShopConstants.AROUT_PARTICULARS)){
                getActivity().finish();
                return;
            }
            ARouter.getInstance().build(ShopConstants.MAIN_PATH).navigation();
        }
    }

    @Override
    public void onLeftImgClick() {
        getActivity().finish();
    }

    @Override
    public void destroy() {
        super.destroy();
        iShowRegisterInterface = null;
    }
}