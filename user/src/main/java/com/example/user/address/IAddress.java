package com.example.user.address;

import com.example.framework.IBaseView;
import com.example.net.model.RegisterBean;

public interface IAddress extends IBaseView {
    void onUpDataPhone(RegisterBean registerBean);

    void onUpDataAddress(RegisterBean registerBean);
}
