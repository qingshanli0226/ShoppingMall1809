package com.fiance.user.logins;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.LoginBean;

public interface LoginView extends IBaseView {
    void onLoginData(LoginBean loginBean);
}
