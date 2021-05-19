package com.example.framework.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class ArouterManager {
    public static ArouterManager arouterManager;
    private IArouterInterface iArouterInterface;
    public ArouterManager() {
    }

    public synchronized static ArouterManager getInstance() {
        if (arouterManager==null){
            arouterManager=new ArouterManager();
        }
        return arouterManager;
    }
    public void registerArouterInterface(IArouterInterface iArouterInterface){
        this.iArouterInterface=iArouterInterface;
    }

    public IArouterInterface getIarouterInterface(){
        return iArouterInterface;
    }
    public interface IArouterInterface{
        void onGologin(Context context, Bundle bundle);
        void onGoregister(Context context,Bundle bundle);

    }
    private Map<String,Class<?>> map=new HashMap<>();
    private Class<?> displayActivity;
    private Context context;
    private Bundle bundle=null;
    private Intent intent=null;
    public void init(Context context){
        this.context=context;
    }
    public void registerpath(String path,Class<?>clazzz){
        if (!map.containsKey(path)){
            map.put(path,clazzz);
        }
    }
    public ArouterManager build(String path){
        displayActivity=map.get(path);
        return this;
    }
    public ArouterManager with(Bundle bundle){
        this.bundle=bundle;
        return this;
    }
    public void navigation(){
        intent=new Intent(context,displayActivity);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else {
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public Intent getIntent() {
        return intent;
    }
}
