package com.example.threeshopping.setting;

import com.example.framework.IBaseView;

import com.example.net.bean.LogoutBean;

public interface ISettingView  extends IBaseView {
    void onLogout(LogoutBean logoutBean);
}
