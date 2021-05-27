package com.example.pay.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.manager.BusinessARouter;

public class PayModel implements BusinessARouter.iPayManager{

    public static void init(){
        PayModel payModel = new PayModel();
        BusinessARouter.getInstance().setPayManager(payModel);
    }


    @Override
    public void OpenPayActivity(Context context, Build build) {
        Intent intent = new Intent(context, OrderActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
