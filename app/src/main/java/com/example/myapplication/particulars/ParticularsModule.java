package com.example.myapplication.particulars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.manager.CaCheArote;

public class ParticularsModule implements CaCheArote.IParticularsInterface {
    public static void init(){
        ParticularsModule appModule=new ParticularsModule();
        CaCheArote.getInstance().registerIParticularsInterface(appModule);
    }
    @Override
    public void openParticularsctivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ParticularsActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
