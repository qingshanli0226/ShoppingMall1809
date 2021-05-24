package com.example.manager;

import com.example.common.bean.ShortcartProductBean;

public class BusinessBuyCarManger {

    private static BusinessBuyCarManger businessBuyCarManger;
    private ShortcartProductBean shortcartProductBean;

    public BusinessBuyCarManger() {

    }

    public static BusinessBuyCarManger getInstance() {
        if (businessBuyCarManger==null){
            businessBuyCarManger = new BusinessBuyCarManger();
        }
        return businessBuyCarManger;
    }

    public ShortcartProductBean getShortcartProductBean() {
        return shortcartProductBean;
    }

    public void setShortcartProductBean(ShortcartProductBean shortcartProductBean) {
        this.shortcartProductBean = shortcartProductBean;
    }
}
