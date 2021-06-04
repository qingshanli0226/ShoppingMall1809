package com.example.framework.manager;

import android.content.Intent;

import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
import com.example.net.bean.OrderinfoBean;
import com.example.net.retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PaySendCacheManager implements CacheUserManager.IloginChange{
    public static PaySendCacheManager paySendCacheManager;

    public synchronized static PaySendCacheManager getInstance() {
        if (paySendCacheManager == null) {
            paySendCacheManager = new PaySendCacheManager();
        }
        return paySendCacheManager;
    }

    private List<FindForSendBean.ResultBean> findForSendBean=new ArrayList<>();
    private List<FindForPayBean.ResultBean> findForPayBean=new ArrayList<>();
    private List<FindForSendBean.ResultBean> findForSendList=new ArrayList<>();


    private int a=0;
    public void  setonIndex(int p){
        this.a=p;
    }
    //获取数量


    //获取数量
    public int getTwoIndex() {
        return findForSendBean.size();
    }

    public List<FindForPayBean.ResultBean> getFindForPayBean() {
        return findForPayBean;
    }
    public void setpay(List<FindForPayBean.ResultBean> fid){
        this.findForPayBean=fid;
    }

    public List<FindForSendBean.ResultBean> getFindForSendBean() {
        return findForSendBean;
    }

    public void setFindForSendBean(List<FindForSendBean.ResultBean> findForSendBean) {
        this.findForSendBean=findForSendBean;

    }
    public List<FindForSendBean.ResultBean> getSendList(){
        return findForSendList;
    }
    public void setFindForSendList(List<FindForSendBean.ResultBean> findForSendList){
        this.findForSendList=findForSendList;
    }

    public void setFindForPayBean(FindForPayBean.ResultBean bean) {
        findForPayBean.add(bean);


    }
    private OrderinfoBean orderinfoBean;
    public void setOrderBean(OrderinfoBean orderBean){
        this.orderinfoBean=orderBean;
    }
    public OrderinfoBean getOrderBean(){
        return orderinfoBean;
    }

    public void init(){
        CacheUserManager.getInstance().registerLogin(this);
    }
    @Override
    public void onLoginChange(boolean loginBean) {
        if (loginBean){
            getFindPay();
        }

    }
    public void getFindPay(){
        RetrofitManager.getApi()
                .getForPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {

                            PaySendCacheManager.getInstance().setpay(findForPayBean.getResult());
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
