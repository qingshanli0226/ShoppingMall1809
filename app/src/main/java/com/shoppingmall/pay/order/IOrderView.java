package com.shoppingmall.pay.order;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.OrderBean;

public interface IOrderView extends IBaseView {
    void orderInfo(OrderBean orderBean);
}
