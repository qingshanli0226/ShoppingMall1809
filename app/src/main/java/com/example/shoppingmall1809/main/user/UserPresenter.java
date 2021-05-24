package com.example.shoppingmall1809.main.user;

import android.widget.ImageView;

import com.example.framework.BasePresenter;

public class UserPresenter extends BasePresenter<IUserView> {
    public UserPresenter(IUserView iUserView) {
        attachView(iUserView);
    }



}
