package com.shoppingmall.framework.mvp;

public interface IBaseView {
    void showLoading();
    void hideLoading();
    void showError(String msg);
}
