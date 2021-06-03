package com.example.common;

import android.content.Context;

import com.umeng.message.PushAgent;

public class UMutlis {
    private static UMutlis manager;

    public synchronized static  UMutlis getInstance() {
        if (manager==null){
            manager=new UMutlis();
        }
        return manager;
    }
    private Context mContext;
    public void init(Context context){
        this.mContext=context;
    }
    public PushAgent getPushAgent(){

        PushAgent mPushAgent = PushAgent.getInstance(mContext);
        return mPushAgent;
    }
}
