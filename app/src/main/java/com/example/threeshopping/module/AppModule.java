package com.example.threeshopping.module;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.threeshopping.MainActivity;

import com.example.threeshopping.message.MessageActivity;
import com.example.threeshopping.particulars.ParticularsActivity;
import com.example.threeshopping.payment.PaymentActivity;
import com.example.threeshopping.shipments.ShipmentsActivity;


public class AppModule {
    public static void init() {
        CommonArouter.getInstance().registerPath(Constants.PATH_MAIN, MainActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_PARTICULARS, ParticularsActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_MESSAGE, MessageActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_PAYMENT, PaymentActivity.class);
        CommonArouter.getInstance().registerPath(Constants.PATH_SHIPMENTS, ShipmentsActivity.class);

    }
}
