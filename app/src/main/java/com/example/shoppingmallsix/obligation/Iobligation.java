package com.example.shoppingmallsix.obligation;

import com.example.framework.IBaseView;
import com.example.net.bean.find.FindForPayBean;
import com.example.net.bean.find.FindForSendbean;

public interface Iobligation extends IBaseView {


    void onfindForpay(FindForPayBean findForPayBean);
}
