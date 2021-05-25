package com.example.myapplication.particulars;

import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShoppingCartBean;

import mvp.view.IBaseVIew;

public interface IAddShoppingCartView extends IBaseVIew {
    void onAddShoppingCart(ShoppingCartBean shoppingCartBean);
    void onIsInventory(RegisterBean registerBean);
}
