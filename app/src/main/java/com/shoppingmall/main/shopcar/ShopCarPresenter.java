package com.shoppingmall.main.shopcar;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.shoppingmall.framework.manager.CacheShopManager;
import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.CheckProductBean;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.PayBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.SelectBean;
import com.shoppingmall.net.bean.ShopCarBean;
import com.shoppingmall.net.model.RetrofitCreate;

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

public class ShopCarPresenter extends BasePresenter<IShopCarView> {
    public ShopCarPresenter(IShopCarView iShopCarView) {
        attachView(iShopCarView);
    }


    //单选
    public synchronized void updateProductSelect(int position,ShopCarBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");

        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitCreate.getShoppingMallApiService()
                .updateProductSelect(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            //更改数据
                            CacheShopManager.getInstance().setCheck(position,resultBean.isProductSelected());
                            if (iView != null) {
                                iView.onCheck(position,CacheShopManager.getInstance().getCarts().get(position).isProductSelected());
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //全选
    public synchronized void selectAll(boolean isAll) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isAll);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");

        RequestBody requestBody = RequestBody.create(parse, jsonObject.toString());
        RetrofitCreate.getShoppingMallApiService()
                .selectAll(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            //全选更改数据源
                            CacheShopManager.getInstance().setCheckAll(isAll);
                            if (iView != null) {
                                iView.onCheckAll(isAll);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //检查数量
    public synchronized void inventory(int position,ShopCarBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitCreate.getShoppingMallApiService()
                .checkProduct(Integer.parseInt(resultBean.getProductId()),
                        Integer.parseInt(resultBean.getProductNum()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            //服务端
                            upDateNum(position,resultBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //更新服务数量
    public void upDateNum(int position,ShopCarBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitCreate.getShoppingMallApiService()
                .updateProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            if (iView != null) {
                                CacheShopManager.getInstance().setNum(position,resultBean.getProductNum());
                                iView.onNum(position);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //删除一个
    public void removeOneProduct(int position,ShopCarBean.ResultBean resultBean){
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitCreate.getShoppingMallApiService()
                .removeOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            CacheShopManager.getInstance().removeProduct(resultBean);
                            if (iView != null) {
                                iView.removeProduct(position);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //删除多个
    public void removeMany(List<ShopCarBean.ResultBean> resultBeans){
        String s = new Gson().toJson(resultBeans);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitCreate.getShoppingMallApiService()
                .removeMany(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            CacheShopManager.getInstance().removeMany(resultBeans);
                            if (iView != null) {
                                iView.removeMany(resultBeans);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
