package com.example.user.register;

import com.example.framework.IBaseView;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;

public interface IUserView extends IBaseView {
    public void onRegister(RegisterBean registerBean);
    public void onLogin(LoginBean loginBean);
    public void onAutoLogin(LoginBean loginBean);

}
