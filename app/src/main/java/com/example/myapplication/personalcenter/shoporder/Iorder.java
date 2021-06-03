package com.example.myapplication.personalcenter.shoporder;

import com.example.net.bean.ConfirmServerPayResultBean;

import mvp.view.IBaseVIew;

public interface Iorder extends IBaseVIew {
    void onConfirmServer(ConfirmServerPayResultBean confirmServerPayResultBean);
}
