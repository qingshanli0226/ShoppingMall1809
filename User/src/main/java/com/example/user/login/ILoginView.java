package com.example.user.login;

import com.example.common.bean.LogBean;
import com.example.framework.IBaseView;

public
interface ILoginView extends IBaseView {
    void onLoginData(LogBean logBean);
}
