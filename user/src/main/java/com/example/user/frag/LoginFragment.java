package com.example.user.frag;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.framework.BaseFragment;
import com.example.net.bean.LoginBean;
import com.example.user.R;
import com.example.user.login.ILoginView;
import com.example.user.login.LoginPresenter;



public class LoginFragment extends BaseFragment<LoginPresenter> implements ILoginView {

    private EditText editname;
    private EditText editpwd;
    private Button btn;

    @Override
    public void onLogin(LoginBean loginBean) {
        String code = loginBean.getCode();
        if (code.equals("200")){

        }
    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {

        editname = findViewById(R.id.editname);
        editpwd = findViewById(R.id.editpwd);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ename = editname.getText().toString().trim();
                String epwd = editpwd.getText().toString().trim();
                if (ename!=null&&epwd!=null){
                    rootPresenter.onLogin(ename,epwd);
                }
            }
        });
    }

    @Override
    public void initPresenter() {
       rootPresenter=new LoginPresenter(this);
    }

    @Override
    public void initData() {

    }
}