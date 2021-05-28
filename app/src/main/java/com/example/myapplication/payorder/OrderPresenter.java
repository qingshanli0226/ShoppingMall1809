package com.example.myapplication.payorder;

import com.example.net.RetrofitManager;
import com.example.net.bean.OrderinfoBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderPresenter extends BasePresenter<IOrderView> {
    public OrderPresenter(IOrderView iOrderView) {
        attView(iOrderView);
    }
    public void getOrder(String subject, String totalPrice, List<OrderinfoBean.BodyBean> list){
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            OrderinfoBean.BodyBean bodyBean = list.get(i);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId",bodyBean.getProductId());
                jsonObject.put("productNum",bodyBean.getProductNum());
                jsonObject.put("productName",bodyBean.getProductName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=utf-8"), jsonArray.toString());
        RetrofitManager.getApi()
                .getOrderInfo(subject,totalPrice,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderinfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OrderinfoBean orderinfoBean) {
                     if (mView!=null){
                         mView.onOrder(orderinfoBean);
                     }
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
