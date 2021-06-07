package com.example.shoppingmallsix.address;

import androidx.annotation.NonNull;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.user.UpdateAddressBean;
import com.example.net.bean.user.UpdatePhoneBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddressPresenter extends BasePresenter<IAddress> {
    public AddressPresenter(IAddress iAddress) {
        attachView(iAddress);
    }

    public void getUpDataPhone(String phone){
        RetrofitCreator.getFiannceApiService()
                .getUpdatePhone(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdatePhoneBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdatePhoneBean updatePhoneBean) {
                        if (iView!=null){
                            iView.onUpDataPhone(updatePhoneBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUpDataAddress(String address){
        RetrofitCreator.getShopApiService()
                .getUpDataAddress(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView!=null){
                            iView.onUpDataAddress(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.Error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
