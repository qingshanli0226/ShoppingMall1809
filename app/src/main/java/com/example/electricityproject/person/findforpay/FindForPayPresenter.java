package com.example.electricityproject.person.findforpay;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.FindForPayBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public
class FindForPayPresenter extends BasePresenter<IFindForPayView> {

    public FindForPayPresenter(IFindForPayView iPersonView) {
        attachView(iPersonView);
    }

    public void getForPayData(){

        RetrofitCreate.getFiannceApiService()
                .getFindForPayData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        IView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        IView.hideLoading();
                    }
                })
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {
                        if (findForPayBean!=null){
                            IView.getFindForPayData(findForPayBean);
                            LogUtils.json(findForPayBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("zrf", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
