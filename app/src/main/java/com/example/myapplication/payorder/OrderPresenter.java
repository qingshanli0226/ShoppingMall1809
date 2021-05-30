package com.example.myapplication.payorder;

import com.example.framework.manager.CaCheMannager;
import com.example.net.RetrofitManager;
import com.example.net.bean.OrderinfoBean;
import com.example.net.bean.ShoppingCartBean;
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
    public void getOrderInfo(List<ShoppingCartBean.ResultBean> list){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonObject.put("subject","buy");
            jsonObject.put("totalPrice", CaCheMannager.getInstance().getShoppingPrice());
            for (ShoppingCartBean.ResultBean resultBean:list) {
                JSONObject object = new JSONObject();
                object.put("productName",resultBean.getProductName());
                object.put("productId",resultBean.getProductId());
                jsonArray.put(object);
            }
            jsonObject.put("body",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitManager.getApi().getOrderInfo(requestBody)
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderinfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OrderinfoBean orderinfoBean) {
                        mView.showLoading();
                        mView.onOrder(orderinfoBean);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                         mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
