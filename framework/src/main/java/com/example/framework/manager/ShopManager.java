package com.example.framework.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class ShopManager {

    private Context context;
    private boolean isConnected;
    private List<IConnectListener> connectListeners = new LinkedList<>();

    public void init(Context context) {
        this.context = context;

        getCurrentConnectStatus();
        initReceiver();
    }

    private void getCurrentConnectStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            isConnected = true;
        } else {
            isConnected = false;
        }

    }


    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver, intentFilter);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                getCurrentConnectStatus();
                notifyConnectChanged();
            }
        }


    };

    private void notifyConnectChanged() {
        for (IConnectListener connectListener : connectListeners) {
            if (isConnected) {
                connectListener.onConnected();
            } else {
                connectListener.onDisconnected();
            }
        }
    }

    public interface IConnectListener {
        void onConnected();
        void onDisconnected();
    }


    public synchronized void registerConnectListener(IConnectListener iConnectListener) {
        if (!connectListeners.contains(iConnectListener)) {
            connectListeners.add(iConnectListener);
        }
    }


    public synchronized void unregisterConnectListener(IConnectListener iConnectListener) {
        if (connectListeners.contains(iConnectListener)) {
            connectListeners.remove(iConnectListener);
        }
    }

    private static ShopManager shopManager;

    public static synchronized ShopManager getInstance() {
        if (shopManager == null) {
            shopManager = new ShopManager();
        }
        return shopManager;
    }
}
