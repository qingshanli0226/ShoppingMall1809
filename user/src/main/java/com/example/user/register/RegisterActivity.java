package com.example.user.register;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private EditText actRegUsername;
    private EditText actRegPassword;
    private EditText actRegPasswordtwo;
    private TextView actRegBut;
    private boolean isConceal=false;
    private ImageView regConceal1;
    private ImageView regConceal2;

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

        regConceal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConceal){
                    regConceal1.setImageResource(R.drawable.new_password_drawable_visible);
                    actRegPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isConceal=true;
                }else {
                    regConceal1.setImageResource(R.drawable.new_password_drawable_invisible);
                    actRegPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isConceal=false;
                }
            }
        });

        regConceal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConceal){
                    regConceal2.setImageResource(R.drawable.new_password_drawable_visible);
                    actRegPasswordtwo.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isConceal=true;
                }else {
                    regConceal2.setImageResource(R.drawable.new_password_drawable_invisible);
                    actRegPasswordtwo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isConceal=false;
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RegisterPresneter(this);
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        actRegUsername = (EditText) findViewById(R.id.act_reg_username);
        actRegPassword = (EditText) findViewById(R.id.act_reg_password);
        actRegPasswordtwo = (EditText) findViewById(R.id.act_reg_passwordtwo);
        actRegBut = (TextView) findViewById(R.id.act_reg_but);
        regConceal1 = (ImageView) findViewById(R.id.regConceal1);
        regConceal2 = (ImageView) findViewById(R.id.regConceal2);
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