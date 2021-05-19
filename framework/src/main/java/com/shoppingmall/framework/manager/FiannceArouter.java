package com.shoppingmall.framework.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

public class FiannceArouter {

    public Class<?> dispalyActivityClass;
    private Context context;

    private IUserInterface iUserInterface;
    private IPayInterface iPayInterface;
    private IAppInterface iAppInterface;

    private HashMap<String,Class<?>> pathMap=new HashMap<>();

    private static FiannceArouter fiannceArouter;

    public static synchronized FiannceArouter getInstance() {
        if (fiannceArouter==null){
            fiannceArouter=new FiannceArouter();
        }
        return fiannceArouter;
    }

    public void init(Context context){
        this.context=context;
    }

    public void registerIUserInterface(IUserInterface iUserInterface){
        this.iUserInterface=iUserInterface;
    }

    public void registerIPayInterface(IPayInterface iPayInterface){
        this.iPayInterface=iPayInterface;
    }
    public void registerIAppInterface(IAppInterface iAppInterface){
        this.iAppInterface=iAppInterface;
    }

    public IUserInterface getIUserInterface() {
        return iUserInterface;
    }

    public IPayInterface getIPayInterface() {
        return iPayInterface;
    }

    public IAppInterface getIAppInterface() {
        return iAppInterface;
    }

    public interface IUserInterface{
        void openLoginActivity(Context context, Bundle bundle);
        void openRegisterActivity(Context context, Bundle bundle);
    }

    public interface IPayInterface{
        void openPayActivity(Context context, Bundle bundle);
    }

    public interface IAppInterface{
        void openMainActivity(Context context, Bundle bundle);
    }

    public void registerActivityPath(String path , Class<?> clazz){
        if (!pathMap.containsValue(path)){
            pathMap.put(path,clazz);
        }
    }
    public FiannceArouter build(String path){
        dispalyActivityClass=pathMap.get(path);
        return this;
    }
    public void navigation(){
        Intent intent = new Intent(context,dispalyActivityClass);
        if (context instanceof Activity){
            context.startActivity(intent);
        } {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}