package com.example.electricityproject.shopp.orderdetails;

import com.example.common.bean.ConfirmServerPayResultBean;
import com.example.framework.IBaseView;

public interface OrderDetailsActivityIView extends IBaseView {

    void onConfirmServerPayResultOk(ConfirmServerPayResultBean bean);


}
