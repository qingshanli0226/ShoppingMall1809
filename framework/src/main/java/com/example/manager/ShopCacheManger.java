package com.example.manager;

import android.content.Context;
import android.util.Log;

import com.example.common.bean.FindForPayBean;
import com.example.common.bean.LogBean;
import com.example.common.bean.SelectOrderBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.net.RetrofitCreate;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopCacheManger implements BusinessUserManager.IUserLoginChanged{

    private static ShopCacheManger cacheManger;
    //商品数量
    private String productNum;

    //购物车所有数据缓存
    private List<ShortcartProductBean.ResultBean> shortBeanList = new ArrayList<>();
    //选中商品缓存
    private List<ShortcartProductBean.ResultBean> selectList = new ArrayList<>();
    //提交订单缓存
    private List<SelectOrderBean> list = new ArrayList<>();
    //支付成功缓存
    private List<FindForPayBean.ResultBean> paySussList = new ArrayList<>();
    //支付失败缓存
    private List<FindForPayBean.ResultBean> payFailList = new ArrayList<>();
    //消息缓存
    private List<FindForPayBean.ResultBean> messageList = new ArrayList<>();
    //购物车数据发生改变时
    private List<iShopBeanChangeListener> shopBeanChangeListeners = new ArrayList<>();
    private Context mContext;

    public void registerShopBeanChange(iShopBeanChangeListener iShopBeanChangeListener){
        shopBeanChangeListeners.add(iShopBeanChangeListener);
    }
    public void unregisterShopBeanChange(iShopBeanChangeListener iShopBeanChangeListener){
        shopBeanChangeListeners.remove(iShopBeanChangeListener);
    }
    public static ShopCacheManger getInstance() {
        synchronized (ShopCacheManger.class){
            if (cacheManger==null){
                cacheManger = new ShopCacheManger();
            }
        }
        return cacheManger;
    }
    public void init(Context context){
        this.mContext=context;
        BusinessUserManager.getInstance().Register(this);
    }
    //判断当期是否登录
    @Override
    public void onLoginChange(LogBean isLog) {
        //登录后请求购物车数据
        if (isLog!=null){
            requestShortProductData();
        }
    }



    public List<ShortcartProductBean.ResultBean> getShortBeanList() {
        return shortBeanList;
    }

    public void setShortBeanList(List<ShortcartProductBean.ResultBean> shortBeanList) {

        this.shortBeanList = shortBeanList;
        Notify();
    }



    public List<SelectOrderBean> getList() {
        return list;
    }

    public void setList(List<SelectOrderBean> list) {
        this.list = list;
    }

    //当前选中的购物车商品集合
    public void setSelect(ShortcartProductBean.ResultBean select) {
        if (select != null) {
            if (!selectList.contains(select)) {
                if (select.isAll()){
                    selectList.add(select);
                }
            }else {
                if (!select.isAll()){
                    selectList.remove(select);
                }
            }
        }
        Log.i("zx", "setSelect: "+selectList.toString());
    }

    //返回选中的集合
    public List<ShortcartProductBean.ResultBean> getSelectList() {
        return selectList;
    }


    public String getMoneyValue() {
        float sumPrice=0;
        for (ShortcartProductBean.ResultBean resultBean : shortBeanList) {
            if(resultBean.isAll()){
                float productPrice=Float.parseFloat(resultBean.getProductPrice()+"");
                int productNum=Integer.parseInt(resultBean.getProductNum());
                sumPrice=sumPrice+productPrice*productNum;
            }
        }
        return String.valueOf(sumPrice);
    }



    //详情页面添加数据
    public void addShopMessageNum(String productId,String productName,String productNum,String url,String productPrice,boolean isAll){
        Log.i("zx", "addShopMessageName: "+productName);
        ShortcartProductBean.ResultBean bean = new ShortcartProductBean.ResultBean(productId, productName, productNum, url, productPrice, false);

            for (ShortcartProductBean.ResultBean resultBean : shortBeanList) {
                if (resultBean.getProductName().equals(bean.getProductName())){
                    int i = Integer.parseInt(resultBean.getProductNum());
                    int j = Integer.parseInt(bean.getProductNum());
                    Log.i("zx", "addShopMessageNum: i="+i+"j="+j);
                    i+=j;
                    resultBean.setProductNum(i+"");
                    Notify();
                    return;
                }
            }
            shortBeanList.add(bean);

        Notify();

    }

    //购物车中删除
    public void ShopDelOne(ShortcartProductBean.ResultBean bean){
        shortBeanList.remove(bean);
        //删除完后刷新
        Notify();
    }
    //商品数量+1
    public void addShopNum(ShortcartProductBean.ResultBean bean){
        for (ShortcartProductBean.ResultBean resultBean : shortBeanList) {
            if (resultBean.getProductName().equals(bean.getProductName())){
                int num=Integer.parseInt(bean.getProductNum());
                resultBean.setProductNum(num+1+"");
            }
        }
        Log.i("zx", "addShopNum: "+shortBeanList.toString());
        Notify();

    }
    //商品数量-1
    public void subShopNum(ShortcartProductBean.ResultBean bean){
        for (ShortcartProductBean.ResultBean resultBean : shortBeanList) {
            if (resultBean.getProductName().equals(bean.getProductName())){
                int num=Integer.parseInt(bean.getProductNum());
                resultBean.setProductNum(num-1+"");
            }
        }
        Log.i("zx", "subShopNum: "+shortBeanList.toString());
       Notify();

    }


    public void setMessageList(List<FindForPayBean.ResultBean> messageList) {
        this.messageList = messageList;
    }

    public List<FindForPayBean.ResultBean> getPayFailList() {
        return payFailList;
    }


    public List<FindForPayBean.ResultBean> getPaySussList() {
        return paySussList;
    }


    //购物车页面数据
    public synchronized void requestShortProductData(){
        RetrofitCreate.getFiannceApiService()
                .getShortProductData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShortcartProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull ShortcartProductBean shortcartProductBean) {
                        List<ShortcartProductBean.ResultBean> result = shortcartProductBean.getResult();
                        ShopCacheManger.getInstance().setShortBeanList(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public  interface iShopBeanChangeListener{
        void OnChange();
    }

    public void Notify(){
        for (iShopBeanChangeListener shopBeanChangeListener : shopBeanChangeListeners) {
            shopBeanChangeListener.OnChange();
        }
    }



}
