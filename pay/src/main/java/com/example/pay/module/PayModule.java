package com.example.pay.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;

import com.example.pay.awaitpayment.AwaitPaymentActivity;

import com.example.pay.orderinfo.OrderInfoActivity;


import com.example.pay.shipments.ShipmentsActivity;

public class PayModule{
    public static void init(){
        CommonArouter.getInstance().registerPath(Constants.PATH_PAYMENT, AwaitPaymentActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_SHIPMENTS, ShipmentsActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_ORDERINFOACTIVITY, OrderInfoActivity.class);
    }
}
