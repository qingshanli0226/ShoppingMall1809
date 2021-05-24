package com.example.user.user;

import com.example.framework.IBaseView;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;

public interface IUserView extends IBaseView {
    public void onLogin(LoginBean loginBean);
    public void onRegister(RegisterBean registerBean);
}
