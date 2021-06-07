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
                .getUpDataPhone(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdatePhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdatePhoneBean updatePhoneBean) {
                        if (iView!=null){
                            iView.onUpDataPhone(updatePhoneBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUpDataAddress(String address){
        RetrofitCreator.getFiannceApiService()
                .getUpdateAddress(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateAddressBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdateAddressBean updateAddressBean) {
                        if (iView!=null){
                            iView.onUpDataAddress(updateAddressBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
