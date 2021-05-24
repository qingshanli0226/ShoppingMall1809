package com.example.shoppingmallsix.fragment.shoppingcarfragment;

import com.example.framework.IBaseView;
import com.example.net.bean.business.GetShortcartProductsBean;

public interface IShopping extends IBaseView {
    void onShopping(GetShortcartProductsBean shoppingCarBean);
}
