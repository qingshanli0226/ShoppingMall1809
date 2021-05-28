package com.example.shoppingmallsix.fragment.shoppingcar;

import com.example.framework.IBaseView;
import com.example.net.bean.business.CheckOneInventoryBean;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.RemoveManyProductBean;
import com.example.net.bean.business.RemoveOneProductBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.business.UpdateProductNumBean;
import com.example.net.bean.business.UpdateProductSelectedBean;

public interface IShopping extends IBaseView {

    void onOrderinfo(GetOrderInfoBean getOrderInfoBean);

    void onConfiemserverpayresult(ConfirmServerPayResultBean confirmServerPayResultBean);
<<<<<<< HEAD

    void onSelectAllProductBean(SelectAllProductBean selectAllProductBean, boolean mBooleans);

    void onUpdateProductSelect(UpdateProductSelectedBean updateProductSelectedBean, int position);

    void onRemoveManyProductBean(RemoveManyProductBean removeManyProductBean);

    void onCheckInventory(CheckOneInventoryBean bean, int position);

    void onUpdateProductNum(UpdateProductNumBean updateProductNumBean, int position, boolean mBoolean);

=======
    void onSelectAllProductBean(SelectAllProductBean selectAllProductBean,boolean mBooleans);
    void onUpdateProductSelect(UpdateProductSelectedBean updateProductSelectedBean,int position);
    void onRemoveManyProductBean(RemoveManyProductBean removeManyProductBean);
    void onRemoveOneProductBean(RemoveOneProductBean removeOneProductBean,int position);
    void onCheckInventory(CheckOneInventoryBean bean,int position);
    void onUpdateProductNum(UpdateProductNumBean updateProductNumBean,int position,boolean mBoolean);
>>>>>>> 0527ss
}
