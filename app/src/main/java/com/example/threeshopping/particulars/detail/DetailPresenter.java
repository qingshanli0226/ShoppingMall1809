package com.example.threeshopping.particulars.detail;

import android.util.Log;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.UpdateProductNumBean;
import com.google.gson.Gson;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class DetailPresenter extends BasePresenter<IDetailView> {
    public DetailPresenter(IDetailView iDetailView) {
        attchView(iDetailView);
    }

    public void addProduct(ProductBean productBean) {
        String s = new Gson().toJson(productBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .addProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ProductBean productBean) {
                        if (mView != null) {
                            mView.onAddCart(productBean);
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


    public void checkInventory(ProductBean productBean){
        String s = new Gson().toJson(productBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        ResponseBody responseBody = ResponseBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .inventory(responseBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductBean productBean) {
                        if (mView != null){
                            mView.onInventory(productBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null){
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    public void UpdateProductNum(){
//        RetrofitManager.getHttpApiService()
//                .UpdateProductNum()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<UpdateProductNumBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(UpdateProductNumBean updateProductNumBean) {
//                        if (mView != null){
//                            mView.onUpdateProductNum(updateProductNumBean);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (mView != null){
//                            mView.showError(e.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
