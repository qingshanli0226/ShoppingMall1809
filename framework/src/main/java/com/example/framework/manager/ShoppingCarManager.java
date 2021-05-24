package com.example.framework.manager;

import com.example.net.model.FindForBean;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarManager {
    private FindForBean findForPayBean;
    private FindForBean findForSendBean;

    public FindForBean getFindForPayBean() {
        return findForPayBean;
    }

    public void setFindForPayBean(FindForBean findForPayBean) {
        this.findForPayBean = findForPayBean;
    }

    public FindForBean getFindForSendBean() {
        return findForSendBean;
    }

    public void setFindForSendBean(FindForBean findForSendBean) {
        this.findForSendBean = findForSendBean;
    }

    private List<IShoppingCar> list=new ArrayList<>();

    private static ShoppingCarManager shoppingCarManager;

    public static ShoppingCarManager getInstance() {
        if (shoppingCarManager==null){
            shoppingCarManager=new ShoppingCarManager();
        }
        return shoppingCarManager;
    }

    public interface IShoppingCar{
        void onShoppingCar();
    }

    public void register(IShoppingCar iShoppingCar){
        list.add(iShoppingCar);
    }

    public void unregister(IShoppingCar iShoppingCar){
        list.remove(iShoppingCar);
    }
}
