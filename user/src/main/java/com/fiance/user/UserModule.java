package com.fiance.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fiance.user.logins.LoginActivity;
import com.fiance.user.registers.RegisterActivity;
import com.shoppingmall.framework.manager.FiannceArouter;

public class UserModule implements FiannceArouter.IUserInterface {

    public static void init(){
        UserModule userModule = new UserModule();
        FiannceArouter.getInstance().registerIUserInterface(userModule);
    }

    @Override
    public void openLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void openRegisterActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RegisterActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else{
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
