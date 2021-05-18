package com.example.framework.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class FiannceConnectManager {

    private boolean isConnected;
    private Context context;

    private List<IConnectListener> connectListenerList=new LinkedList<>();

    private static FiannceConnectManager fiannceConnectManager;

    public synchronized static FiannceConnectManager getInstance() {
        if (fiannceConnectManager==null){
            fiannceConnectManager=new FiannceConnectManager();
        }
        return fiannceConnectManager;
    }

    public void init(Context context){
        this.context=context;
        getCurrentConnectStatus();
        initRReceiver();
    }

    public boolean isConnected() {
        return isConnected;
    }

    private void getCurrentConnectStatus(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            isConnected=true;
        }else {
            isConnected=false;
        }
    }

    private void initRReceiver(){
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(netConnectReceiver,intentFilter);
    }

    private BroadcastReceiver netConnectReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentConnectStatus();

                notifyConnectChanged();
            }
        }
    };

    private void notifyConnectChanged(){
        for (IConnectListener iConnectListener : connectListenerList) {
            if (isConnected){
                iConnectListener.onConnected();
            }else {
                iConnectListener.onDisconnected();
            }
        }
    }

    public synchronized void registerConnectListener(IConnectListener iConnectListener){
        if (!connectListenerList.contains(iConnectListener)){
            connectListenerList.add(iConnectListener);
        }
    }

    public synchronized void unRegisterConnectListener(IConnectListener iConnectListener){
        if (connectListenerList.contains(iConnectListener)){
            connectListenerList.add(iConnectListener);
        }
    }

    public interface IConnectListener{
        void onConnected();
        void onDisconnected();
    }

}
