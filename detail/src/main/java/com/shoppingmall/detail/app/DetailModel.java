package com.shoppingmall.detail.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fiance.user.logins.LoginActivity;
import com.shoppingmall.framework.manager.ShopMallArouter;

public class DetailModel implements ShopMallArouter.IDetailInterface {
    public static void init(){
        DetailModel detailModel = new DetailModel();
        ShopMallArouter.getInstance().registerDetailInterface(detailModel);
    }

    @Override
    public void openDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
