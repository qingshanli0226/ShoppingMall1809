package com.example.pay.awaitpayment;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.AwaitPaymentBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AwaitPaymentPresenter extends BasePresenter<IAwaitPaymentView> {

    public AwaitPaymentPresenter(IAwaitPaymentView iAwaitPaymentView) {
        attchView(iAwaitPaymentView);
    }

    public void getpay(){
        RetrofitManager.getHttpApiService()
                .getAwaitPayment()
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
                .subscribe(new Observer<AwaitPaymentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext( AwaitPaymentBean paymentBean) {
                        if (mView != null) {
                            mView.onAwaitPayment(paymentBean);
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
