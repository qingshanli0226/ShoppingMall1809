package com.example.user.login;

import com.example.framework.IBaseView;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;

public interface ILoginView extends IBaseView {
    public void onLoginData(LoginBean loginBean);
}
