package com.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.manager.CaCheArote;
import com.example.user.UserActivity;
import com.example.user.phoneaddress.PhoneAddressMainActivity;


public class UserModule implements CaCheArote.IUserInterface {
    public static void init(){
        UserModule userModule=new UserModule();
        CaCheArote.getInstance().registerIUserInterface(userModule);
    }
    @Override
    public void openLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UserActivity.class);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        if (context instanceof Activity){
            ((Activity) context).startActivityForResult(intent,100);
        }else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ((Activity) context).startActivityForResult(intent,100);
        }
    }

    @Override
    public void openPhoneAddressActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PhoneAddressMainActivity.class);
        if (context instanceof Activity){
            if (bundle!=null){
                intent.putExtras(bundle);
            }
            ((Activity) context).startActivityForResult(intent,100);
        }else {
            if (bundle!=null){
                intent.putExtras(bundle);
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ((Activity) context).startActivityForResult(intent,100);
        }
    }

}
