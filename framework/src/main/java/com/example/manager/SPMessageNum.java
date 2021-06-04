package com.example.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.common.db.DaoMaster;
import com.example.common.db.MessageDataBase;
import com.example.common.db.MessageTable;

import java.util.List;

public class SPMessageNum {

    private static SPMessageNum messageManger;
    private String MESSAGE_SPName = "commodity";
    private String MESSAGE_EDIT_MESSAGENUM = "messageNum";
    private int num;

    public static synchronized SPMessageNum getInstance() {
        if (messageManger==null){
            messageManger = new SPMessageNum();
        }
        return messageManger;
    }

    //初始化
    public void init(Context context){

        DaoMaster daoMaster = MessageDataBase.getInstance().getDaoMaster();
        List<MessageTable> messageTables = MessageDataBase.getInstance().payLoadAll();
        MessageManager.getInstance().setMessageTableList(messageTables);
        SharedPreferences sharedPreferences = context.getSharedPreferences(MESSAGE_SPName, context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(MESSAGE_EDIT_MESSAGENUM,messageTables.size());
        edit.commit();

    }

    //查询数据库的数量
    public int queryMessageNum(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences(MESSAGE_SPName, context.MODE_PRIVATE);
        num = sharedPreferences.getInt(MESSAGE_EDIT_MESSAGENUM, 0);

        return num;

    }
    //数据库数量加一
    public void addShopNum(int i){
        num=num+i;
    }
    //数据库数量减一
    public void subShopNum(int i){
        num=num-i;
    }

    //返回数据库的数量
    public int getShopNum(){
        return num;
    }





}
