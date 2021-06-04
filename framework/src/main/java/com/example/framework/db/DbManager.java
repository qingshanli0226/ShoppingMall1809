package com.example.framework.db;

import android.content.Context;

public class DbManager {
    private static DbManager dbManager;

    public static DbManager getDbManager() {
        if (dbManager==null){
            dbManager=new DbManager();
        }
        return dbManager;
    }
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public void init(Context context){
        this.context=context;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "sql.db");
            daoMaster=new DaoMaster(devOpenHelper.getWritableDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoMaster==null){
            getDaoMaster();
        }
        if (daoSession==null){
            daoSession= daoMaster.newSession();
        }
        return daoSession;
    }

}
