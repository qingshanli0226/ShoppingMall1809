package com.example.user.register;

import com.example.common.bean.LogBean;
import com.example.common.bean.RegBean;
import com.example.framework.IBaseView;

public
interface IRegisterView extends IBaseView {

    void onRegisterData(RegBean regBean);

}
