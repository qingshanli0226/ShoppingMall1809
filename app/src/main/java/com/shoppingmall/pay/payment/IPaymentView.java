package com.shoppingmall.pay.payment;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.SelectBean;

public interface IPaymentView extends IBaseView {
    void onConfigPay(SelectBean selectBean);
}
