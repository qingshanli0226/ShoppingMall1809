package com.example.user.login;

import com.example.framework.IBaseView;
import com.example.net.bean.user.LoginBean;

public interface ILoginView extends IBaseView {

    void onLoginData(LoginBean loginBean);

}
