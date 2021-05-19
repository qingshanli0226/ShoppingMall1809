package com.fiannce.user.register;

import com.fiannce.framework.IBaseView;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;

public interface IUserView extends IBaseView {

    public void onRegister(RegisterBean registerBean);

    public void onLogin(LoginBean loginBean);

    public void onAutoLogin(LoginBean loginBean);

}
