package com.example.framework.manager;

import com.example.net.bean.LoginBean;

public class CaCheLoginUserMannager {
    private static CaCheLoginUserMannager manager;

    public static CaCheLoginUserMannager getInstance() {
        if (manager == null) {
            manager = new CaCheLoginUserMannager();
        }
        return manager;
    }

    private LoginBean bean;

    public LoginBean getBean() {
        return bean;
    }

    public void setBean(LoginBean bean) {
        this.bean = bean;
    }
}
