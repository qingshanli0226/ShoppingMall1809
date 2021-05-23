package com.shoppingmall.framework.manager;



import com.shoppingmall.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class ShopMallUserManager {

    private List<IUserLoginChanged> list=new ArrayList<>();

    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        for (IUserLoginChanged iUserLoginChanged : list) {
            iUserLoginChanged.onLoginChanged(loginBean);
        }
    }

    public void registerUserLoginChanged(IUserLoginChanged iUserLoginChanged){
        list.add(iUserLoginChanged);
    }

    public void unregisterUserLoginChanged(IUserLoginChanged iUserLoginChanged){
        list.remove(iUserLoginChanged);
    }

    public interface IUserLoginChanged{
        void onLoginChanged(LoginBean loginBean);
    }

    private static ShopMallUserManager shopMallUserManager;

    public synchronized static ShopMallUserManager getInstance() {
        if (shopMallUserManager==null){
            shopMallUserManager=new ShopMallUserManager();
        }
        return shopMallUserManager;
    }
}
