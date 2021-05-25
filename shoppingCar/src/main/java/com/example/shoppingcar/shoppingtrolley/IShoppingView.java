package com.example.shoppingcar.shoppingtrolley;

import com.example.framework.IBaseView;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;

public interface IShoppingView extends IBaseView {
    void onShopping(ShoppingTrolleyBean shoppingTrolleyBean);
    void onUpDateSelected(RegisterBean registerBean);
}
