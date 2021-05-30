package com.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.ScriptC;

import com.example.commom.ShopConstants;
import com.example.framework.db.DaoMaster;
import com.example.framework.db.DaoSession;
import com.example.framework.db.MessageTable;

import java.util.List;


public class GreenManager {

    private Context context;
    private DaoSession daoSession;

    public void init(Context context) {
        this.context = context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, ShopConstants.SQ_MANAGE);
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
    }

    //获取所有
    public List<MessageTable>  getMessage() {
        return  daoSession.loadAll(MessageTable.class);
    }
    //添加一个
    public void setMessage(MessageTable message) {
        daoSession.insert(message);
    }

    public boolean addCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
        if (count!=-1){
            ++count;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ShopConstants.SP_MANAGE_NAME,count);
            edit.commit();
            return true;
        }
        return false;
    }

    public boolean subCount(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShopConstants.SP_MANAGE, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(ShopConstants.SP_MANAGE_NAME, -1);
        if (count!=-1){
            --count;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ShopConstants.SP_MANAGE_NAME,count);
            edit.commit();
            return true;
        }
        return false;
    }

    private static GreenManager greenManager;

    private GreenManager() {
    }

    public static GreenManager getInstance() {
        if (greenManager == null) {
            greenManager = new GreenManager();
        }
        return greenManager;
    }


}
