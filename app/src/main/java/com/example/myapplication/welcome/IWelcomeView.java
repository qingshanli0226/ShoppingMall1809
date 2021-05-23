package com.example.myapplication.welcome;

import com.example.net.bean.HomeBean;

import mvp.view.IBaseVIew;

public interface IWelcomeView extends IBaseVIew {
    void onWelcome(HomeBean homeBean);
}
