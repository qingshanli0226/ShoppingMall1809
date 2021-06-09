package com.example.user.login;

import com.example.common.SignUtil;
import com.example.net.retrofit.RetrofitManager;
import com.example.net.bean.LoginBean;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;

public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView iLoginView) {
        attView(iLoginView);
    }

    public void getLogin(String name, String password) {
        TreeMap<String, String> map = SignUtil.getEmptyTreeMap();//TreeMap遍历时是按照key字母的升序遍历出数据的
        map.put("name",name);
        map.put("password",password);
        String sign = SignUtil.generateSign(map);
        map.put("sign", sign);
        TreeMap<String,String> encryptParams = SignUtil.encryptParamsByBase64(map);
        RetrofitManager.getApi()
                .getLogin(encryptParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                    mView.showLoading();
                })
                .doFinally(() -> mView.hideLoading())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (mView != null) {
                            mView.onLogin(loginBean);
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
