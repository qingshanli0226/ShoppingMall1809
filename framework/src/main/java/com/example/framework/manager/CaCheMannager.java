package com.example.framework.manager;

import com.example.net.bean.HomeBean;
import com.example.net.bean.ShoppingCartBean;

import java.util.ArrayList;
import java.util.List;

//缓存类
public class CaCheMannager {
    private static CaCheMannager mannager;
    public static CaCheMannager getInstance() {
        if (mannager == null) {
            mannager = new CaCheMannager();
        }
        return mannager;
    }
    private HomeBean homeBean;
    public HomeBean getHomeBean() {
        return homeBean;
    }
    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }



    //购物车存接口
    public interface IShoppingCartInterface {
        void onShoppinCartgData(List<ShoppingCartBean.ResultBean> shoppingCartBean);
        void onShoppingCartRemove(int position);//删除单个
        void onShoppingCartRemove(List<ShoppingCartBean.ResultBean> shoppingCartBean);//删除单个
    }

    private List<IShoppingCartInterface> list = new ArrayList<>();
    private ShoppingCartBean shoppingCartBean;//购物车请求返回
    private List<ShoppingCartBean.ResultBean> shoppingCartBeanList;//购物车数据

    public ShoppingCartBean getShoppingCartBean() {
        return shoppingCartBean;
    }
    public void setShoppingCartBean(ShoppingCartBean shoppingCartBean) {
        this.shoppingCartBean = shoppingCartBean;
    }
    public List<ShoppingCartBean.ResultBean> getShoppingCartBeanList() {
        return shoppingCartBeanList;
    }
    public void setShoppingCartBeanList(List<ShoppingCartBean.ResultBean> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
    }

    //注册
    public void registerIShoppingCart(IShoppingCartInterface iShoppingCartInterface) {
        if (!list.contains(iShoppingCartInterface)) {
            list.add(iShoppingCartInterface);
        }
    }
    public void unregisterIShoppingCart(IShoppingCartInterface iShoppingCartInterface) {
        if (list.contains(iShoppingCartInterface)) {
            list.remove(iShoppingCartInterface);
        }
    }

    //显示
    public void showShoppingData() {
        this.shoppingCartBean = getShoppingCartBean();
        for (IShoppingCartInterface iShoppingCartInterface : list) {
            iShoppingCartInterface.onShoppinCartgData(CaCheMannager.getInstance().getShoppingCartBeanList());
        }
    }

    //删除单个商品
    public void removeShoppingData(int position) {
        this.shoppingCartBean = getShoppingCartBean();
        for (IShoppingCartInterface iShoppingCartInterface : list) {
            iShoppingCartInterface.onShoppingCartRemove(position);
        }
    } //删除单个商品
    public void removeShoppingData(List<ShoppingCartBean.ResultBean> shoppingCartBean) {
        this.shoppingCartBean = getShoppingCartBean();
        for (IShoppingCartInterface iShoppingCartInterface : list) {
            iShoppingCartInterface.onShoppingCartRemove(shoppingCartBean);
        }
    }


}
