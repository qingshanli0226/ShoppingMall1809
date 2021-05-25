package com.shoppingmall.detail;

import com.google.gson.Gson;
import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.model.RetrofitCreate;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DetailPresenter extends BasePresenter<IDetailView> {
    public DetailPresenter(IDetailView iDetailView){
        attachView(iDetailView);
    }

    public void addProduct(String id,int num,String name,String url,String price){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",id);
            jsonObject.put("productNum",num);
            jsonObject.put("productName",name);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",price);
            jsonObject.put("productSelected",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, jsonObject.toString());
        RetrofitCreate.getShoppingMallApiService()
                .addProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //该函数当RxJava发起网络请求时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //将disposable存储起来，当页面销毁时，可以通过它去判断当前获取数据的线程是否已经停止，如果没有停止，则停止
                        add(disposable);
                        iView.showLoading();
                    }
                })
                //当网络请求结束时回调该方法
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<AddProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AddProductBean addProductBean) {
                        if (iView!=null){
                            iView.addProduct(addProductBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
