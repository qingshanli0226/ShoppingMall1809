package com.example.common.call;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

public class BuisnessNetManager {

    private static BuisnessNetManager manager;

    private List<NetConnectListener> list=new ArrayList<>();

    private Context context;

    public static BuisnessNetManager getInstance() {
        if (manager==null){
            manager=new BuisnessNetManager();
        }
        return manager;
    }
    private boolean isNetConnect;

    public boolean getNetConnect() {
        return isNetConnect;
    }

    public void RegisterConnect(NetConnectListener netConnectListener){
        list.add(netConnectListener);
    }

    public void UnRegisterConnect(NetConnectListener netConnectListener){
        list.remove(netConnectListener);
    }

    public void init(Context context){
        this.context=context;
        getCurrentContextStatus();
        initReceiver();
    }

    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver,intentFilter);
    }
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentContextStatus();
                for (NetConnectListener netConnectListener : list) {
                    if (isNetConnect){
                        netConnectListener.OnConnect();
                    }else {
                        netConnectListener.DisConnect();
                    }
                }
            }
        }
    };
    private void getCurrentContextStatus() {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null){
            isNetConnect=true;
        }else {
            isNetConnect=false;
        }

    }



    public interface NetConnectListener{
        void OnConnect();
        void DisConnect();
    }
}
