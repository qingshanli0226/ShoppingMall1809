package com.example.pay.awaitpayment;

import com.example.framework.IBaseView;
import com.example.net.bean.AwaitPaymentBean;

public interface IAwaitPaymentView extends IBaseView {
    public void onAwaitPayment(AwaitPaymentBean paymentBean);

}
