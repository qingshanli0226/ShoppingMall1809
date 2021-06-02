package com.shoppingmall.pay.order;

import com.google.gson.Gson;
import com.shoppingmall.framework.manager.CacheShopManager;
import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.OrderBean;
import com.shoppingmall.net.bean.PayBean;
import com.shoppingmall.net.model.RetrofitCreate;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderPresenter extends BasePresenter<IOrderView> {
    public OrderPresenter(IOrderView iOrderView){
        attachView(iOrderView);
    }

    //下订单
    public void getOrder(PayBean payBean){
        String s = new Gson().toJson(payBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitCreate.getShoppingMallApiService()
                .orderInfo(requestBody)
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
                            if (iView != null) {
                                iView.orderInfo(orderBean);
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
