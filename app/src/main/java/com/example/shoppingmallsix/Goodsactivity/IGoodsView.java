package com.example.shoppingmallsix.Goodsactivity;

import com.example.framework.IBaseView;
import com.example.net.bean.ProductBean;

public interface IGoodsView extends IBaseView {
    void onAddCart(ProductBean productBean);
}
