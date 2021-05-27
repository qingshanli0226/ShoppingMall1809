package com.example.pay.payment;

import com.example.framework.IBaseView;
import com.example.net.bean.LoginBean;
import com.example.net.bean.PaymentBean;
import com.example.net.bean.RegisterBean;

public interface IPaymentView extends IBaseView {
    public void onPayment(PaymentBean paymentBean);

}
