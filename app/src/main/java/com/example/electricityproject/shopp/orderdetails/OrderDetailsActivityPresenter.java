package com.example.electricityproject.shopp.orderdetails;

import com.example.common.LogUtils;
import com.example.common.bean.ConfirmServerPayResultBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;
import com.example.pay.alipay.sdk.pay.demo.PayResult;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderDetailsActivityPresenter extends BasePresenter<OrderDetailsActivityIView> {

    public OrderDetailsActivityPresenter(OrderDetailsActivityIView orderDetailsActivityIView) {
        attachView(orderDetailsActivityIView);
    }

    public void confirmServerPayResult(String outTradeNo, PayResult result, Boolean clientPayResult) {
        JSONObject object = new JSONObject();
        try {
            object.put("outTradeNo",outTradeNo);
            object.put("result",result.getResult());
            object.put("clientPayResult",clientPayResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        RetrofitCreate.getFiannceApiService().setConfirmServerPayResult(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConfirmServerPayResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConfirmServerPayResultBean bean) {
                        IView.onConfirmServerPayResultOk(bean);
                        LogUtils.i(bean.getCode());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
