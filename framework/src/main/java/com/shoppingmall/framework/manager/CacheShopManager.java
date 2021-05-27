package com.shoppingmall.framework.manager;

import android.os.UserManager;

import com.blankj.utilcode.util.LogUtils;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.bean.ShopCarBean;
import com.shoppingmall.net.model.RetrofitCreate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheShopManager {
    private static CacheShopManager cacheShopManager;

    private CacheShopManager() {

    }

    public synchronized static CacheShopManager getInstance() {
        if (cacheShopManager == null) {
            cacheShopManager = new CacheShopManager();
        }
        return cacheShopManager;
    }

    public void init(){
        ShopMallUserManager.getInstance().registerUserLoginChanged(new ShopMallUserManager.IUserLoginChanged() {
            @Override
            public void onLoginChanged(LoginBean loginBean) {
                getShowCart();
            }
        });
    }
    private ShopMallUserManager.IUserLoginChanged iUserChange;

    //购物车数据源
    private List<ShopCarBean.ResultBean> carts = new ArrayList<>();
    private List<ShopCarBean.ResultBean> cartsPrice = new ArrayList<>();
    private List<ICartChange> cartChanges = new LinkedList<>();

    public List<ShopCarBean.ResultBean> getCartsPrice() {
        return cartsPrice;
    }



    public synchronized void registerCart(ICartChange iCartChange) {
        cartChanges.add(iCartChange);
    }

    public synchronized void unRegisterCart(ICartChange iCartChange) {
        cartChanges.remove(iCartChange);
    }

    public List<ShopCarBean.ResultBean> getCarts() {
        return carts;
    }

    public void setCarts(List<ShopCarBean.ResultBean> carts) {
        this.carts.addAll(carts);
        cartsPrice.addAll(carts);

    }

    public synchronized void getShowCart() {
        RetrofitCreate.getShoppingMallApiService()
                .getShopCarProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopCarBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ShopCarBean shopCarBean) {
                        if (shopCarBean.getCode().equals("200")){
                            setCarts(shopCarBean.getResult());
                            notifyCar(carts);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public synchronized void notifyCar(List<ShopCarBean.ResultBean> carts){
        for (ICartChange cartChange : cartChanges) {
            cartChange.onShowCart(carts);
        }
    }

    //添加数据
    public synchronized void addData(ShopCarBean.ResultBean resultBean){
        int count = 0;
        int position = -1;
        for (int i = 0; i < carts.size(); i++) {
            ShopCarBean.ResultBean cart = carts.get(i);
            if (cart.getProductId().equals(resultBean.getProductId())) {
                int cartNum = Integer.parseInt(cart.getProductNum());
                int resultNum = Integer.parseInt(resultBean.getProductNum());
                cart.setProductNum(cartNum+resultNum+"");
                position = i;
            } else{
                count++;
            }
        }
        if(count == carts.size()){
            carts.add(resultBean);
            cartsPrice.add(resultBean);
            position = carts.size();
        }
        //通知
        notifyAdd(position);
    }
    //添加通知
    public synchronized void notifyAdd(int position){
        for (ICartChange cartChange : cartChanges) {
            cartChange.onAddCart(position);
        }
    }

    //单选改变
    public synchronized void setCheck(int position,boolean isCheck){
        carts.get(position).setProductSelected(isCheck);
    }

    //全选
    public synchronized void setChackAll(boolean isCheckAll){
        for (ShopCarBean.ResultBean cart : carts) {
            cart.setProductSelected(isCheckAll);
        }
    }
    //修改库存
    public synchronized void setNum(int position,String num){
        carts.get(position).setProductNum(num);

    }

    public synchronized void removeProduct(ShopCarBean.ResultBean resultBean) {
        for (int i = carts.size()-1; i >= 0; i--) {
            ShopCarBean.ResultBean bean = carts.get(i);
            if(bean.getProductId().equals(resultBean.getProductId())){
                carts.remove(i);
            }
        }
    }

    public void removeMany(List<ShopCarBean.ResultBean> resultBeans) {
        for (int i = carts.size()-1; i >= 0; i--) {
            ShopCarBean.ResultBean bean = carts.get(i);
            for (int i1 = resultBeans.size()-1; i1 >= 0; i1--) {
                if(bean.getProductId().equals(resultBeans.get(i1).getProductId())){
                    carts.remove(i);
                    resultBeans.remove(i1);
                }
            }
        }
        LogUtils.d("removeManyaaa");
    }


    public interface ICartChange {
        void onShowCart(List<ShopCarBean.ResultBean> carts);
        void onAddCart(int position);
    }
    public void destory(){
        ShopMallUserManager.getInstance().unregisterUserLoginChanged(iUserChange);
    }
}
