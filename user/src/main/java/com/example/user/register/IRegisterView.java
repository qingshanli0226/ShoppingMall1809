package com.example.user.register;

import com.example.framework.IBaseView;
import com.example.net.model.RegisterBean;

public interface IRegisterView extends IBaseView {
    public void onRegisterData(RegisterBean registerBean);
}
