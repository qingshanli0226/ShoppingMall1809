package com.example.electricityproject.details;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.AddOneProductBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;
import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
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

    public void checkOneProductInventory(Map<String,Integer> map){

        String s = new Gson().toJson(map);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);

        RetrofitCreate.getFiannceApiService()
                .checkOneProductInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddOneProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull AddOneProductBean addOneProductBean) {
                        if (IView!=null){
                            IView.checkOneProductInventory(addOneProductBean);
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




}
