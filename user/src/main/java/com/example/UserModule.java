package com.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.manager.CaCheArote;
import com.example.user.UserActivity;


public class UserModule implements CaCheArote.IUserInterface {
    public static void init(){
        UserModule userModule=new UserModule();
        CaCheArote.getInstance().registerIUserInterface(userModule);
    }
    @Override
    public void openLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UserActivity.class);
        if (context instanceof Activity){
            intent.putExtras(bundle);
            context.startActivity(intent);
        }else {
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void openGettureActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UserActivity.class);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else {
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
//
//    @Override
//    public void openUserMessageActivity(Context context, Bundle bundle) {
//        Intent intent = new Intent(context, UserMessageActivity.class);
//        if (context instanceof Activity){
//            intent.putExtra("param",bundle);
//            context.startActivity(intent);
//        }else {
//            intent.putExtra("param",bundle);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//        }
//    }
}
