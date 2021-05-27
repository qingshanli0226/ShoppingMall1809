package com.example.framework.manager;

import com.example.net.model.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class ShopeUserManager {

    private List<IUserLoginChanged> iUserLoginChangedList = new ArrayList<>();

    private LoginBean loginBean;

    public interface IUserLoginChanged {
        void onLoginChange(LoginBean loginBean);
    }

    public synchronized LoginBean getLoginBean() {
        return loginBean;
    }

    public synchronized void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        for (IUserLoginChanged iUserLoginChanged : iUserLoginChangedList) {
            iUserLoginChanged.onLoginChange(loginBean);
        }
    }

    public void register(IUserLoginChanged iUserLoginChanged){
        new Throwable().printStackTrace();
        iUserLoginChangedList.add(iUserLoginChanged);
    }


    public void unregister(IUserLoginChanged iUserLoginChanged){
        iUserLoginChangedList.remove(iUserLoginChanged);
    }


    private static ShopeUserManager shopeUserManager;

    public static ShopeUserManager getInstance() {
        if (shopeUserManager == null) {
            shopeUserManager = new ShopeUserManager();
        }
        return shopeUserManager;
    }
}
