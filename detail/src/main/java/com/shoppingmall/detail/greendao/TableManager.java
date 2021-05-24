package com.shoppingmall.detail.greendao;

import android.content.Context;

import com.yoho.greendao.gen.DaoMaster;
import com.yoho.greendao.gen.DaoSession;

public class TableManager {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper helper;
    private Context mContext;

    public void init(Context context){
        this.mContext = context;
    }

    public DaoMaster getDaoMaster() {
        if (mContext==null){
            throw new NullPointerException();
        }
        if (daoMaster==null){
            helper = new DaoMaster.DevOpenHelper(mContext, "db");
            daoMaster = new DaoMaster(helper.getWritableDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoMaster==null){
            getDaoMaster();
        }
        if (daoSession==null){
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public void close(){
        if (helper!=null){
            helper.close();
            helper = null;
        }
        if (daoSession!=null){
            daoSession.clear();
            daoSession = null;
        }
    }

    private static TableManager tableManager;

    public static TableManager getInstance() {
        if (tableManager==null){
            tableManager = new TableManager();
        }
        return tableManager;
    }


}
