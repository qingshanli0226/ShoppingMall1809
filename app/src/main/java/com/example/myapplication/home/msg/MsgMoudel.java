package com.example.myapplication.home.msg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.manager.CaCheArote;

public class MsgMoudel implements CaCheArote.IMsgInterface {
    public static void init() {
        MsgMoudel msgMoudel = new MsgMoudel();
        CaCheArote.getInstance().registerIMsgInterface(msgMoudel);

    }


    @Override
    public void openMsgActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MsgMainActivity.class);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
        }
    }

    public void onEvent(String event) {
    }
}
