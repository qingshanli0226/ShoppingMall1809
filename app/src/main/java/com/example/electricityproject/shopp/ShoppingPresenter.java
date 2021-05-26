package com.example.electricityproject.shopp;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.RegBean;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.bean.UpdateProductNumBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

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

public
class ShoppingPresenter extends BasePresenter<IShoppingView> {


    public ShoppingPresenter(IShoppingView iShoppingView) {
        attachView(iShoppingView);
    }

    public void getShortProductsData() {
        RetrofitCreate.getFiannceApiService()
                .getShortProductData()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        IView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        IView.hideLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShortcartProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull ShortcartProductBean shortcartProductBean) {
                        if (IView != null) {
                            IView.getShortProductData(shortcartProductBean);
                            LogUtils.json(shortcartProductBean);
                            List<ShortcartProductBean.ResultBean> result = shortcartProductBean.getResult();
                            for (int i = 0; i < result.size(); i++) {
                                Log.i("zrf", "onNext: " + result.get(i).getProductPrice());
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView != null) {
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void postSelectAllProductData(boolean isSelect) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreate.getFiannceApiService()
                .SelectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectAllProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull SelectAllProductBean selectAllProductBean) {
                        if (IView != null) {
                            IView.postSelectAllProductData(selectAllProductBean);
                            LogUtils.json(selectAllProductBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView != null) {
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUpdateProduct(String productId, String productNum, String productName, String url, String productPrice) {
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
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitCreate.getFiannceApiService()
                .setUpdateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateProductNumBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdateProductNumBean updateProductNumBean) {
                        if (IView != null) {
                            IView.amendProductData(updateProductNumBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView != null) {
                            IView.showError(e.getMessage() + "");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    public void getCheckShopBean(String productId,String productNum){
        RetrofitCreate.getFiannceApiService()
                .checkOneProductInventory(productId,productNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegBean regBean) {
                        if (IView!=null){
                            IView.CheckProductData(regBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage()+"");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
