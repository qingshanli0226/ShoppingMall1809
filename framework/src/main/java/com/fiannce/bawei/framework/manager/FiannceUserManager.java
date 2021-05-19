package com.fiannce.bawei.framework.manager;

import java.util.LinkedList;
import java.util.List;

public class FiannceUserManager {

    //维护一个接口列表。因为多个页面都监听登录状态
    private List<IUserLoginChanged> iUserLoginChangedList = new LinkedList<>();

    private static FiannceUserManager instance;
    private FiannceUserManager(){}

    public static synchronized FiannceUserManager getInstance(){
        if (instance==null){
            instance=new FiannceUserManager();
        }
        return instance;
    }

    private boolean isLogin;

    //获取当前用户的登录状态
    public boolean isLogin() {
        return isLogin;
    }

    public void register(IUserLoginChanged iUserLoginChanged) {
        iUserLoginChangedList.add(iUserLoginChanged);
    }
    public void unRegister(IUserLoginChanged iUserLoginChanged) {
        iUserLoginChangedList.remove(iUserLoginChanged);
    }

    //当登录成功后，或者退出登录时，登录状态发生改变，修改单例里的登录状态
    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
        //通知各个页面，当前登录状态发生了改变通知各个页面登录状态发生了改变
        for(IUserLoginChanged listenr:iUserLoginChangedList) {
            listenr.onLoginChange(isLogin);
        }
    }


    public interface IUserLoginChanged{
        void onLoginChange(boolean isLogin);
    }



}
