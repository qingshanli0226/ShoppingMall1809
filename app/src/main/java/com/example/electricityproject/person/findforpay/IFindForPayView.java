package com.example.electricityproject.person.findforpay;

import com.example.common.bean.FindForPayBean;
import com.example.framework.IBaseView;

public
interface IFindForPayView extends IBaseView {
    void getFindForPayData(FindForPayBean findForPayBean);

}
