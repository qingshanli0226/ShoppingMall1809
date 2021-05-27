package com.example.shoppingmallsix.fragment.shoppingcar;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.framework.manager.SoppingCartMemoryDataManager;
import com.example.net.RetrofitCreator;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.RemoveManyProductBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.business.UpdateProductSelectedBean;
import com.example.shoppingmallsix.fragment.shoppingcar.bean.DeletesDataBean;
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


    public void getSelectAllProduct(boolean mBoolean,boolean mBooleans){

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
                           iView.onSelectAllProductBean(selectAllProductBean,mBooleans);
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
    public void removeManyProduct(List<GetShortcartProductsBean.ResultBean> resultBeans){
        String s = new Gson().toJson(resultBeans);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), s);
        RetrofitCreator.getFiannceApiService()
                .removeManyProduct(requestBody)
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
                .subscribe(new Observer<RemoveManyProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RemoveManyProductBean removeManyProductBean) {
                        if (iView!=null){
                            iView.onRemoveManyProductBean(removeManyProductBean);
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
