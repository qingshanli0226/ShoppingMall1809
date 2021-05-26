package com.example.shoppingcar.shoppingtrolley;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.DeleteBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
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
import okhttp3.ResponseBody;

public class ShoppingPresenter extends BasePresenter<IShoppingView> {
    public ShoppingPresenter(IShoppingView iShopping) {
        attachView(iShopping);
    }


    public void getUpDateSelected(String productId, boolean productSelected, String productName, String url, String productPrice) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productSelected", productSelected);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());

        RetrofitCreator.getShopApiService()
                .getUpdateProductSelected(requestBody)
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

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", selected);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());

        RetrofitCreator.getShopApiService()
                .getSelectAllProduct(requestBody)
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


    public void getRemoveManyProduct( List<DeleteBean> delete ){
        String json = new Gson().toJson(delete);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json.toString());

        RetrofitCreator.getShopApiService()
                .getRemoveManyProduct(requestBody)
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

    public void getCheckInventory( List<DeleteBean> enough ){
        String json = new Gson().toJson(enough);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json.toString());

        RetrofitCreator.getShopApiService()
                .getCheckInventory(requestBody)
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


    public void getUpDateProductNum(String productId, String productNum, String productName, String url, String productPrice){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", productNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonObject.toString());

        RetrofitCreator.getShopApiService()
                .getUpDateProductNum(requestBody)
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


    public void getCheckOneProductInventory(String productId,String productNum){

        RetrofitCreator.getShopApiService()
                .getCheckOneProductInventory(productId,productNum)
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
