package com.example.electricityproject.shopp;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.CheckInventoryBean;
import com.example.common.bean.OrderBean;
import com.example.common.bean.OrderInfoBean;
import com.example.common.bean.RegBean;
import com.example.common.bean.RemoveManyProductBean;
import com.example.common.bean.RemoveOneProductBean;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.bean.UpdateProductNumBean;
import com.example.framework.BasePresenter;
import com.example.manager.ShopCacheManger;
import com.example.net.RetrofitCreate;
import com.google.gson.Gson;

import org.json.JSONArray;
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

    //检查选中的商品库存
    public void checkInventory(List<OrderBean> list) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (OrderBean orderBean : list) {
                JSONObject object = new JSONObject();
                object.put("productId",orderBean.getProductId());
                object.put("productNum",orderBean.getProductNum());
                object.put("productName",orderBean.getProductName());
                object.put("url",orderBean.getUrl());
                jsonArray.put(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());
        RetrofitCreate.getFiannceApiService().setCheckInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        IView.showLoading();
                    }
                })
                .subscribe(new Observer<CheckInventoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CheckInventoryBean bean) {
                        IView.checkInventory(bean);
                        LogUtils.json(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //全选
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
    public void selectOntProductData(boolean isSelect){
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
                            IView.postSelectOneProductData(selectAllProductBean);
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
    //更新商品数据
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
    //判断库存
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
    //删除一个
    public void getRemoveOneShopBean(String productId, String productName, String productNum, String url, String productPrice){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productName",productName);
            jsonObject.put("productNum",productNum);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreate.getFiannceApiService()
                .setRemoveOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RemoveOneProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RemoveOneProductBean removeOneProductBean) {
                        if (IView!=null){
                            IView.removeOneShop(removeOneProductBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void getRemoveManyShopBean(List<ShortcartProductBean.ResultBean> list){
        String json = new Gson().toJson(list);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);

        RetrofitCreate.getFiannceApiService()
                .setRemoveManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RemoveManyProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RemoveManyProductBean removeManyProductBean) {
                        if (IView!=null){
                            IView.removeManyShop(removeManyProductBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getOrderInfo(List<ShortcartProductBean.ResultBean> list){

        JSONObject object = new JSONObject();
        JSONArray body = new JSONArray();
        try {
            object.put("subject","buy");
            object.put("totalPrice", ShopCacheManger.getInstance().getMoneyValue()+"");
            for (ShortcartProductBean.ResultBean resultBean : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("productName",resultBean.getProductName());
                jsonObject.put("productId",resultBean.getProductId());
                body.put(jsonObject);
            }
            object.put("body",body);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());

        RetrofitCreate.getFiannceApiService()
                .GetOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderInfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OrderInfoBean orderInfoBean) {
                        if (IView!=null){
                            IView.getOrderInfoData(orderInfoBean);
                            LogUtils.json(orderInfoBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
