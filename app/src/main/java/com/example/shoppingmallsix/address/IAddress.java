package com.example.shoppingmallsix.address;

import com.example.framework.IBaseView;
import com.example.net.bean.user.UpdateAddressBean;
import com.example.net.bean.user.UpdatePhoneBean;

public interface IAddress extends IBaseView {
    void onUpDataPhone(UpdatePhoneBean updatePhoneBean);

    void onUpDataAddress(UpdateAddressBean updateAddressBean);
}

