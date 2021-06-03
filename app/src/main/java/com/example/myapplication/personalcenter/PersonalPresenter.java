package com.example.myapplication.personalcenter;

import com.example.common.log.LogUtil;
import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.PaySendCacheManager;
import com.example.net.bean.OrderinfoBean;
import com.example.net.bean.ShoppingCartBean;
import com.example.net.retrofit.RetrofitManager;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PersonalPresenter extends BasePresenter<IPersonalView> {
    public PersonalPresenter(IPersonalView view) {
        attView(view);
    }
    public void getFindForPay(){
        RetrofitManager.getApi().getForPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (mView!=null){
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView!=null){
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {
                        if (mView!=null){
                            mView.onShoppingPay(findForPayBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            LogUtil.e(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public void onFindPay(){
        RetrofitManager.getApi()
                .getForPay()
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
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {
                        if (mView!=null){
                            PaySendCacheManager.getInstance().setFindForPayBean(findForPayBean);
                            LogUtil.d(findForPayBean.toString());
                           mView.ondend(findForPayBean);
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
                        mView.onShoppingSend(findForSendBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
