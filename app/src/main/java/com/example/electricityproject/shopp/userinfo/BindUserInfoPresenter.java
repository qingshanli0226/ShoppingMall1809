package com.example.electricityproject.shopp.userinfo;

import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.UpdateAddress;
import com.example.common.bean.UpdatePhoneBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BindUserInfoPresenter extends BasePresenter<IBindUserInfoView> {

    public BindUserInfoPresenter(IBindUserInfoView iBindUserInfoView) {
        attachView(iBindUserInfoView);
    }

    public void postUpdatePhoneData(String phone){
        RetrofitCreate.getFiannceApiService()
                .setUpdatePhone(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribe(new Observer<UpdatePhoneBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull UpdatePhoneBean updatePhoneBean) {
                        if (updatePhoneBean.getCode().equals("200")){
                            IView.updatePhone(updatePhoneBean);
                            LogUtils.json(updatePhoneBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postUpdateAddressData(String address){

        RetrofitCreate.getFiannceApiService()
                .setUpdateAddress(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribe(new Observer<UpdateAddress>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull UpdateAddress updateAddress) {
                        if (updateAddress.getCode().equals("200")){
                            IView.updateAddress(updateAddress);
                            LogUtils.json(updateAddress);
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