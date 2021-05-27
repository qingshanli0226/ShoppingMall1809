package com.example.shoppingcar.shoppingtrolley;

import com.example.framework.IBaseView;
import com.example.net.model.OrderinfoBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;

public interface IShoppingView extends IBaseView {
    void onUpDateSelected(RegisterBean registerBean);
    void onSelectAllProduct(RegisterBean registerBean);
    void onRemoveManyProduct(RegisterBean registerBean);
    void onCheckInventory(ShoppingTrolleyBean shoppingTrolleyBean);
    void onUpDateProductNum(RegisterBean registerBean);
    void onCheckOneProductInventory(RegisterBean registerBean);
}
