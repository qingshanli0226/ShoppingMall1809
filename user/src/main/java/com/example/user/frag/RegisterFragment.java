package com.example.user.frag;

import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.register.IRegisterView;
import com.example.user.register.RegisPresenter;

import com.example.framework.BaseFragment;


public class RegisterFragment extends BaseFragment<RegisPresenter> implements IRegisterView {

    @Override
    public int bandLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void OnRegister(RegisterBean registerBean) {

    }
}