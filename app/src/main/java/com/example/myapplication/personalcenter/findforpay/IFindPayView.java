package com.example.myapplication.personalcenter.findforpay;

import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

import mvp.view.IBaseVIew;

public interface IFindPayView extends IBaseVIew {
    void onFindPay(FindForPayBean findForPayBean);
    void onFindSend(FindForSendBean findForSendBean);
}
