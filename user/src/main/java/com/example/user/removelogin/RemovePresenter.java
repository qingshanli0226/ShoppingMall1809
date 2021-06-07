package com.example.user.removelogin;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.RegisterBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RemovePresenter extends BasePresenter<IRemoveView> {
    public RemovePresenter(IRemoveView iUserView) {
        attachView(iUserView);
    }

    public void getUnlockData(){
        RetrofitCreator.getShopApiService()
                .getLogout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean unlockBean) {
                        if (iView!=null){
                            iView.onUserData(unlockBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.Error(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
