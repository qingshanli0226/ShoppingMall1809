package com.example.framework.manager;

import com.example.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManager {

    private static UserManager userManager;

    private UserManager(){

    }

    public synchronized static UserManager getInstance(){
        if (userManager ==  null){
            userManager = new UserManager();
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
