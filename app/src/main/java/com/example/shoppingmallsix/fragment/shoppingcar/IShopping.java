package com.example.shoppingmallsix.fragment.shoppingcar;

import com.example.framework.IBaseView;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.business.UpdateProductSelectedBean;

public interface IShopping extends IBaseView {
    void onSelectAllProductBean(SelectAllProductBean selectAllProductBean,boolean mBooleans);

    void onUpdateProductSelect(UpdateProductSelectedBean updateProductSelectedBean,int position);
}
