package com.example.manager;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.FindForPayBean;
import com.example.common.bean.LogBean;
import com.example.common.bean.RegBean;
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

public class ShopCacheManger {

    private static ShopCacheManger cacheManger;
    private Context context;

    private String productNum;

    private List<iSelectShop> onSelectShopList=new ArrayList<>();

    private List<ShortcartProductBean.ResultBean> shortBeanList = new ArrayList<>();

    private List<ShortcartProductBean.ResultBean> selectList = new ArrayList<>(); //选中的集合

    private List<SelectOrderBean> list = new ArrayList<>();

    private List<FindForPayBean.ResultBean> paySussList = new ArrayList<>();

    private List<FindForPayBean.ResultBean> payFailList = new ArrayList<>();

    private List<FindForPayBean.ResultBean> messageList = new ArrayList<>();


    public synchronized static ShopCacheManger getInstance() {
        if (cacheManger==null){
            cacheManger = new ShopCacheManger();
        }
        return cacheManger;
    }


    public void RegisterSelectShop(iSelectShop iSelectShop){
        onSelectShopList.add(iSelectShop);
    }

    public void unRegisterSelectShop(iSelectShop iSelectShop){
        onSelectShopList.remove(iSelectShop);
    }

    public void setSelect(ShortcartProductBean.ResultBean select) {

        if (select != null) {
            if (!selectList.contains(select)) {     //判断是否包含
                if (select.isAll()){                //选中
                    selectList.add(select);
                }
            }else {
                if (!select.isAll()){               //没选中
                    selectList.remove(select);
                }
            }
        }
    }

    public List<SelectOrderBean> getList() {
        return list;
    }

    public void setList(List<SelectOrderBean> list) {
        this.list = list;
    }


    public List<FindForPayBean.ResultBean> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<FindForPayBean.ResultBean> messageList) {
        this.messageList = messageList;
    }

    public List<ShortcartProductBean.ResultBean> getSelectList() {
        return selectList;
    }

    public List<ShortcartProductBean.ResultBean> getShortBeanList() {
        return shortBeanList;
    }


    public void setShortBeanList(List<ShortcartProductBean.ResultBean> shortBean) {
        if (shortBean!=null) {
            shortBean.clear();
            shortBeanList.addAll(shortBean);
        }
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

    public void registerUserManger(){
        BusinessUserManager.getInstance().Register(new BusinessUserManager.IUserLoginChanged() {
            @Override
            public void onLoginChange(LogBean isLog) {
                if (isLog!=null){
                    requestShortProductData();
                }else {
                }
            }
        });
    }

    public void registerBuyCarManger(){
        BusinessBuyCarManger.getInstance().Register(new BusinessBuyCarManger.iShopBeanChange() {
            @Override
            public void OnShopBeanChange(ShortcartProductBean shortcartProductBean) {
                if (shortcartProductBean!=null){
                    com.example.common.LogUtils.i("ShopCacheManger",162,"获得数据");
                }else{
                    com.example.common.LogUtils.i("ShopCacheManger",164,"没有数据");

                }
            }
        });
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
                        LogUtils.json(shortcartProductBean);
                        Log.i("xxxxx", "onNext: "+shortcartProductBean.getResult().toString());
                        List<ShortcartProductBean.ResultBean> result = shortcartProductBean.getResult();
                        shortBeanList.addAll(result);
                        BusinessBuyCarManger.getInstance().setShortcartProductBean(shortcartProductBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public ShortcartProductBean getShortData(){
        ShortcartProductBean shortcartProductBean = BusinessBuyCarManger.getInstance().getShortcartProductBean();
        return shortcartProductBean;
    }

    //检查库存
    public void getCheckShopData(String productId,String productNum){

        RetrofitCreate.getFiannceApiService()
                .checkOneProductInventory(productId,productNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegBean regBean) {
                        setProductNum(regBean.getResult());
                        Log.i("kucunkucun", "onNext: "+regBean.getResult());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
    public interface iSelectShop {
        void onSelectBean(List<ShortcartProductBean.ResultBean> selectShopList);
    }


}
