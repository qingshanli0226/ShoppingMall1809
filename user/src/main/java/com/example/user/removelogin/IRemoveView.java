package com.example.user.removelogin;


import com.example.framework.IBaseView;
import com.example.net.model.RegisterBean;

public interface IRemoveView extends IBaseView {

    void onUserData(RegisterBean unlockBean);

}
