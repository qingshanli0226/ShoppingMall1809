package com.shoppingmall.framework.manager;

import com.shoppingmall.net.bean.FindForPayBean;

import java.util.ArrayList;
import java.util.List;

public class PaymentManager {
    public static PaymentManager paymentManager;

    public static PaymentManager getInstance() {
        if (paymentManager==null){
            paymentManager = new PaymentManager();
        }
        return paymentManager;
    }

    public void setList(FindForPayBean.ResultBean list) {
        this.list = list;
    }

    public FindForPayBean.ResultBean getList() {
        return list;
    }

    public FindForPayBean.ResultBean list = new FindForPayBean.ResultBean();

    public static PaymentManager getPaymentManager() {
        return paymentManager;
    }

    public static void setPaymentManager(PaymentManager paymentManager) {
        PaymentManager.paymentManager = paymentManager;
    }

}
