package com.example.user.register;

import com.example.framework.IBaseView;
import com.example.net.bean.user.LoginBean;
import com.example.net.bean.user.RegisterBean;

public interface IUserView extends IBaseView {

    public void onRegister(RegisterBean registerBean);

    public void onLogin(LoginBean loginBean);

    public void onAutoLogin(LoginBean loginBean);

}
