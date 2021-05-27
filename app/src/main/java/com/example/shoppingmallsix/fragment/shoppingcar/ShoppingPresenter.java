package com.example.shoppingmallsix.fragment.shoppingcar;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.framework.manager.SoppingCartMemoryDataManager;
import com.example.net.RetrofitCreator;
import com.example.net.bean.Orbean;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.SelectAllProductBean;

import com.example.net.bean.business.UpdateProductSelectedBean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShoppingPresenter extends BasePresenter<IShopping> {

    public ShoppingPresenter(IShopping iShopping) {
        attachView(iShopping);
    }


    public void getConfiemserverpayresult(String outTradeNo,String result,boolean clientPayResult){


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("outTradeNo",outTradeNo);
            jsonObject.put("result",result);
            jsonObject.put("clientPayResult",clientPayResult);
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
                .subscribe(new Observer<ConfirmServerPayResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConfirmServerPayResultBean confirmServerPayResultBean) {
                        if (iView!=null){
                            iView.onConfiemserverpayresult(confirmServerPayResultBean);
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






    public void getShoppingData(){
       RetrofitCreator.getFiannceApiService()
               .getShortcartProductsBean()
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
               .subscribe(new Observer<GetShortcartProductsBean>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onNext(@NonNull GetShortcartProductsBean shoppingCarBean) {
                       LogUtils.json(shoppingCarBean);
                       if (iView!=null){
                           iView.onShopping(shoppingCarBean);
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


    public void getSelectAllProduct(boolean mBoolean){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected",mBoolean);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitCreator.getFiannceApiService()
               .getSelectAllProduct(requestBody)
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
               .subscribe(new Observer<SelectAllProductBean>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onNext(@NonNull SelectAllProductBean selectAllProductBean) {
                       LogUtils.json(selectAllProductBean);
                       if (iView!=null){
                           iView.onSelectAllProductBean(selectAllProductBean);
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

    public void getUpProductSelect(String productId,String productNum,String productName,String url,String productPrice,int position){

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
                .getUpdateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateProductSelectedBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdateProductSelectedBean selectedBean) {
                        if (iView!=null){
                            iView.onUpdateProductSelect(selectedBean,position);
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
}
