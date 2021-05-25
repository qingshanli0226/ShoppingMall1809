package com.example.myapplication.personalCenter;

import com.example.net.bean.FindForPayBean;

import mvp.view.IBaseVIew;

public interface IPersonalView extends IBaseVIew {
    void onShoppingPay(FindForPayBean findForPayBean);
}
