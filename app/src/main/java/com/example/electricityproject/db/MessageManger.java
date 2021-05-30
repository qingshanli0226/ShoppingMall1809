package com.example.electricityproject.db;

import android.content.Context;

import com.example.common.db.DaoMaster;
import com.example.common.db.DaoSession;

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
    private Context mContext;
    public void init(Context context){
        this.mContext=context;
    }
    public DaoMaster getDaoMaster() {
        if (daoMaster==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext, "message");
            daoMaster = new DaoMaster(devOpenHelper.getReadableDb());
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
