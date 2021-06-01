package com.example.manager;

import com.example.common.bean.ShortcartProductBean;

import java.util.ArrayList;
import java.util.List;

public class BusinessBuyCarManger {

    private iShopBeanChange shopBeanChange;
    private static BusinessBuyCarManger businessBuyCarManger;
    private ShortcartProductBean shortcartProductBean;

    private List<iShopBeanChange> list=new ArrayList<>();

    public BusinessBuyCarManger() {

    }

    public void Register(iShopBeanChange shopBeanChange){
        list.add(shopBeanChange);
    }

    public void UnRegister(iShopBeanChange shopBeanChange){
        list.remove(shopBeanChange);
    }


    public static BusinessBuyCarManger getInstance() {
        if (businessBuyCarManger==null){
            businessBuyCarManger = new BusinessBuyCarManger();
        }
        return businessBuyCarManger;
    }


    private boolean isChange;

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }


    public ShortcartProductBean getShortcartProductBean() {
        return shortcartProductBean;
    }

    public void setShortcartProductBean(ShortcartProductBean shortcartProductBean) {
        this.shortcartProductBean = shortcartProductBean;
        if (shortcartProductBean!=null){
            for (iShopBeanChange iShopBeanChange : list) {
                iShopBeanChange.OnShopBeanChange(shortcartProductBean);
            }
        }
    }

    public interface iShopBeanChange{
        void OnShopBeanChange(ShortcartProductBean shortcartProductBean);
    }
}
