package com.example.manager;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.LogBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.net.RetrofitCreate;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManger {

    private static CacheManger cacheManger;
    private Context context;

    private List<ShortcartProductBean.ResultBean> shortBeanList=new ArrayList<>();

    public synchronized static CacheManger getInstance() {
        if (cacheManger==null){
            cacheManger = new CacheManger();
        }
        return cacheManger;
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


    //选中的商品
    public void setOneSelectBeanList(int position,boolean isSelect) {
        if (shortBeanList!=null){
            shortBeanList.get(position).setAll(!isSelect);
        }


    }


    public void init(Context application){
        context = application;
    }

    public void registerUserManger(){
        BusinessUserManager.getInstance().Register(new BusinessUserManager.IUserLoginChanged() {
            @Override
            public void onLoginChange(LogBean isLog) {
                if (isLog!=null){
                    Log.i("zrf", "onLoginChange: "+"sss");
                    requestShortProductData();
                }else {
                    Log.i("zrf", "onLoginChange: "+"aaa");
                }
            }
        });
    }

    public void registerBuyCarManger(){
        BusinessBuyCarManger.getInstance().Register(new BusinessBuyCarManger.iShopBeanChange() {
            @Override
            public void OnShopBeanChange(ShortcartProductBean shortcartProductBean) {
                if (shortcartProductBean!=null){
                    Log.i("zrf", "onLoginChange: "+"获得数据");
                }else{
                    Log.i("zrf", "onLoginChange: "+"没有数据" );
                }
            }
        });
    }

    public void requestShortProductData(){
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




}
