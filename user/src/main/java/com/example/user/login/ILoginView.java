package com.example.user.login;

import com.example.framework.IBaseView;
import com.example.net.bean.LoginBean;

public interface ILoginView extends IBaseView {
    public void onLogin(LoginBean loginBean);
}
