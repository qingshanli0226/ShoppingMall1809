package com.fiannce.bawei.framework.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FiannceConnectManager {

    private boolean isConnected;//当前应用的网络连接状态
    private Context context;

    private static FiannceConnectManager instance;

    private List<IConnectListener> connectListenerList = new LinkedList<>();

    private FiannceConnectManager(){

    }

    public static synchronized FiannceConnectManager getInstance() {
        if (instance==null) {
            instance = new FiannceConnectManager();
        }
        return instance;
    }


    public void init(Context applicatitonContext) {
        this.context = applicatitonContext;
        getCurrentConnectStatus();
        initReceiver();
    }

    public boolean isConnected() {
        return isConnected;
    }

    private void getCurrentConnectStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();//获取当前系统网络连接的信息
        if (networkInfo!=null && networkInfo.isConnected()) {//如果有连接
            isConnected = true;
        } else {
            isConnected = false;
        }
    }

    //因为网络连接的状态是变化的，所以我们要监听系统网络连接的变化。系统通过广播来通知网络连接的变化
    private void initReceiver() {

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(netConnectReceiver, intentFilter);
    }

    private BroadcastReceiver netConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果收到网络连接通知的广播，代表系统的网络连接发生了改变
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                 getCurrentConnectStatus();
                 //去通知各个页面去刷新UI
                notifyConnectChanged();
            }

        }
    };

    //回调各个页面注册的接口，通知网络的变化
    private void notifyConnectChanged() {
        for(IConnectListener listener:connectListenerList) {
            if (isConnected) {
                listener.onConnected();
            } else {
                listener.onDisconnected();
            }
        }
    }

    public synchronized void registerConnectListener(IConnectListener listener) {
        if (!connectListenerList.contains(listener)) {
            connectListenerList.add(listener);
        }
    }

    public synchronized void unRegisterConnectListener(IConnectListener listener) {
        if (connectListenerList.contains(listener)) {
            connectListenerList.remove(listener);
        }
    }

    public interface IConnectListener {
        void onConnected();
        void onDisconnected();
    }


}
