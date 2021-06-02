package com.example.framework.manager;

import android.content.Intent;

import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
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

    private FindForSendBean findForSendBean;
    private FindForPayBean findForPayBean;

    private int a=0;
    public void  setonIndex(int p){
        this.a=p;
    }
    //获取数量
    public int getOneIndex() {
        return findForPayBean.getResult().size();
    }
    private List<FindForSendBean.ResultBean> findForSendList=new ArrayList<>();

    //获取数量
    public int getTwoIndex() {
        return findForSendBean.getResult().size();
    }

    public FindForPayBean getFindForPayBean() {
        return findForPayBean;
    }

    public FindForSendBean getFindForSendBean() {
        return findForSendBean;
    }

    public void setFindForSendBean(FindForSendBean findForSendBean) {
        this.findForSendBean = findForSendBean;

    }
    public List<FindForSendBean.ResultBean> getSendList(){
        return findForSendList;
    }

    public void setFindForPayBean(FindForPayBean findForPayBean) {
        this.findForPayBean = findForPayBean;


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

                            PaySendCacheManager.getInstance().setFindForPayBean(findForPayBean);
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
