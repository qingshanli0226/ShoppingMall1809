package com.example.framework.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class CacheConnectManager {
    private  static CacheConnectManager cacheConnectManager;

    private   CacheConnectManager() {

    }

    public synchronized static CacheConnectManager getInstance(){
        if (cacheConnectManager == null){
            cacheConnectManager = new CacheConnectManager();
        }
        return cacheConnectManager;
    }

    private boolean isConnect;
    private Context context;
    private List<IConnect> list = new LinkedList<>();

    public boolean isConnect() {
        return isConnect;
    }

    public void init(Context applicationContext){
        this.context = applicationContext;
        getCurrentConnectStatus();
        initReceiver();
    }

    public synchronized void getCurrentConnectStatus(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();//获取当前系统网络连接的信息
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            isConnect = true;
        }else {
            isConnect = false;
        }
    }

    public void  initReceiver(){
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver,intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentConnectStatus();
                notifyConnectChange();
            }
        }
    };

    public synchronized void  notifyConnectChange(){
        for (IConnect iConnect :list){
            if (isConnect){
                iConnect.onConect();
            }else {
                iConnect.onDisConnect();
            }
        }
    }

    public synchronized void registerConnectListener(IConnect iConnect){
            list.add(iConnect);
    }

    public synchronized void unregisterConnectListener(IConnect iConnect){
            list.remove(iConnect);
    }

    public interface IConnect{
        void onConect();
        void onDisConnect();
    }

}

