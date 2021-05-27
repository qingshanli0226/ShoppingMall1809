package com.example.myapplication.personalCenter;

import android.util.Log;

import com.example.framework.manager.PaySendCacheManager;
import com.example.net.RetrofitManager;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;

public class PersonalPresenter extends BasePresenter<IPersonalView> {
    public PersonalPresenter(IPersonalView view) {
        attView(view);
    }
    public void getFindForPay(){
        RetrofitManager.getApi().getForPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (mView!=null){
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView!=null){
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {
                        if (mView!=null){
                            mView.onShoppingPay(findForPayBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            Log.d("获取待支付订单错误PersonalPresenter", e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public void onFindPay(){
        RetrofitManager.getApi()
                .getForPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {
                        if (mView!=null){
                            PaySendCacheManager.getInstance().setFindForPayBean(findForPayBean);
                            Log.d("FindpayPresenter", findForPayBean.toString());
                           mView.ondend(findForPayBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getFindSend(){
        RetrofitManager.getApi()
                .getForSend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<FindForSendBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForSendBean findForSendBean) {
                        mView.onShoppingSend(findForSendBean);
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
