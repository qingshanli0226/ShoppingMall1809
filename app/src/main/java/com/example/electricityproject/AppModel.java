package com.example.electricityproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.common.call.BusinessARouter;
import com.example.electricityproject.main.MainActivity;
import com.example.user.UserModel;
import com.example.user.login.LoginActivity;

public
class AppModel implements BusinessARouter.iAppManager{
    public static void init(){
        AppModel appModel = new AppModel();
        BusinessARouter.getInstance().setAppManager(appModel);
    }

    @Override
    public void OpenMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        String notify = bundle.getString("notify", "");
        intent.putExtra("notifyss",notify);

        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
