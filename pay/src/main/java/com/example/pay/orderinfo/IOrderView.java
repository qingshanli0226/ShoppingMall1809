package com.example.pay.orderinfo;

import com.example.framework.IBaseView;
import com.example.net.bean.CartBean;
import com.example.net.bean.OrderBean;

import java.util.List;

public interface IOrderView extends IBaseView {
    //支付订单
    void onOrder(OrderBean orderBean);


}
