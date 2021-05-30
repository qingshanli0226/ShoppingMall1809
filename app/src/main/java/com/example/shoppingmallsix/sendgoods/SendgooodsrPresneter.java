package com.example.shoppingmallsix.sendgoods;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.find.FindForPayBean;
import com.example.net.bean.find.FindForSendbean;
import com.example.shoppingmallsix.sendgoods.Isendgoods;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SendgooodsrPresneter extends BasePresenter<Isendgoods> {

    public SendgooodsrPresneter(Isendgoods iShoppingcar){
        attachView(iShoppingcar);
    }

    public void getFindForPay(){

        RetrofitCreator.getFiannceApiService().getFindforsend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView != null){
                            iView.showLoading();
                        }
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView != null){
                            iView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<FindForSendbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindForSendbean findForSendbean) {
                            if (iView != null){
                                iView.onfindForSend(findForSendbean);
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
