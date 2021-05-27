package com.shoppingmall.detail;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.SelectBean;

public interface IDetailView extends IBaseView {
    void addProduct(SelectBean selectBean);
    void checkProduct(SelectBean selectBean);
}
