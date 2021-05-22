package com.example.shoppingmall1809.main.user;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.model.LoginBean;
import com.example.shoppingmall1809.R;

public class UserFragment extends BaseFragment implements FiannceUserManager.IUserLoginChanged {


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

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
        if (loginBean != null) {

        }else {

        }
    }
}
