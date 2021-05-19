package com.example.framework.manager;

import com.example.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class CacheUserManager {
    public static CacheUserManager cacheUserManager;

    public synchronized static CacheUserManager getInstance() {
        if (cacheUserManager==null){
            cacheUserManager=new CacheUserManager();
        }
        return cacheUserManager;
    }
    private LoginBean loginBean;
    private List<IloginChange> list=new ArrayList<>();
    public void registerLogin(IloginChange iloginChange){
        list.add(iloginChange);
    }
    public void unregisterLogin(IloginChange iloginChange){
        list.remove(iloginChange);
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean){
        this.loginBean=loginBean;
        for (IloginChange iloginChange:list) {
            iloginChange.onLoginChange(loginBean);
        }
    }
    public interface IloginChange{
        void onLoginChange(LoginBean loginBean);
    }
}
