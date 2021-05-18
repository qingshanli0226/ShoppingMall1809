package com.example.user.register;

import android.widget.EditText;
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
import com.example.net.model.RegisterBean;
import com.example.user.R;
import com.example.user.login.ILoginView;
import com.example.user.login.LoginPresneter;

@Route(path = "/user/RegisterActivity")
public class RegisterActivity extends BaseActivity<RegisterPresneter> implements IRegisterView, ILoginView {


    private ToolBar toolbar;
    private TextView actRegPhone;
    private EditText actRegUsername;
    private EditText actRegPassword;
    private EditText actRegPasswordtwo;
    private TextView actRegBut;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        actRegBut.setOnClickListener(view -> {
            String user = actRegUsername.getText().toString().trim();
            String pwd = actRegPassword.getText().toString().trim();
            String pwdtwo = actRegPasswordtwo.getText().toString().trim();

            if (user.equals("") || pwd.equals("")) {
                Toast.makeText(this, R.string.theUsernameAndPasswordCannotBeEmpty, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pwd.equals(pwdtwo)) {
                Toast.makeText(this, R.string.theTwoPasswordsDontMatch, Toast.LENGTH_SHORT).show();
                return;
            }
            httpPresenter.getRegisterData(user, pwd);
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RegisterPresneter(this);
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        actRegPhone = (TextView) findViewById(R.id.act_reg_phone);
        actRegUsername = (EditText) findViewById(R.id.act_reg_username);
        actRegPassword = (EditText) findViewById(R.id.act_reg_password);
        actRegPasswordtwo = (EditText) findViewById(R.id.act_reg_passwordtwo);
        actRegBut = (TextView) findViewById(R.id.act_reg_but);
    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            String user = actRegUsername.getText().toString().trim();
            String pwd = actRegPassword.getText().toString().trim();
//            Toast.makeText(this, registerBean.getResult(), Toast.LENGTH_SHORT).show();
//            Bundle bundle = new Bundle();
//            bundle.putString("user",user);
//            bundle.putString("pwd",pwd);
//            FiannceArouter.getInstance().build(FianceConstants.LOGIN_PATH).navigation(bundle);

            loginPresneter = new LoginPresneter(this);
            loginPresneter.getRegisterData(user, pwd);

        }
    }

    private LoginPresneter loginPresneter;

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
    public void destroy() {
        super.destroy();
        if (loginPresneter!=null){
            loginPresneter.detachView();
            loginPresneter=null;
        }
    }

    @Override
    public void onLoginData(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")) {
            FiannceUserManager.getInstance().setLoginBean(loginBean);
            SpUtil.setString(this, ShopConstants.TOKEN_KEY, loginBean.getResult().getToken());
            ARouter.getInstance().build(ShopConstants.MAIN_PATH).navigation();
        }
    }
}