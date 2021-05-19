package com.example.electricityproject.home;

import com.example.common.bean.HomeBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
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
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                        IView.onHomeBanner(homeBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        IView.showError(e.getMessage()+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
