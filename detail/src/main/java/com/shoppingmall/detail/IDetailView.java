package com.shoppingmall.detail;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.ProductBean;

public interface IDetailView extends IBaseView {
    void addProduct(AddProductBean addProductBean);
}
