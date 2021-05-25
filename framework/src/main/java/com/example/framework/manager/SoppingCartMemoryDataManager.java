package com.example.framework.manager;

import com.blankj.utilcode.util.LogUtils;
import com.example.net.RetrofitCreator;
import com.example.net.bean.business.GetShortcartProductsBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SoppingCartMemoryDataManager {

    private static GetShortcartProductsBean resultBean;
    private static SoppingCartMemoryDataManager hoppingCartMemoryDataManager;
    private static List<ISoppingDateChange> iSoppingDateChangeArrayList = new ArrayList<>();
    //单例
    public synchronized static SoppingCartMemoryDataManager getInstance(){
       if (hoppingCartMemoryDataManager == null){
           hoppingCartMemoryDataManager = new SoppingCartMemoryDataManager();
       }
       return hoppingCartMemoryDataManager;
    }
    //获取Bean类
    public synchronized static GetShortcartProductsBean getResultBean() {
        return resultBean;
    }
    //请求数据
    public synchronized static void getShoppingData(){
        RetrofitCreator.getFiannceApiService()
                .getShortcartProductsBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetShortcartProductsBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetShortcartProductsBean shoppingCarBean) {
                        LogUtils.json(shoppingCarBean);
                        SoppingCartMemoryDataManager.setResultBean(shoppingCarBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //传输数据
    public synchronized static void setResultBean(GetShortcartProductsBean resultBean) {
        SoppingCartMemoryDataManager.resultBean = resultBean;
        for (int i = 0; i <iSoppingDateChangeArrayList.size() ; i++) {
            iSoppingDateChangeArrayList.get(i).onSoppingDataChange(resultBean);
        }
    }
    //注册
    public synchronized void registerHoppingCartMemory(SoppingCartMemoryDataManager.ISoppingDateChange iHoppingDateChange) {
        iSoppingDateChangeArrayList.add(iHoppingDateChange);
    }
    //删除注册
    public synchronized void unHoppingCartMemory(SoppingCartMemoryDataManager.ISoppingDateChange iHoppingDateChange) {
        iSoppingDateChangeArrayList.remove(iHoppingDateChange);
    }
    //接口回调
    public interface ISoppingDateChange{
       void onSoppingDataChange(GetShortcartProductsBean getShortcartProductsBean);
   }
}
