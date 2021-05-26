package com.example.shoppingmallsix.fragment.shoppingcar;

import com.example.framework.IBaseView;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.business.UpdateProductSelectedBean;

public interface IShopping extends IBaseView {
    void onSelectAllProductBean(SelectAllProductBean selectAllProductBean);
<<<<<<< HEAD:app/src/main/java/com/example/shoppingmallsix/fragment/shoppingcar/IShopping.java

    void onUpdateProductSelect(UpdateProductSelectedBean updateProductSelectedBean,int position);
=======
    void onOrderinfo(GetOrderInfoBean getOrderInfoBean);
    void onConfiemserverpayresult(ConfirmServerPayResultBean confirmServerPayResultBean);
>>>>>>> wqq0525:app/src/main/java/com/example/shoppingmallsix/fragment/shoppingcarfragment/IShopping.java
}
