package com.example.framework.manager;

import com.example.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class CaCheLoginUserMannager {
    private static CaCheLoginUserMannager manager;

    private List<UserPhoneAndAddress> list=new ArrayList<>();

    public static CaCheLoginUserMannager getInstance() {
        if (manager == null) {
            manager = new CaCheLoginUserMannager();
        }
        return manager;
    }

    public void registerAddLoginUser(UserPhoneAndAddress andAddress){
        list.add(andAddress);
    }
    public void unRegisterAddLoginUser(UserPhoneAndAddress andAddress){
        list.remove(andAddress);
    }

    private LoginBean bean;

    public LoginBean getBean() {
        return bean;
    }

    public void setBean(LoginBean bean) {
        this.bean = bean;
    }

    //修改地址
    public void setUserPhoneOrAddress(){
        LoginBean bean = getBean();
        for (UserPhoneAndAddress a :
                list) {
            a.setPhoneOrAddress(bean);
        }
    }
    public interface UserPhoneAndAddress{
        void setPhoneOrAddress(LoginBean loginBean);
    }
}
