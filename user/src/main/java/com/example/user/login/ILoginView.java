package com.example.user.login;

import com.example.net.bean.LoginBean;

import mvp.view.IBaseVIew;


public interface ILoginView extends IBaseVIew {
    void onLogin(LoginBean loginBean);
    void onAutoLogin(LoginBean loginBean);
}
