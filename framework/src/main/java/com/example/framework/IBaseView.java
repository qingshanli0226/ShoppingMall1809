package com.example.framework;

import android.view.View;

public interface IBaseView {
    void showLoading();
    void hideLoading();
    void showError(String error);
    <T extends View> T findViewById(int resId);
}
