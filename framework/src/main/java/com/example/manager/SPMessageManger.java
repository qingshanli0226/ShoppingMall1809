package com.example.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.common.db.DaoMaster;
import com.example.common.db.MessageDataBase;
import com.example.common.db.MessageTable;

import java.util.List;

public class SPMessageManger {

    private static SPMessageManger messageManger;
    private String MESSAGE_SPName = "commodity";
    private String MESSAGE_EDIT_MESSAGENUM = "messageNum";


    public static synchronized SPMessageManger getInstance() {
        if (messageManger==null){
            messageManger = new SPMessageManger();
        }
        return messageManger;
    }
    //初始化
    public void init(Context context){

        DaoMaster daoMaster = MessageDataBase.getInstance().getDaoMaster();
        List<MessageTable> messageTables = MessageDataBase.getInstance().getDaoSession().loadAll(MessageTable.class);
        SharedPreferences sharedPreferences = context.getSharedPreferences(MESSAGE_SPName, context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(MESSAGE_EDIT_MESSAGENUM,messageTables.size());
        edit.commit();

    }
    //查询数据库的数量
    public int queryMessageNum(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences(MESSAGE_SPName, context.MODE_PRIVATE);
        int num = sharedPreferences.getInt(MESSAGE_EDIT_MESSAGENUM, 0);
        return num;

    }





}
