package com.example.electricityproject.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.electricityproject.advert.AdvertActivity;
import com.example.manager.BusinessARouter;

public class MainModel implements BusinessARouter.iAppManager {
    public static void init() {
        MainModel mainModel = new MainModel();
        BusinessARouter.getInstance().setAppManager(mainModel);
    }

    @Override
    public void OpenMainActivity(Context context, Bundle bundle) {
        if (bundle != null) {
            String notify = bundle.getString("notify", "");
            if (notify.equals("go_buyCar")) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("notifyss", notify);
            }
        }
        Intent intent = new Intent(context, MainActivity.class);
        if (context instanceof Activity) {
            context.startActivity(intent);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void OpenAdvertActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AdvertActivity.class);
        if (context instanceof Activity) {
            context.startActivity(intent);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

}
