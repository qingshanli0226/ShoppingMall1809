package com.example.pay.orderinfo;

import com.example.framework.BasePresenter;
import com.example.framework.manager.CacheShopManager;
import com.example.net.RetrofitManager;
import com.example.net.bean.CartBean;
import com.example.net.bean.OrderBean;
import com.example.net.bean.PayBean;
import com.example.net.bean.SelectBean;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderPresenter extends BasePresenter<IOrderView> {
    public OrderPresenter(IOrderView orderView) {
        attchView(orderView);
    }

    //下订单
    public void getOrder(PayBean payBean){
        String s = new Gson().toJson(payBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .getOrder(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .subscribe(new Observer<OrderBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OrderBean orderBean) {
                        if (orderBean.getCode().equals("200")) {
                            CacheShopManager.getInstance().removeMany(false,-1);
                            if (mView != null) {
                                mView.onOrder(orderBean);
                            }
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

}
