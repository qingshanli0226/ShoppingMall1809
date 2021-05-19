package com.example.user.frag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.register.IRegisterView;
import com.example.user.register.RegisPresenter;

import mvp.view.BaseFragment;


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