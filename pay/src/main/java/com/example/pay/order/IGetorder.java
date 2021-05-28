package com.example.pay.order;

import com.example.framework.IBaseView;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.find.FindForPayBean;
import com.example.net.bean.find.FindForSendbean;

public interface IGetorder extends IBaseView {

    void onOrderinfo(GetOrderInfoBean getOrderInfoBean);
    void onfindForSend(FindForSendbean findForSendbean);
    void onfindForPay(FindForPayBean findForPayBean);
    void onConfirmServerPayResult(ConfirmServerPayResultBean confirmServerPayResultBean);

}
