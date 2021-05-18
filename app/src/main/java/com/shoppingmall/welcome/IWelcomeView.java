package com.shoppingmall.welcome;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.HomeBean;

public interface IWelcomeView extends IBaseView {
    void onHomeData(HomeBean homeBean);
}
