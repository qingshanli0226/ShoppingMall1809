package com.example.electricityproject.details;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.AddOneProductBean;
import com.example.common.bean.RegBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;
import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public
class DetailsPresenter extends BasePresenter<IDetailsView> {
    public DetailsPresenter(IDetailsView iDetailsView) {
        attachView(iDetailsView);
    }

    public void postAddOneProduct(Map<String,String> map){

        String s = new Gson().toJson(map);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);

        RetrofitCreate.getFiannceApiService()
                .setAddOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (IView!=null){
                            add(disposable);
                            IView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<AddOneProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull AddOneProductBean addOneProductBean) {
                        if (IView!=null){
                            IView.getAddOneProduct(addOneProductBean);
                            LogUtils.json(addOneProductBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void checkOneProductInventory(String productId,String productNum){

        RetrofitCreate.getFiannceApiService()
                .checkOneProductInventory(productId,productNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (IView!=null){
                            IView.showLoading();
                            add(disposable);
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull RegBean checkInventoryBean) {
                        if (IView!=null){
                            IView.checkOneProductInventory(checkInventoryBean);
                            LogUtils.json(checkInventoryBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }




}
