package com.fiance.user.registers;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.RegisterBean;

public interface IRegisterView extends IBaseView {
    void onRegisterData(RegisterBean registerBean);
}
