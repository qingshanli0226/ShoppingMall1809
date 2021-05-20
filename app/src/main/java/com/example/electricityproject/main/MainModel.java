package com.example.electricityproject.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.common.call.BusinessARouter;

public class MainModel implements BusinessARouter.iAppManager {
    public static void init(){
        MainModel mainModel = new MainModel();
        BusinessARouter.getInstance().setAppManager(mainModel);
    }
    @Override
    public void OpenMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            //intent.FLAG_ACTIVITY_NEW_TASK
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }
}
