package com.example.framework.manager;

import android.app.Activity;

import com.example.net.RetrofitManager;
import com.example.net.bean.CartBean;
import com.example.net.bean.LoginBean;

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
        iUserChange = new CacheUserManager.IUserChange() {
            @Override
            public void onUserChange(LoginBean loginBean) {
                getShowCart();
            }
        };
        CacheUserManager.getInstance().registerLogin(iUserChange);
    }
    private CacheUserManager.IUserChange iUserChange;

    //购物车数据源
    private List<CartBean.ResultBean> carts = new ArrayList<>();
    private List<CartBean.ResultBean> cartsPrice = new ArrayList<>();
    private List<ICartChange> cartChanges = new LinkedList<>();
    private List<Activity> activityList = new LinkedList<>();


    public List<CartBean.ResultBean> getCartsPrice() {
        return cartsPrice;
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }
    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public List<Activity> getActivityList(){
        return activityList;
    }


    public synchronized void registerCart(ICartChange iCartChange) {
        cartChanges.add(iCartChange);
    }

    public synchronized void unRegisterCart(ICartChange iCartChange) {
        cartChanges.remove(iCartChange);
    }

    public List<CartBean.ResultBean> getCarts() {
        return carts;
    }

    public void setCarts(List<CartBean.ResultBean> carts) {
        this.carts.addAll(carts);
        cartsPrice.addAll(carts);

    }

    public synchronized void getShowCart() {
        RetrofitManager.getHttpApiService()
                .showCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CartBean cartBean) {
                        if (cartBean.getCode().equals("200")) {
                            setCarts(cartBean.getResult());
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

    public synchronized void notifyCar(List<CartBean.ResultBean> carts){
        for (ICartChange cartChange : cartChanges) {
            cartChange.onShowCart(carts);
        }
    }

    //添加数据
    public synchronized void addData(CartBean.ResultBean resultBean){
        int count = 0;
        int position = -1;
        for (int i = 0; i < carts.size(); i++) {
            CartBean.ResultBean cart = carts.get(i);
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
        for (CartBean.ResultBean cart : carts) {
            cart.setProductSelected(isCheckAll);
        }
    }
    //修改库存
    public synchronized void setNum(int position,String num){
        carts.get(position).setProductNum(num);

    }



    public void removeMany(boolean isOne,int position) {
        if(isOne){
            carts.remove(position);
            notifyRemoveProduct(position);
        } else{
            for (int i = carts.size()-1; i >= 0; i--) {
                CartBean.ResultBean bean = carts.get(i);
                if(bean.isProductSelected()){
                    carts.remove(i);
                }
            }
            notifyCar(carts);
        }


    }
    //通知页面
    public void notifyRemoveProduct(int position){
        for (ICartChange cartChange : cartChanges) {
            cartChange.onRemoveProduct(position);
        }
    }
    public interface ICartChange {
        void onShowCart(List<CartBean.ResultBean> carts);
        void onAddCart(int position);
        //删除一个
        void onRemoveProduct(int position);
    }
    public void destory(){
        CacheUserManager.getInstance().unregisterLogin(iUserChange);
    }

}
