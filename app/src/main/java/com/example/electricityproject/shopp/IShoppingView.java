package com.example.electricityproject.shopp;

import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.framework.IBaseView;

public
interface IShoppingView extends IBaseView {
    void postSelectAllProductData(SelectAllProductBean selectAllProductBean);
    void getShortProductData(ShortcartProductBean shortcartProductBean);

}
