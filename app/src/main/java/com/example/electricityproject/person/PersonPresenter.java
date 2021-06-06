package com.example.electricityproject.person;

import com.example.common.bean.OutLogBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonPresenter extends BasePresenter<IoutloginView> {
    public PersonPresenter(IoutloginView ioutloginView) {
        attachView(ioutloginView);
    }
    public void outLogin(){
        RetrofitCreate.getFiannceApiService()
                .outLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OutLogBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OutLogBean outLogBean) {
                        if (IView!=null){
                            IView.outLogin(outLogBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
