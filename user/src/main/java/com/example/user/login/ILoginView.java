package com.example.user.login;

import com.example.net.bean.LoginBean;

import mvp.view.BaseVIew;


public interface ILoginView extends BaseVIew {
    void onLogin(LoginBean loginBean);
}
