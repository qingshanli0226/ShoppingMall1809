package com.shoppingmall.welcome;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.ShopCarBean;

public interface IWelcomeView extends IBaseView {
    void onHomeData(HomeBean homeBean);

}
