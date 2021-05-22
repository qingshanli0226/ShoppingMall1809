package com.example.myapplication.welcomem;

import com.example.net.bean.HomeBean;

import mvp.view.BaseVIew;

public interface IWelcomeView extends BaseVIew {
    void onWelcome(HomeBean homeBean);
}
