package com.example.user.phoneaddress;

import com.example.net.bean.UpdateAddress;
import com.example.net.bean.UpdatePhone;

import mvp.view.IBaseVIew;

public interface IPhoneAndAddressView extends IBaseVIew {
    void onBindphone(UpdatePhone updatePhone);
    void onBindAddress(UpdateAddress updateAddress);
}
