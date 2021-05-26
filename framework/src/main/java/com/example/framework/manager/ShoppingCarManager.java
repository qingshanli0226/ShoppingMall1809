package com.example.framework.manager;

import com.blankj.utilcode.util.LogUtils;
import com.example.net.model.FindForBean;
import com.example.net.model.ShoppingTrolleyBean;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarManager {
    //待付款
    private FindForBean findForPayBean;
    //待发货
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

    private IFindForBean iFindForBean;
    public interface IFindForBean{
        void onFindForBean();
    }

    public void unRegisterFindForBean() {
        iFindForBean =null;
    }
    public void registerFindForBean(IFindForBean iFindForBean) {
        this.iFindForBean = iFindForBean;
    }

    //购物车集合
    private List<ShoppingTrolleyBean.ResultBean> result;

    //初始化购物车集合
    public synchronized void initResult(List<ShoppingTrolleyBean.ResultBean> result) {
        this.result = result;
    }

    //添加购物车集合
    public synchronized void insertResultBean(ShoppingTrolleyBean.ResultBean resultBean) {
        result.add(resultBean);
    }

    //删除购物车集合
    public synchronized void deleteResultBean(ShoppingTrolleyBean.ResultBean resultBean) {
        result.remove(resultBean);
    }

    //修改购物车集合
    public synchronized void upDataResultBean(ShoppingTrolleyBean.ResultBean resultBean) {
        for (ShoppingTrolleyBean.ResultBean bean : result) {
            if (bean.getProductId().equals(resultBean.getProductId())) {
                bean = resultBean;
            }
        }
    }

    //查询集合
    public synchronized ShoppingTrolleyBean.ResultBean selectResultBean(String productId) {
        for (ShoppingTrolleyBean.ResultBean bean : result) {
            if (bean.getProductId().equals(productId)) {
                return bean;
            }
        }
        return null;
    }

    //全选
    public synchronized boolean checkAll() {
        for (ShoppingTrolleyBean.ResultBean resultBean : result) {
            resultBean.setProductSelected(true);
        }
        return true;
    }

    //取消全选
    public synchronized boolean unCheckAll() {
        for (ShoppingTrolleyBean.ResultBean resultBean : result) {
            resultBean.setProductSelected(false);
        }
        return true;
    }

    //删除一部分
    public synchronized void deletePartResult(boolean isIrder, List<ShoppingTrolleyBean.ResultBean> delete) {
        for (int i = result.size() - 1; i >= 0; i--) {
            for (int j = delete.size() - 1; j >= 0; j--) {
                if (result.get(i).getProductId().equals(delete.get(j).getProductId())) {
                    delete.remove(j);
                    result.remove(i);
                }
            }
        }
        if (isIrder) {
            iFindForBean.onFindForBean();
        }
    }

    //单例
    private static ShoppingCarManager shoppingCarManager;

    private ShoppingCarManager() {
    }

    public static ShoppingCarManager getInstance() {
        if (shoppingCarManager == null) {
            shoppingCarManager = new ShoppingCarManager();
        }
        return shoppingCarManager;
    }

    //接口回调
    private List<IShoppingCar> list = new ArrayList<>();

    public interface IShoppingCar {
        void onShoppingCar(List<ShoppingTrolleyBean.ResultBean> result);
    }

    //注册接口
    public synchronized void register(IShoppingCar iShoppingCar) {
        list.add(iShoppingCar);
    }

    //取消注册接口
    public synchronized void unregister(IShoppingCar iShoppingCar) {
        list.remove(iShoppingCar);
    }

    //刷新接口回调
    public synchronized void refreshData() {
        for (IShoppingCar iShoppingCar : list) {
            iShoppingCar.onShoppingCar(result);
        }
    }
}
