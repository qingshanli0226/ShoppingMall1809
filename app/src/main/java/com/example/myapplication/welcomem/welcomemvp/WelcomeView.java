package com.example.myapplication.welcomem.welcomemvp;

import com.example.net.bean.HomeBean;

import mvp.view.BaseVIew;

public interface WelcomeView extends BaseVIew {
    void onWelcome(HomeBean homeBean);
}
