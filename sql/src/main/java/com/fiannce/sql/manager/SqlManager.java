package com.fiannce.sql.manager;

import android.content.Context;


import com.example.common.Constants;
import com.fiannce.sql.DaoMaster;
import com.fiannce.sql.DaoSession;

public class SqlManager {
    private static SqlManager sqlManager;

    public static SqlManager getInstance(){
        if (sqlManager == null){
            sqlManager = new SqlManager();
        }
        return sqlManager;
    }

     private Context mContext;
     private DaoMaster daoMaster;
     private DaoSession daoSession;

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public DaoMaster getDaoMaster() {
        if (mContext == null) {
            throw new NullPointerException();
        }
        if (daoMaster == null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext, Constants.SQLDB);
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
