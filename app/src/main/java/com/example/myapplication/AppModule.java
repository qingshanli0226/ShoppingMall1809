package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.framework.manager.CaCheArote;
import com.example.myapplication.msg.MsgMainActivity;


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
        Log.d("AppModule", event);
    }

}
