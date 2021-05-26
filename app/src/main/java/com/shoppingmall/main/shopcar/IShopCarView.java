package com.shoppingmall.main.shopcar;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.CheckProductBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.ShopCarBean;

public interface IShopCarView extends IBaseView {
    void getShopCarData(ShopCarBean shopCarBean);
    void checkProductNum(CheckProductBean checkProductBean);
    void updateProduct(AddProductBean addProductBean);
}
