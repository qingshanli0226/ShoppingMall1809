package com.example.user.register;

import com.example.net.bean.RegisterBean;

import mvp.view.IBaseVIew;

public interface IRegisterView extends IBaseVIew {
    void OnRegister(RegisterBean registerBean);
}
