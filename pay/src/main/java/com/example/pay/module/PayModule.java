package com.example.pay.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.pay.payment.PaymentActivity;
import com.example.pay.shipments.ShipmentsActivity;

public class PayModule{
    public static void init(){
        CommonArouter.getInstance().registerPath(Constants.PATH_PAYMENT, PaymentActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_SHIPMENTS, ShipmentsActivity.class);
    }
}
