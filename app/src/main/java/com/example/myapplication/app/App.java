package com.example.myapplication.app;

import android.app.Application;
import android.content.Intent;

import com.example.UserModule;
import com.example.framework.manager.CaCheArote;
import com.example.myapplication.AppModule;
<<<<<<< HEAD
import com.example.myapplication.particulars.ParticularsModule;
=======
import com.example.user.AutoService;
>>>>>>> zyh


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UserModule.init();
        AppModule.init();
        ParticularsModule.init();
        CaCheArote.getInstance().init(this);

        Intent intent = new Intent(this, AutoService.class);
        startService(intent);
    }
}
