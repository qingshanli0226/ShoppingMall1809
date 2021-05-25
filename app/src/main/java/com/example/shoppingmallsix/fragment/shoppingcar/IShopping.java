package com.example.shoppingmallsix.fragment.shoppingcar;

import com.example.framework.IBaseView;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.SelectAllProductBean;

public interface IShopping extends IBaseView {
    void onShopping(GetShortcartProductsBean shoppingCarBean);
    void onSelectAllProductBean(SelectAllProductBean selectAllProductBean);
}
