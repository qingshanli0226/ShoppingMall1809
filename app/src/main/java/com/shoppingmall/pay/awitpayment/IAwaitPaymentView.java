package com.shoppingmall.pay.awitpayment;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.FindForPayBean;

public interface IAwaitPaymentView extends IBaseView {
    public void onAwaitPayment(FindForPayBean findForPayBean);

}
