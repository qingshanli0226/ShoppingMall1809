package com.example.myapplication.particulars;

import com.example.net.bean.RegisterBean;
import com.example.net.bean.AddShoppingCartBean;
import com.example.net.bean.ShoppingCartBean;

import mvp.view.IBaseVIew;

public interface IAddShoppingCartView extends IBaseVIew {
    void onAddShoppingCart(AddShoppingCartBean addShoppingCartBean);
    void onIsInventory(RegisterBean registerBean);
    void onGetShopping(ShoppingCartBean shoppingCartBean);
}
