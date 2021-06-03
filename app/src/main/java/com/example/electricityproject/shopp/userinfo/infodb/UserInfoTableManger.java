package com.example.electricityproject.shopp.userinfo.infodb;

import android.content.Context;



public class UserInfoTableManger {

    private static UserInfoTableManger userInfoTableManger;
    public static UserInfoTableManger getInstance() {
        if (userInfoTableManger==null){
            userInfoTableManger = new UserInfoTableManger();
        }
        return userInfoTableManger;
    }

    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DaoMaster getDaoMaster(Context context) {
        if (daoMaster==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "user.db");
            daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null){
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
