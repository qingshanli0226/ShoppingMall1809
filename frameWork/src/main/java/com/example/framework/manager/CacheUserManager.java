package com.example.framework.manager;

import com.example.net.bean.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class CacheUserManager {

    private static CacheUserManager userManager;

    private CacheUserManager(){

    }

    public synchronized static CacheUserManager getInstance(){
        if (userManager ==  null){
            userManager = new CacheUserManager();
        }
        return userManager;
    }



    private LoginBean loginBean;
    private List<IUserChange> userChangeslist = new LinkedList<>();
    public void registerLogin(IUserChange iUserChange){
        userChangeslist.add(iUserChange);
    }

    public void unregisterLogin(IUserChange iUserChange){
        userChangeslist.remove(iUserChange);
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        for (IUserChange userChange : userChangeslist) {
            userChange.onUserChange(loginBean);
        }
    }

    public interface IUserChange{
        void onUserChange(LoginBean loginBean);
    }




}
