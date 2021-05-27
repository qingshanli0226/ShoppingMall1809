package com.example.shoppingcar.orderForm;

import com.example.framework.IBaseView;
import com.example.net.model.OrderinfoBean;

public interface IOrderFormView extends IBaseView {
    void onOrderInfo(OrderinfoBean orderinfoBean);
}
