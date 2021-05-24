package com.example.shoppingcar;

import com.example.framework.IBaseView;
import com.example.net.bean.RegisterBean;

public interface IAddOneProduct extends IBaseView {
    void onAddOneProduct(RegisterBean registerBean);
}
