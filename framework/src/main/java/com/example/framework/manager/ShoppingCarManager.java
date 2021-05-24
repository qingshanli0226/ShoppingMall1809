package com.example.framework.manager;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarManager {

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
