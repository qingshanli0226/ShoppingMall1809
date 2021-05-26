package com.example.shoppingmallsix.fragment.shoppingcarfragment;

import com.example.framework.IBaseView;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.SelectAllProductBean;

public interface IShopping extends IBaseView {
    void onShopping(GetShortcartProductsBean shoppingCarBean);
    void onSelectAllProductBean(SelectAllProductBean selectAllProductBean);
    void onOrderinfo(GetOrderInfoBean getOrderInfoBean);
    void onConfiemserverpayresult(ConfirmServerPayResultBean confirmServerPayResultBean);
}
