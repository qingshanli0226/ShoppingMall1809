package com.example.framework.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

public class CacheConnectManager {

    public static CacheConnectManager cacheconnectManager;

    public CacheConnectManager() {
    }

    public static CacheConnectManager getInstance() {
        if (cacheconnectManager==null){
            cacheconnectManager=new CacheConnectManager();
        }
        return cacheconnectManager;
    }
    private boolean isConnect;
    private Context context;
    private List<IConnect> list=new ArrayList<>();

    public boolean isConnect() {
        return isConnect;
    }
    public void init(Context context){
        this.context=context;
        getCurrentConnectStatus();
        initReceiver();
    }
    public void initReceiver(){
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver,intentFilter);
    }

    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentConnectStatus();
                notifyConnectChange();
            }
        }
    };

    private void getCurrentConnectStatus() {
        ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()){
            isConnect=true;
        }else {
            isConnect=false;
        }
    }
    private void notifyConnectChange(){
        for (IConnect iConnect:list){
            if (isConnect){
                iConnect.onConnect();
            }else {
                iConnect.onDisConnect();
            }
        }
    }

    public void registerConncectListener(IConnect iConnect){
        if (!list.contains(iConnect)){
            list.add(iConnect);
        }

    }
    public void unregisterConncectListener(IConnect iConnect){
        if (list.contains(iConnect)){
            list.remove(iConnect);
        }
    }


    public interface IConnect{
        void onConnect();
        void onDisConnect();
    }
}
