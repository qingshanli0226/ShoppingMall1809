package com.example.framework.manager;

import com.example.net.bean.user.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class CacheUserManager {
    private static CacheUserManager cacheUserManager;

    private CacheUserManager() {
    }

    public synchronized static CacheUserManager getInstance() {
        if (cacheUserManager == null) {
            cacheUserManager = new CacheUserManager();
        }
        return cacheUserManager;
    }


    private LoginBean loginBean;
    private List<ILoginChange> loginChangeList = new ArrayList<>();

    public void registerLogin(ILoginChange iLoginChange) {
        loginChangeList.add(iLoginChange);
    }

    public void unRegisterLogin(ILoginChange iLoginChange) {
        loginChangeList.remove(iLoginChange);
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        for (ILoginChange iLoginChange : loginChangeList) {
            iLoginChange.onLoginChange(loginBean);
        }
    }

    public interface ILoginChange {
        void onLoginChange(LoginBean loginBean);
    }
}
