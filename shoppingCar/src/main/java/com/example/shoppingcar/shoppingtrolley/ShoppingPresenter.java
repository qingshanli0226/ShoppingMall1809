package com.example.shoppingcar.shoppingtrolley;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.example.commom.SignUtil;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.DeleteBean;
import com.example.net.model.OrderInfoParamBean;
import com.example.net.model.OrderinfoBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.List;
import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ShoppingPresenter extends BasePresenter<IShoppingView> {
    public ShoppingPresenter(IShoppingView iShopping) {
        attachView(iShopping);
    }


    public void getUpDateSelected(String productId, boolean productSelected, String productName, String url, String productPrice) {

//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("productId", productId);
//            jsonObject.put("productSelected", productSelected);
//            jsonObject.put("productName", productName);
//            jsonObject.put("url", url);
//            jsonObject.put("productPrice", productPrice);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("productId", productId);
        jsonObject.put("productSelected", productSelected);
        jsonObject.put("productName", productName);
        jsonObject.put("url", url);
        jsonObject.put("productPrice", productPrice);
        String sign = SignUtil.generateJsonSign(jsonObject);
        jsonObject.put("sign", sign);
        SignUtil.encryptJsonParamsByBase64(jsonObject);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());

        RetrofitCreator.getShopApiService()
                .getUpdateProductSelected(requestBody)
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
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.onUpDateSelected(registerBean);
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

    public void getSelectAllProduct(boolean selected) {

//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("selected", selected);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("selected", selected);
        String sign = SignUtil.generateJsonSign(jsonObject);
        jsonObject.put("sign", sign);
        SignUtil.encryptJsonParamsByBase64(jsonObject);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());

        RetrofitCreator.getShopApiService()
                .getSelectAllProduct(requestBody)
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
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.onSelectAllProduct(registerBean);
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


    public void getRemoveManyProduct(List<DeleteBean> delete) {
//        String json = new Gson().toJson(delete);

        JSONArray objects = new JSONArray();
        for (DeleteBean deleteBean : delete) {
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            jsonObject.put("productId",deleteBean.getProductId());
            jsonObject.put("productNum",deleteBean.getProductNum());
            jsonObject.put("productName",deleteBean.getProductName());
            String sign = SignUtil.generateJsonSign(jsonObject);
            jsonObject.put("sign", sign);
            SignUtil.encryptJsonParamsByBase64(jsonObject);

            objects.add(jsonObject);
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), objects.toString());

        RetrofitCreator.getShopApiService()
                .getRemoveManyProduct(requestBody)
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
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.onRemoveManyProduct(registerBean);
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

    public void getCheckInventory(List<DeleteBean> enough) {
//        String json = new Gson().toJson(enough);

        JSONArray objects = new JSONArray();
        for (DeleteBean deleteBean : enough) {
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            jsonObject.put("productId",deleteBean.getProductId());
            jsonObject.put("productNum",deleteBean.getProductNum());
            jsonObject.put("productName",deleteBean.getProductName());
            String sign = SignUtil.generateJsonSign(jsonObject);
            jsonObject.put("sign", sign);
            SignUtil.encryptJsonParamsByBase64(jsonObject);

            objects.add(jsonObject);
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), objects.toString());

        RetrofitCreator.getShopApiService()
                .getCheckInventory(requestBody)
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
                .subscribe(new Observer<ShoppingTrolleyBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ShoppingTrolleyBean shoppingTrolleyBean) {
                        if (iView != null) {
                            iView.onCheckInventory(shoppingTrolleyBean);
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


    public void getUpDateProductNum(String productId, String productNum, String productName, String url, String productPrice) {

//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("productId", productId);
//            jsonObject.put("productNum", productNum);
//            jsonObject.put("productName", productName);
//            jsonObject.put("url", url);
//            jsonObject.put("productPrice", productPrice);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("productId", productId);
        jsonObject.put("productNum", productNum);
        jsonObject.put("productName", productName);
        jsonObject.put("url", url);
        jsonObject.put("productPrice", productPrice);
        String sign = SignUtil.generateJsonSign(jsonObject);
        jsonObject.put("sign", sign);
        SignUtil.encryptJsonParamsByBase64(jsonObject);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());

        RetrofitCreator.getShopApiService()
                .getUpDateProductNum(requestBody)
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
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.onUpDateProductNum(registerBean);
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


    public void getCheckOneProductInventory(String productId, String productNum) {

        TreeMap<String, String> map = SignUtil.getEmptyTreeMap();
        map.put("productId", productId);
        map.put("productNum", productNum);
        String sign = SignUtil.generateSign(map);
        map.put("sign", sign);
        TreeMap<String, String> encryptParamsByBase = SignUtil.encryptParamsByBase64(map);


        RetrofitCreator.getShopApiService()
                .getCheckOneProductInventory(encryptParamsByBase)
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
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.onCheckOneProductInventory(registerBean);
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
