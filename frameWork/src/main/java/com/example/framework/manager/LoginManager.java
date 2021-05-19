package com.example.framework.manager;

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

    public Boolean getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(Boolean loginstate) {
        this.loginstate = loginstate;
    }
}
