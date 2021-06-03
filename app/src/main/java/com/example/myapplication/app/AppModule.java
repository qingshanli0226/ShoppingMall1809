package com.example.myapplication.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.common.log.LogUtil;
import com.example.framework.manager.CaCheArote;
import com.example.myapplication.MainActivity;


public class AppModule implements CaCheArote.IAppInterface {
    public static void init(){
        AppModule appModule=new AppModule();
        CaCheArote.getInstance().registerIAppInterface(appModule);
    }


    @Override
    public void openMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(bundle);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void onEvent(String event) {
        LogUtil.d( event);
    }

}
