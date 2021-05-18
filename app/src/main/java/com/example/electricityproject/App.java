package com.example.electricityproject;

import android.app.Application;

import com.example.net.NetModel;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetModel.init(this);
    }
}
