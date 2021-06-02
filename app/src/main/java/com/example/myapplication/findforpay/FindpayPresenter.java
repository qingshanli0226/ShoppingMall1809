package com.example.myapplication.findforpay;

import com.example.common.log.LogUtil;
import com.example.framework.manager.PaySendCacheManager;
import com.example.net.retrofit.RetrofitManager;
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

public class FindpayPresenter extends BasePresenter<IFindPayView> {
    public FindpayPresenter(IFindPayView iFindPayView) {
        attView(iFindPayView);
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
                          if (mView!=null){
                              mView.onFindSend(findForSendBean);
                              PaySendCacheManager.getInstance().setFindForSendBean(findForSendBean);

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
}
