package com.example.user.auto;

import android.util.Log;

import com.example.common.bean.LogBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AutoPresenter extends BasePresenter<IAutoView> {

    public AutoPresenter(IAutoView iAutoView) {
        attachView(iAutoView);
    }
    public void getAutoData(String token){

        RetrofitCreate.getFiannceApiService()
                .postAutoLogin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LogBean logBean) {
                        Log.i("zx", "onNext: "+logBean.toString());
                        IView.onAutoData(logBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        IView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
