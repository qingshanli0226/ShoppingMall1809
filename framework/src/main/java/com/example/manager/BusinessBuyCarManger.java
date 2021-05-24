package com.example.manager;

import com.example.common.bean.ShortcartProductBean;

public class BusinessBuyCarManger {

    private static BusinessBuyCarManger businessBuyCarManger;

    public synchronized static BusinessBuyCarManger getBusinessBuyCarManger() {
        if (businessBuyCarManger==null){
            businessBuyCarManger = new BusinessBuyCarManger();
        }
        return businessBuyCarManger;
    }

    private ShortcartProductBean shortcartProductBean;

    public ShortcartProductBean getShortProductBean() {
        return shortcartProductBean;
    }

    public void setShortProductBean(ShortcartProductBean shortcartProductBean) {
        this.shortcartProductBean = shortcartProductBean;
    }
}
