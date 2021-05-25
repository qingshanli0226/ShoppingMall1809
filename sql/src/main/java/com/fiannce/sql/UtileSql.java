package com.fiannce.sql;

import android.content.Context;


import com.example.common.Constants;

public class UtileSql {
    private static UtileSql utileSql;

    public static UtileSql getInstance(){
        if (utileSql == null){
            utileSql = new UtileSql();
        }
        return utileSql;
    }

     Context context;
     DaoMaster daoMaster;
     DaoSession daoSession;

    public void setContext(Context context) {
        this.context = context;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, Constants.SQLDB);
            daoMaster =  new DaoMaster(devOpenHelper.getWritableDatabase());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null){
            if (daoMaster == null){
                getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
