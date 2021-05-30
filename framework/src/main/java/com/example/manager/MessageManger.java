package com.example.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class MessageManger {

    private static MessageManger messageManger;
    private Context context;
    private String MESSAGE_SpName = "message";
    private SharedPreferences.Editor edit;
    private IMessageListener iMessageListener;

    public static MessageManger getInstance() {
        if (messageManger==null){
            messageManger = new MessageManger();
        }
        return messageManger;
    }

    public void init(Context context){
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(MESSAGE_SpName, 0);
        edit = sharedPreferences.edit();
    }

    public void addMes(IMessageListener iMessageListener){
        iMessageListener.onResult(true);
    }

    public interface IMessageListener{
        void onResult(boolean isSuccess);
    }


}
