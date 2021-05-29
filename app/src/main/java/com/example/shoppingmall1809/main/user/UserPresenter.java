package com.example.shoppingmall1809.main.user;

import android.widget.ImageView;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.CategoryBean;
import com.example.net.model.FindForBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends BasePresenter<IUserView> {
//    public UserPresenter(IUserView iUserView) {
//        attachView(iUserView);
//    }
//
//
//    public void getFindForPayData() {
//        RetrofitCreator.getShopApiService().getFindForPayData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(disposable -> {
//                    add(disposable);
//                    if (iView != null) {
//                        iView.showLoading();
//                    }
//                })
//                .doFinally(() -> {
//                    if (iView != null) {
//                        iView.hideLoading();
//                    }
//                })
//                .subscribe(new Observer<FindForBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull FindForBean findForBean) {
//                        if (iView != null) {
//                            iView.onFindForPayData(findForBean);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        if (iView != null) {
//                            iView.Error(e.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//    }
//
//    public void getFindForSendData() {
//        RetrofitCreator.getShopApiService().getFindForSendData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(disposable -> {
//                    add(disposable);
//                    if (iView != null) {
//                        iView.showLoading();
//                    }
//                })
//                .doFinally(() -> {
//                    if (iView != null) {
//                        iView.hideLoading();
//                    }
//                })
//                .subscribe(new Observer<FindForBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull FindForBean findForBean) {
//                        if (iView != null) {
//                            iView.onFindForSendData(findForBean);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        if (iView != null) {
//                            iView.Error(e.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

}
