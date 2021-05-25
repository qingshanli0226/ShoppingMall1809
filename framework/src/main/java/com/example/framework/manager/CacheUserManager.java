package com.example.framework.manager;

import com.example.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class CacheUserManager {

    public static CacheUserManager cacheUserManager;

    public synchronized static CacheUserManager getInstance() {
        if (cacheUserManager == null) {
            cacheUserManager = new CacheUserManager();
        }
        return cacheUserManager;
    }

    private boolean isLogin;//登陆状态
    private List<IloginChange> list = new ArrayList<>();

    public void registerLogin(IloginChange iloginChange) {
        if (!list.contains(iloginChange)){
            list.add(iloginChange);
        }
    }

    public void unregisterLogin(IloginChange iloginChange) {
        if (list.contains(iloginChange)){
            list.remove(iloginChange);
        }
    }

    //获取当前登录状态
    public boolean getIsLogin() {
        return isLogin;
    }

    public void setLoginBean(boolean loginBean) {
        this.isLogin = loginBean;
        for (IloginChange iloginChange : list) {
            iloginChange.onLoginChange(isLogin);
        }
    }

    public interface IloginChange {
        void onLoginChange(boolean loginBean);
    }
}
