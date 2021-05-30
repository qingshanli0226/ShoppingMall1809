package com.example.pay.payment;

import com.example.framework.IBaseView;
import com.example.net.bean.SelectBean;

public interface IPaymentView extends IBaseView {
    void onConfigPay(SelectBean selectBean);
}
