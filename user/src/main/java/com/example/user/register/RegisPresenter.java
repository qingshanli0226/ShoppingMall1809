package com.example.user.register;

import com.example.common.SignUtil;
import com.example.net.retrofit.RetrofitManager;
import com.example.net.bean.RegisterBean;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;

public class RegisPresenter extends BasePresenter<IRegisterView> {

    public RegisPresenter(IRegisterView iRegisterView) {
        attView(iRegisterView);
    }

    public void getRegister(String name, String password) {
        TreeMap<String, String> map = SignUtil.getEmptyTreeMap();//TreeMap遍历时是按照key字母的升序遍历出数据的
        map.put("name",name);
        map.put("password",password);
        String sign = SignUtil.generateSign(map);
        map.put("sign", sign);
        TreeMap<String,String> encryptParams = SignUtil.encryptParamsByBase64(map);
        RetrofitManager.getApi()
                .getRegister(encryptParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                    mView.showLoading();
                })
                .doFinally(() -> mView.hideLoading())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.OnRegister(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
