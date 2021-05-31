package com.shoppingmall.pay.awitpayment;

import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.FindForPayBean;
import com.shoppingmall.net.model.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AwaitPaymentPresenter extends BasePresenter<IAwaitPaymentView> {
    public AwaitPaymentPresenter(IAwaitPaymentView iAwaitPaymentView){
        attachView(iAwaitPaymentView);
    }

    public void getFindForPay(){
        RetrofitCreate.getShoppingMallApiService().findForPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        if (iView!=null){
                            iView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {
                        if (iView!=null){
                            iView.onAwaitPayment(findForPayBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
