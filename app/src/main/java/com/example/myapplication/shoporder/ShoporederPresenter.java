package com.example.myapplication.shoporder;

import com.example.net.bean.ConfirmServerPayResultBean;
import com.example.net.retrofit.RetrofitManager;
import com.example.pay.PayResult;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvp.presenter.BasePresenter;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShoporederPresenter extends BasePresenter<Iorder> {
    public ShoporederPresenter(Iorder iorder) {
        attView(iorder);
    }
    public void confrimserver(String outTradeNo, PayResult result,Boolean clientPayResult){
        JSONObject object = new JSONObject();
        try {
            object.put("outTradeNo",outTradeNo);
            object.put("result",result);
            object.put("clientPayResult",clientPayResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        RetrofitManager.getApi().getConfirmserver(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConfirmServerPayResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConfirmServerPayResultBean bean) {
                        mView.onConfirmServer(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
