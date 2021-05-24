package com.example.electricityproject.shopp;

import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.framework.IBaseView;

public
interface IShoppingView extends IBaseView {
    void getShortProductData(ShortcartProductBean shortcartProductBean);
    void postSelectAllProductData(SelectAllProductBean selectAllProductBean);

}
