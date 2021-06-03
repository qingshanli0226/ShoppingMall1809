package com.example.myapplication.shoppingcart.payorder;

import com.example.net.bean.OrderinfoBean;

import mvp.view.IBaseVIew;

public interface IOrderView extends IBaseVIew {
    void onOrder(OrderinfoBean orderinfoBean);
}
