package com.example.myapplication.welcomem.welcomemvp;

import com.example.net.bean.HomeBean;

import mvp.view.BaseVuew;

public interface WelcomeView extends BaseVuew {
    void onWelcome(HomeBean homeBean);
}
