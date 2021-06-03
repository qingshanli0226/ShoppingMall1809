package com.example.manager;

import android.content.Context;
import android.util.Log;

import com.example.common.LogUtils;
import com.example.common.bean.FindForPayBean;
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

public class ShopCacheManger{

    private static ShopCacheManger cacheManger;
    private Context context;
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


    public List<ShortcartProductBean.ResultBean> getShortBeanList() {
        return shortBeanList;
    }

    public void setShortBeanList(List<ShortcartProductBean.ResultBean> shortBeanList) {

        this.shortBeanList = shortBeanList;
        for (iShopBeanChangeListener shopBeanChangeListener : shopBeanChangeListeners) {
            shopBeanChangeListener.OnChange();
        }
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
                    Log.i("zx", "setSelect: 添加");
                    selectList.add(select);
                }
            }else {
                if (!select.isAll()){
                    Log.i("zx", "setSelect: 删除");
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


    public void init(Context application){
        context = application;
    }
    //详情页面添加数据
    public void addShopMessageNum(String productId,String productName,String productNum,String url,String productPrice,boolean isAll){
        ShortcartProductBean.ResultBean bean = new ShortcartProductBean.ResultBean(productId, productName, productNum, url, productPrice, false);
        if (!shortBeanList.contains(bean)){
            shortBeanList.add(bean);
            for (iShopBeanChangeListener shopBeanChangeListener : shopBeanChangeListeners) {
                shopBeanChangeListener.OnChange();
            }
        }
        shortBeanList.add(bean);

    }

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
                        LogUtils.i(""+shortcartProductBean.getResult().toString());
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

    public List<FindForPayBean.ResultBean> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<FindForPayBean.ResultBean> messageList) {
        this.messageList = messageList;
    }

    public List<FindForPayBean.ResultBean> getPayFailList() {
        return payFailList;
    }

    public void setPayFailList(List<FindForPayBean.ResultBean> payFailList) {
        this.payFailList = payFailList;
    }

    public List<FindForPayBean.ResultBean> getPaySussList() {
        return paySussList;
    }

    public void setPaySussList(List<FindForPayBean.ResultBean> paySussList) {
        this.paySussList = paySussList;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }
    public  interface iShopBeanChangeListener{
        void OnChange();
    }



}
