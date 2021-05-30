package com.example.pay;

import com.example.framework.IBaseView;
import com.example.net.model.RegisterBean;

public interface IPayView extends IBaseView {
    void getConfirmServerPayResult(RegisterBean registerBean);
}
