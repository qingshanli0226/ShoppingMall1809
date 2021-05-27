package com.example.electricityproject.home;

import com.example.common.bean.HomeBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<CallHomeData> {

    public HomePresenter(CallHomeData callHomeData) {
        attachView(callHomeData);
    }
    public void getHomeBannerData(){
        RetrofitCreate.getFiannceApiService()
                .getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (IView!=null){
                            IView.showLoading();
                            add(disposable);
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }


                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                        if (IView!=null) {
                            IView.onHomeBanner(homeBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null) {
                            IView.showError(e.getMessage() + "");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
