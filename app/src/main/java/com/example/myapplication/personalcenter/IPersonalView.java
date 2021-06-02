package com.example.myapplication.personalcenter;

import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

import mvp.view.IBaseVIew;

public interface IPersonalView extends IBaseVIew {
    void onShoppingPay(FindForPayBean findForPayBean);
    void onShoppingSend(FindForSendBean findForSendBean);
    void ondend(FindForPayBean findForPayBean);
}
