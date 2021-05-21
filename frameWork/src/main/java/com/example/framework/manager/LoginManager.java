package com.example.framework.manager;

import com.example.net.bean.LoginBean;

public class LoginManager {

    private static LoginManager loginManager;

    private LoginManager(){

    }

    public static LoginManager getInstance(){
        if (loginManager ==  null){
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    private Boolean loginstate;
    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Boolean getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(Boolean loginstate) {
        this.loginstate = loginstate;
    }
}
