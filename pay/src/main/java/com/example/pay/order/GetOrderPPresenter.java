package com.example.pay.order;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.Orbean;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.find.FindForPayBean;
import com.example.net.bean.find.FindForSendbean;
import com.example.pay.demo.PayResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GetOrderPPresenter  extends BasePresenter<IGetorder> {

    public GetOrderPPresenter(IGetorder iGetorder) {
        attachView(iGetorder);
    }

    public void findForPay(){

        RetrofitCreator.getFiannceApiService()
                .getFindforpay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            iView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView!=null){
                            iView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindForPayBean findForPayBean) {
                        if (iView!=null){
                            iView.onfindForPay(findForPayBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    public void findForSend(){

        RetrofitCreator.getFiannceApiService()
                        .getFindforsend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            iView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView!=null){
                            iView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<FindForSendbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindForSendbean findForSendbean) {
                        if (iView!=null){
                            iView.onfindForSend(findForSendbean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (iView!=null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void getOrderinfo(String subject,String totalPrice,String productName,String productId){
        Orbean orbean = new Orbean();
        List<Orbean.BodyBean> body = new Orbean().getBody();
        Orbean.BodyBean bodyBean = new Orbean.BodyBean();
        bodyBean.setProductId(productId);
        bodyBean.setProductName(productName);
        body.add(bodyBean);
        orbean.setSubject(subject);
        orbean.setTotalPrice(totalPrice);
        orbean.setBody(body);
        String s = new Gson().toJson(orbean);

        RequestBody requestBody =  RequestBody.create(MediaType.parse("application/json;charset=utf-8"), s);
        RetrofitCreator.getFiannceApiService()
                .getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                           iView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView!=null){
                            iView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<GetOrderInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetOrderInfoBean getOrderInfoBean) {
                        if (iView!=null){
                            iView.onOrderinfo(getOrderInfoBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getConfiemserverpayresult(String outTradeNo, PayResult result, boolean clientPayResult) {


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("outTradeNo", outTradeNo);
            jsonObject.put("result", result);
            jsonObject.put("clientPayResult", clientPayResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreator.getFiannceApiService()
                .getConfirmServerPayResult(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
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
                .subscribe(new Observer<ConfirmServerPayResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConfirmServerPayResultBean confirmServerPayResultBean) {
                        if (iView != null) {
                            iView.onConfirmServerPayResult(confirmServerPayResultBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView != null) {
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
