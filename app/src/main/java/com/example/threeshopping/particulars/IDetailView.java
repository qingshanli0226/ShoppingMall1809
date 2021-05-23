package com.example.threeshopping.particulars;

import com.example.framework.IBaseView;
import com.example.net.bean.ProductBean;

public interface IDetailView extends IBaseView {
    void onAddCart(ProductBean productBean);
}
