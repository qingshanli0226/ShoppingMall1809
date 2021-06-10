package com.example.shoppingcar.addOneProduct;

import com.blankj.utilcode.util.LogUtils;
import com.example.commom.SignUtil;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.RegisterBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class AddOnrProductPresenter extends BasePresenter<IAddOneProduct> {
    public AddOnrProductPresenter(IAddOneProduct iAddOneProduct) {
        attachView(iAddOneProduct);
    }

    public void AddOneProduct(String productId, String productNum, String productName,
                              String url, String productPrice) {

//        JSONObject jsonObject = new JSONObject();
//
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
        jsonObject.put("productSelected", false);

        String sign = SignUtil.generateJsonSign(jsonObject);
        jsonObject.put("sign", sign);
        SignUtil.encryptJsonParamsByBase64(jsonObject);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreator.getShopApiService()
                .addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView != null) {
                            iView.onAddOneProduct(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.Error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void CheckOneProductInventory(String productId, String productNum) {

        TreeMap<String, String> map = SignUtil.getEmptyTreeMap();
        map.put("productId",productId);
        map.put("productNum",productNum);
        String sign = SignUtil.generateSign(map);
        map.put("sign",sign);
        TreeMap<String, String> encryptParamsByBase = SignUtil.encryptParamsByBase64(map);

        RetrofitCreator.getShopApiService()
                .getCheckOneProductInventory(encryptParamsByBase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                            iView.Error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void UpDateProductNum(String productId, String productNum, String productName,
                                 String url, String productPrice) {

//        JSONObject jsonObject = new JSONObject();
//
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

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitCreator.getShopApiService()
                .getUpDateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                            iView.Error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
