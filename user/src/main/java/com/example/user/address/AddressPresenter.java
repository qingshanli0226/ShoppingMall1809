package com.example.user.address;

import com.example.commom.SignUtil;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.RegisterBean;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddressPresenter extends BasePresenter<IAddress> {
    public AddressPresenter(IAddress iAddress) {
        attachView(iAddress);
    }

    public void getUpDataPhone(String phone){
        TreeMap<String, String> map = SignUtil.getEmptyTreeMap();
        map.put("phone",phone);
        String sign = SignUtil.generateSign(map);
        map.put("sign",sign);
        TreeMap<String, String> encryptParamsByBase = SignUtil.encryptParamsByBase64(map);

        RetrofitCreator.getShopApiService()
                .getUpDataPhone(encryptParamsByBase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView!=null){
                            iView.onUpDataPhone(registerBean);
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

    public void getUpDataAddress(String address){

        TreeMap<String, String> map = SignUtil.getEmptyTreeMap();
        map.put("address",address);
        String sign = SignUtil.generateSign(map);
        map.put("sign",sign);
        TreeMap<String, String> encryptParamsByBase = SignUtil.encryptParamsByBase64(map);

        RetrofitCreator.getShopApiService()
                .getUpDataAddress(encryptParamsByBase)
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
