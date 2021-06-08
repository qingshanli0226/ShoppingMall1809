package com.example.electricityproject.welcome;

import com.example.common.bean.ShortcartProductBean;
import com.example.framework.IBaseView;

public
interface IWelcomeView extends IBaseView {
    void getShortProductData(ShortcartProductBean shortcartProductBean);
}
