package com.example.myapplication.shoppingCart;

import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShoppingCartBean;

public interface IShoppingCartView {
    void onupdateCheck(RegisterBean bean);//全选或者全不选
    void onupdateShoppingSelect(RegisterBean bean);//修改单个商品选择
    void onShoppingSetNum(RegisterBean bean);//更改数量
    void onIsInventory(RegisterBean registerBean);//库存
    void onDeleteOneShopping(RegisterBean registerBean);//删除单个
    void onRemoveManvProduct(RegisterBean registerBean);//删除多个

    void onShoppingData(ShoppingCartBean shoppingCartBean);//删除多个
}
