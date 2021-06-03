package com.example.electricityproject.shopp.userinfo;

import com.example.common.bean.UpdateAddress;
import com.example.common.bean.UpdatePhoneBean;
import com.example.framework.IBaseView;

public
interface IBindUserInfoView extends IBaseView {

    void updatePhone(UpdatePhoneBean updatePhoneBean);
    void updateAddress(UpdateAddress updateAddress);
}
