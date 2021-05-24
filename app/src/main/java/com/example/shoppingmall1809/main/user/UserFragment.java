package com.example.shoppingmall1809.main.user;

import android.widget.LinearLayout;

import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.model.LoginBean;
import com.example.shoppingmall1809.R;

public class UserFragment extends BaseFragment implements FiannceUserManager.IUserLoginChanged {


    private LinearLayout fragUserFukuan;
    private LinearLayout fragUserFahuo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personalcenter;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        fragUserFukuan = (LinearLayout) findViewById(R.id.frag_user_fukuan);
        fragUserFahuo = (LinearLayout) findViewById(R.id.frag_user_fahuo);
    }

    @Override
    public void onLoginChange(LoginBean loginBean) {

    }
}
