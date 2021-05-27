package com.shoppingmall.detail;

import com.google.gson.Gson;
import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.SelectBean;
import com.shoppingmall.net.bean.ShopCarBean;
import com.shoppingmall.net.model.RetrofitCreate;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DetailPresenter extends BasePresenter<IDetailView> {
    public DetailPresenter(IDetailView iDetailView) {
        attachView(iDetailView);
    }

    public void addProduct(ProductBean productBean) {
        String s = new Gson().toJson(productBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitCreate.getShoppingMallApiService()
                .addProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (iView != null) {
                            iView.addProduct(selectBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    //检查数量
    public void inventory(ShopCarBean.ResultBean resultBean) {
        RetrofitCreate.getShoppingMallApiService()
                .checkProduct(Integer.parseInt(resultBean.getProductId()),Integer.parseInt(resultBean.getProductNum()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (iView != null){
                            iView.checkProduct(selectBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null){
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
