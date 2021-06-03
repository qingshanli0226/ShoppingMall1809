package com.example.framework.manager;

import android.util.Log;
import android.widget.Toast;

import com.example.net.AppMoudel;
import com.example.net.bean.HomeBean;
import com.example.net.bean.ShoppingCartBean;
import com.example.net.retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//缓存类
public class CaCheMannager implements CacheUserManager.IloginChange {
    private static CaCheMannager mannager;

    public static CaCheMannager getInstance() {
        if (mannager == null) {
            synchronized (CaCheMannager.class) {
                if (mannager == null) {
                    mannager = new CaCheMannager();
                }
            }
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

    public void init() {
        CacheUserManager.getInstance().registerLogin(this);
    }

    //登陆状态
    @Override
    public void onLoginChange(boolean loginBean) {
        if (loginBean) {
            Log.d("CaCheMannager", "登陆了");
            getShoppingData();
        }
    }

    //购物车存接口
    public interface IShoppingCartInterface {
        void onShoppinCartgData(List<ShoppingCartBean.ResultBean> shoppingCartBean);

        void onShoppingCartRemove(int position);//删除单个

        void onShoppingCartRemove(List<ShoppingCartBean.ResultBean> shoppingCartBean);//删除单个

    }

    private List<IShoppingCartInterface> list = new ArrayList<>();
    private List<ShoppingCartBean.ResultBean> shoppingCartBeanList = new ArrayList<>();//购物车数据
    private List<ShoppingCartBean.ResultBean> CheckList = new ArrayList<>();//选中集合

    public synchronized List<ShoppingCartBean.ResultBean> getCheckList() {
        return CheckList;
    }

    public synchronized void setCheckList(List<ShoppingCartBean.ResultBean> checkList) {
        CheckList = checkList;
    }

    public synchronized List<ShoppingCartBean.ResultBean> getShoppingCartBeanList() {
        return shoppingCartBeanList;
    }

    public synchronized void setShoppingCartBeanList(List<ShoppingCartBean.ResultBean> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
    }

    public synchronized void payNotify(int flag) {
        getShoppingData();
    }


    private float price = -1l;

    public synchronized void setShoppingPrice(float p) {
        this.price = p;
    }

    public synchronized float getShoppingPrice() {
        return price;
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
    public synchronized void showShoppingData() {
        this.shoppingCartBeanList = getShoppingCartBeanList();
        for (IShoppingCartInterface iShoppingCartInterface : list) {
            iShoppingCartInterface.onShoppinCartgData(CaCheMannager.getInstance().getShoppingCartBeanList());
        }
    }

    //删除单个商品
    public synchronized void removeShoppingData(int position) {
        this.shoppingCartBeanList = getShoppingCartBeanList();
        for (IShoppingCartInterface iShoppingCartInterface : list) {
            iShoppingCartInterface.onShoppingCartRemove(position);
        }
    }

    //删除单个商品
    public synchronized void removeShoppingData(List<ShoppingCartBean.ResultBean> shoppingCartBean) {
        this.shoppingCartBeanList = getShoppingCartBeanList();
        for (IShoppingCartInterface iShoppingCartInterface : list) {
            iShoppingCartInterface.onShoppingCartRemove(shoppingCartBean);
        }
    }

    //获取购物车数据
    public synchronized void getShoppingData() {
        RetrofitManager.getApi().getShoppingCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingCartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ShoppingCartBean shoppingCartBean) {
                        //获取数据成功   给缓存类赋值
                        CaCheMannager.getInstance().setShoppingCartBeanList(shoppingCartBean.getResult());
                        showShoppingData();
                        Log.d("CaCheMannager", "获取了数据");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
