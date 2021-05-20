package com.example.threeshopping.type.typechild.label;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.LabelBean;
import com.example.net.bean.LoginBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LabelPresenter extends BasePresenter<ILabelView> {

    public LabelPresenter(ILabelView labelView) {
        attchView(labelView);
    }

    public void getLabel(){
        RetrofitManager.getHttpApiService()
                .getLabel()
                .delay(3, TimeUnit.SECONDS)
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
                .subscribe(new Observer<LabelBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LabelBean labelBean) {
                        if (mView != null){
                            mView.onILabel(labelBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null){
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
