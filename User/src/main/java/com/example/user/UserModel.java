package com.example.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.manager.BusinessARouter;
import com.example.user.login.LoginActivity;

public class UserModel implements BusinessARouter.iUserManager{

    public static void init(){
        UserModel userModel = new UserModel();
        BusinessARouter.getInstance().setUserManager(userModel);
    }

    @Override
    public void OpenLogActivity(Context context, Bundle bundle) {

        Intent intent = new Intent(context, LoginActivity.class);

        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
}
