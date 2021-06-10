package com.example.pay;

import com.blankj.utilcode.util.LogUtils;
import com.example.commom.SignUtil;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.OrderInfoParamBean;
import com.example.net.model.OrderinfoBean;
import com.example.net.model.RegisterBean;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PayPresenter extends BasePresenter<IPayView> {
    public PayPresenter(IPayView iPayView) {
        attachView(iPayView);
    }

    public void getConfirmServerPayResult(String outTradeNo,String result,boolean clientPayResult) {
//        LogUtils.e("开始请求验证成功");
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//            jsonObject.put("outTradeNo",outTradeNo);
//            jsonObject.put("result",result);
//            jsonObject.put("clientPayResult",clientPayResult);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("outTradeNo",outTradeNo);
        jsonObject.put("result",result);
        jsonObject.put("clientPayResult",clientPayResult);
        String sign = SignUtil.generateJsonSign(jsonObject);
        jsonObject.put("sign", sign);
        SignUtil.encryptJsonParamsByBase64(jsonObject);



        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());


        RetrofitCreator.getShopApiService()
                .getConfirmServerPayResult(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                    if (iView != null) {
                        iView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (iView != null) {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.getConfirmServerPayResult(registerBean);
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
