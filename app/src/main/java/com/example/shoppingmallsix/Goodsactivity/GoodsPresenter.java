package com.example.shoppingmallsix.Goodsactivity;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.business.AddOneProductBean;
import com.example.net.bean.business.CheckInventoryBean;
import com.example.net.bean.business.UpdateProductNumBean;


import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class GoodsPresenter extends BasePresenter<IGoodsView> {
    public GoodsPresenter(IGoodsView iDetailView) {
        attachView(iDetailView);
    }

    public void addOneProduct(String productId,String productNum,String productName,
                              String url,String productPrice){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productNum",productNum);
            jsonObject.put("productName",productName);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitCreator.getFiannceApiService()
                .getAddOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddOneProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AddOneProductBean productBean) {
                        if (iView!=null){
                            iView.onAddCart(productBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void checkInventory(String productId,String productNum,String productName,
                               String url,String productPrice) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productNum",productNum);
            jsonObject.put("productName",productName);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResponseBody body = ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        RetrofitCreator.getFiannceApiService().getCheckInventory(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckInventoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CheckInventoryBean bean) {
                        if (iView!=null){
                            iView.onCheckInventory(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView != null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
