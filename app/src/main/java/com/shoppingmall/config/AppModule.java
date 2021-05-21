package com.shoppingmall.config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shoppingmall.framework.manager.FiannceArouter;
import com.shoppingmall.main.MainActivity;

public class AppModule implements FiannceArouter.IAppInterface {
    public static void init(){
        AppModule appModule = new AppModule();
        FiannceArouter.getInstance().registerIAppInterface(appModule);
    }

    @Override
    public void openMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else{
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
