package com.example.threeshopping.particulars.detail;

import android.util.Log;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.CartBean;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.SelectBean;
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
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (mView != null) {
                            mView.onAddCart(selectBean);
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


<<<<<<< HEAD
  //检查数量
    public void inventory(CartBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .inventory(Integer.parseInt(resultBean.getProductId()),Integer.parseInt(resultBean.getProductNum()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        Log.i("zyb", "onNext: 成功" + selectBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("zyb错误", "onError: "+e);
                        Log.i("zyb", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
=======

>>>>>>> zzy


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
