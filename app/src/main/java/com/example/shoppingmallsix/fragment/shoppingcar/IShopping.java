package com.example.shoppingmallsix.fragment.shoppingcar;

import com.example.framework.IBaseView;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.RemoveManyProductBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.business.UpdateProductSelectedBean;

public interface IShopping extends IBaseView {
<<<<<<< HEAD
    void onOrderinfo(GetOrderInfoBean getOrderInfoBean);
    void onConfiemserverpayresult(ConfirmServerPayResultBean confirmServerPayResultBean);
    void onSelectAllProductBean(SelectAllProductBean selectAllProductBean,boolean mBooleans);
    void onUpdateProductSelect(UpdateProductSelectedBean updateProductSelectedBean,int position);
    void onRemoveManyProductBean(RemoveManyProductBean removeManyProductBean);

=======
    void onSelectAllProductBean(SelectAllProductBean selectAllProductBean);

    void onUpdateProductSelect(UpdateProductSelectedBean updateProductSelectedBean,int position);

    void onOrderinfo(GetOrderInfoBean getOrderInfoBean);
    void onConfiemserverpayresult(ConfirmServerPayResultBean confirmServerPayResultBean);

    void onShopping(GetShortcartProductsBean shoppingCarBean);
>>>>>>> wqq0526
}
