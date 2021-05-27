package com.example.pay.payment;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;
import com.example.net.bean.PaymentBean;
import com.example.net.bean.RegisterBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PaymentPresenter extends BasePresenter<IPaymentView> {

    public PaymentPresenter(IPaymentView iPaymentView) {
        attchView(iPaymentView);
    }

    public void getpay(){
        RetrofitManager.getHttpApiService()
                .getPayment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        if (mView!= null){
                            mView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView!= null){
                            mView.hideLoading();
                        }

                    }
                })
                .subscribe(new Observer<PaymentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext( PaymentBean paymentBean) {
                        if (mView != null) {
                            mView.onPayment(paymentBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
