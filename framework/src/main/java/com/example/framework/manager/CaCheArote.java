package com.example.framework.manager;

import android.content.Context;
import android.os.Bundle;

public class CaCheArote {
    private static CaCheArote arote;

    public CaCheArote() {
    }

    public static synchronized CaCheArote getInstance() {
        if (arote == null) {
            arote = new CaCheArote();
        }
        return arote;
    }

    private IUserInterface iUserInterface;
//    private IGestureInterface iGestureInterface;
//    private IPayInterface iPayInterface;
    private IAppInterface iAppInterface;
    private IParticularsInterface iParticularsInterface;
    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    //注册user模块的回调借口
    public void registerIUserInterface(IUserInterface iUserInterface) {
        this.iUserInterface = iUserInterface;
    }
    //回去User模块的接口
    public IUserInterface getUserInterface() {
        return iUserInterface;
    }

    public void registerIParticularsInterface(IParticularsInterface iParticularsInterface) {
        this.iParticularsInterface = iParticularsInterface;
    }
    public IParticularsInterface getParticularsInterface() {
        return iParticularsInterface;
    }
//
//    //注册手势模块的回调借口
//    public void registerIGestureInterface(IGestureInterface iGesturerInterface) {
//        this.iGestureInterface = iGesturerInterface;
//    }
//    //回去手势模块的接口
//    public IGestureInterface getGestureInterface() {
//        return iGestureInterface;
//    }
//
//    //注册支付页面回调接口
//    public void registerIPayInterface(IPayInterface iPayInterface) {
//        this.iPayInterface = iPayInterface;
//    }
//    public IPayInterface getPayInterface() {
//        return iPayInterface;
//    }
//
    public void registerIAppInterface(IAppInterface iAppInterface) {
        this.iAppInterface = iAppInterface;
    }
    public IAppInterface getAppInterface() {
        return iAppInterface;
    }


    ////在框架里约定好熬User要实现的接口，其他页面可以调用此接口
    public interface IUserInterface {
        void openLoginActivity(Context context, Bundle bundle);
        void openGettureActivity(Context context, Bundle bundle);
//        void openUserMessageActivity(Context context, Bundle bundle);
    }
//
//    ////在框架里约定好手势要实现的接口，其他页面可以调用此接口
//    public interface IGestureInterface {
//        void openGestureActivity(Context context, Bundle bundle);
//    }
//
//    //定义支付模块实现接口
//    public interface IPayInterface {
//        void openPayActivity(Context context, Bundle bundle);
//    }

    public interface IAppInterface {
        void openMainActivity(Context context, Bundle bundle);
        void onEvent(String event);
    }
    public interface IParticularsInterface {
        void openParticularsctivity(Context context, Bundle bundle);
    }


}
