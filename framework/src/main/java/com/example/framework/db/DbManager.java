package com.example.framework.db;

import android.content.Context;

public class DbManager {
    public static DbManager dbManager;

    public static DbManager getDbManager() {
        if (dbManager==null){
            dbManager=new DbManager();
        }
        return dbManager;
    }
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DaoMaster getDaoMaster(Context context) {
        if (daoMaster==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "msg.db");
            daoMaster=new DaoMaster(devOpenHelper.getWritableDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession==null){
           daoSession= daoMaster.newSession();
        }
        return daoSession;

    }

}
