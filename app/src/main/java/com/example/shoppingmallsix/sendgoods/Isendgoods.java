package com.example.shoppingmallsix.sendgoods;

import com.example.framework.IBaseView;
import com.example.net.bean.find.FindForPayBean;
import com.example.net.bean.find.FindForSendbean;

public interface Isendgoods extends IBaseView {

    void onfindForSend(FindForSendbean findForSendbean);

}
