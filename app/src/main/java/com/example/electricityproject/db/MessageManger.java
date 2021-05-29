package com.example.electricityproject.db;

import android.content.Context;

public class MessageManger {

    private static MessageManger messageManger;
    public static MessageManger getInstance() {
        if(messageManger==null){
            messageManger = new MessageManger();
        }
        return messageManger;
    }

    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DaoMaster getDaoMaster(Context context) {
        if (daoMaster==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "message.db");
            daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession==null){
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
