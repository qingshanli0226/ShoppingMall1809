package com.example.pay.payment;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.CheckNumAll;
import com.example.net.bean.PayBean;
import com.example.net.bean.PayCheckBean;
import com.example.net.bean.PayResult;
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

public class PayMentPresenter extends BasePresenter<IPaymentView> {
    public PayMentPresenter(IPaymentView paymentView) {
        attchView(paymentView);
    }

    //查询全部数量
    public void checkNumAll(PayCheckBean payCheckBean){
        String s = new Gson().toJson(payCheckBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .configPayCheck(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            if (mView != null) {
                                mView.onConfigPay(selectBean);
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
