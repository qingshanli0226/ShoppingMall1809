package com.example.pay.obligation;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.find.FindForPayBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ObligationPresenter extends BasePresenter<Iobligation> {

    public ObligationPresenter(Iobligation iobligation){
        attachView(iobligation);
    }

    public void findForSend(){

        RetrofitCreator.getFiannceApiService()
                .getFindforpay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            iView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView!=null){
                            iView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindForPayBean findForPayBean) {
                            if (iView != null){
                                iView.onfindForpay(findForPayBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView != null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
