package com.example.myapplication.payorder;

import com.example.net.bean.OrderinfoBean;

import mvp.view.IBaseVIew;

public interface IOrderView extends IBaseVIew {
    void onOrder(OrderinfoBean orderinfoBean);
}
