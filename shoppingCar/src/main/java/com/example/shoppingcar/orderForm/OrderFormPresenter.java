package com.example.shoppingcar.orderForm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.commom.SignUtil;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.OrderInfoParamBean;
import com.example.net.model.OrderinfoBean;
import com.google.gson.Gson;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderFormPresenter extends BasePresenter<IOrderFormView> {
    public OrderFormPresenter(IOrderFormView iOrderFormView) {
        attachView(iOrderFormView);
    }

    public void getOrderInfo(OrderInfoParamBean orderInfoParamBean) {

//        String json = new Gson().toJson(orderInfoParamBean);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subject",orderInfoParamBean.getSubject());
        jsonObject.put("totalPrice",orderInfoParamBean.getTotalPrice());
        JSONArray objects = new JSONArray();
        for (OrderInfoParamBean.BodyBean bodyBean : orderInfoParamBean.getBody()) {
            JSONObject json = new JSONObject();
            json.put("productName",bodyBean.getProductName());
            json.put("productId",bodyBean.getProductId());
            String sign = SignUtil.generateJsonSign(json);
            json.put("sign",sign);
            SignUtil.encryptJsonParamsByBase64(json);
        }
        jsonObject.put("body",objects);

        String sign = SignUtil.generateJsonSign(jsonObject);
        jsonObject.put("sign",sign);

        SignUtil.encryptJsonParamsByBase64(jsonObject);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());


        RetrofitCreator.getShopApiService()
                .getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        if (iView != null) {
                            iView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView != null) {
                            iView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<OrderinfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OrderinfoBean orderinfoBean) {
                        if (iView != null) {
                            iView.onOrderInfo(orderinfoBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.Error(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
