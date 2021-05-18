package com.example.user.register;

import com.example.net.bean.RegisterBean;

import mvp.view.BaseVIew;

public interface IRegisterView extends BaseVIew {
    void OnRegister(RegisterBean registerBean);
}
