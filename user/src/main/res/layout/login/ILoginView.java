package com.fiannce.user.login;

import com.fiannce.framework.IBaseView;
import com.fiannce.net.mode.LoginBean;

public interface ILoginView extends IBaseView {

    void onLoginData(LoginBean loginBean);

}
