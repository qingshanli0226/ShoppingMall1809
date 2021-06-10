package com.example.user.register;

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

public class RegisterPresneter extends BasePresenter<IRegisterView> {
    public RegisterPresneter(IRegisterView iRegisterView) {
        attachView(iRegisterView);
    }

    public void getRegisterData(String user, String pwd) {

        TreeMap<String, String> map = SignUtil.getEmptyTreeMap();
        map.put("name",user);
        map.put("password",pwd);
        String sign = SignUtil.generateSign(map);
        map.put("sign",sign);

        TreeMap<String, String> encryptParamsByBase = SignUtil.encryptParamsByBase64(map);


        RetrofitCreator.getShopApiService()
                .getRegisterData(encryptParamsByBase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                })
                .doFinally(() -> {

                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.onRegisterData(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null)
                            iView.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
