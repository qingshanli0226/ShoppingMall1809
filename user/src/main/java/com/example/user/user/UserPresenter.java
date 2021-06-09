package com.example.user.user;

import com.example.common.SignUtil;
import com.example.framework.BasePresenter;
import com.example.framework.manager.CacheConnectManager;
import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter  extends BasePresenter<IUserView> {

    public UserPresenter(IUserView iUserView) {
        attchView(iUserView);
    }


    public void getRegister(String name,String password){
//        TreeMap<String, String> treeMap = new TreeMap<>();
//        treeMap.put("name",name);
//        treeMap.put("password",password);
//        String sign = SignUtil.generateSign(treeMap);
//        treeMap.put("sign",sign);
//        TreeMap<String, String> map = SignUtil.encryptParamsByBase64(treeMap);
//         .getRegister(map)
        RetrofitManager.getHttpApiService()
                .getRegister(name,password)
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
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.onRegister(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage().toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getLogin(String name,String password){

//        TreeMap<String, String> treeMap = new TreeMap<>();
//        treeMap.put("name",name);
//        treeMap.put("password",password);
//        String sign = SignUtil.generateSign(treeMap);
//        treeMap.put("sign",sign);
//        TreeMap<String, String> map = SignUtil.encryptParamsByBase64(treeMap);


        RetrofitManager.getHttpApiService()
                .getLogin(name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);

                        if (mView!= null){
                            mView.showLoading();
                        }

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView!= null){
                            mView.hideLoading();
                        }

                    }
                })
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
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
