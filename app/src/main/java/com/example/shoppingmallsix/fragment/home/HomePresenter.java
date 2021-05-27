package com.example.shoppingmallsix.fragment.home;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.HomeBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView> {

    public HomePresenter(IHomeView iHomeView) {
        attachView(iHomeView);
    }

    public void getHomeDData() {

        RetrofitCreator.getFiannceApiService().getHoemData()
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (iView != null) {
                            iView.getHomeData(homeBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView != null) {
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
