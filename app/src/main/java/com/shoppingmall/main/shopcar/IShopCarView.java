package com.shoppingmall.main.shopcar;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.CheckProductBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.ShopCarBean;

import java.util.List;

public interface IShopCarView extends IBaseView {
    void onCheck(int position,boolean isCheck);
    void onCheckAll(boolean isChcekAll);
    void onNum(int position);
    //删除一个
    void removeProduct(int position);

    //删除多个
    void removeMany(List<ShopCarBean.ResultBean> resultBeans);
}
