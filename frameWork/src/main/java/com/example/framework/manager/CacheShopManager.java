package com.example.framework.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.MainThread;

import com.example.common.LogUtil;
import com.example.net.BuildConfig;
import com.example.net.RetrofitManager;
import com.example.net.bean.CartBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.SelectBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CacheShopManager {
    private static CacheShopManager cacheShopManager;

    private CacheShopManager() {

    }

    public synchronized static CacheShopManager getInstance() {
        if (cacheShopManager == null) {
            cacheShopManager = new CacheShopManager();
        }
        return cacheShopManager;
    }

    public void init(){
        UserManager.getInstance().registerLogin(new UserManager.IUserChange() {
            @Override
            public void onUserChange(LoginBean loginBean) {
                LogUtil.d("登录");

                getShowCart();
            }
        });
    }

    //购物车数据源
    private List<CartBean.ResultBean> carts;
    private List<ICartChange> cartChanges = new LinkedList<>();


    //购物车数据源
    public synchronized void getShowCart() {
        RetrofitManager.getHttpApiService()
                .showCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CartBean cartBean) {
                        if (cartBean.getCode().equals("200")) {
                            notifyCarts(cartBean.getResult());
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

    //单选
    public synchronized void updateProductSelect(int position,CartBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");

        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
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
                            carts.get(position).setProductSelected(resultBean.isProductSelected());
                            notifyCheck(position,resultBean.isProductSelected());
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
        RetrofitManager.getHttpApiService()
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
                            setChackAll(isAll);
                            notifyCheckAll(isAll);
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
    public synchronized void inventory(int position,CartBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .inventory(Integer.parseInt(resultBean.getProductId()),Integer.parseInt(resultBean.getProductNum()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            //修改库存
                            carts.get(position).setProductNum(resultBean.getProductNum());
                            //服务端
                            upDateNum(position,resultBean);
                        }
                        Log.i("zyb", "onNext: 成功" + selectBean);
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
    public void upDateNum(int position,CartBean.ResultBean resultBean) {
        String s = new Gson().toJson(resultBean);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService()
                .updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectBean selectBean) {
                        if (selectBean.getCode().equals("200")) {
                            //通知更改数量
                            notifyNum(position);
                        } else{

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


    public synchronized void registerCart(ICartChange iCartChange) {
        cartChanges.add(iCartChange);
    }

    public synchronized void unRegisterCart(ICartChange iCartChange) {
        cartChanges.remove(iCartChange);
    }

    public List<CartBean.ResultBean> getCarts() {
        return carts;
    }
    //发送信息
    public synchronized void notifyCarts(List<CartBean.ResultBean> carts) {
        this.carts = carts;
        for (ICartChange cartChange : cartChanges) {
            cartChange.onShowCart(carts);
        }

    }
    //单选通知
    public synchronized void notifyCheck(int position,boolean isCheck){
        for (ICartChange cartChange : cartChanges) {
            cartChange.onCheck(position,isCheck);
        }
    }
    //添加数据
    public synchronized void addData(CartBean.ResultBean resultBean){
        int count = 0;
        int position = -1;
        for (int i = 0; i < carts.size(); i++) {
            CartBean.ResultBean cart = carts.get(i);
            if (cart.getProductId().equals(resultBean.getProductId())) {
                int cartNum = Integer.parseInt(cart.getProductNum());
                int resultNum = Integer.parseInt(resultBean.getProductNum());
                cart.setProductNum(cartNum+resultNum+"");
                position = i;
            } else{
                count++;
            }
        }
        if(count == carts.size()){
            carts.add(resultBean);
            position = carts.size();
        }
        //通知
//        notifyAdd(position);
        notifyCarts(carts);
    }
    //添加通知
    public synchronized void notifyAdd(int position){
        for (ICartChange cartChange : cartChanges) {
            cartChange.onAddCart(position);
        }
    }
    
    //全选
    public synchronized void setChackAll(boolean isCheckAll){
        for (CartBean.ResultBean cart : carts) {
            cart.setProductSelected(isCheckAll);
        }
    }
    
    //全选通知
    public synchronized void notifyCheckAll(boolean isCheckAll){
        for (ICartChange cartChange : cartChanges) {
            cartChange.onCheckAll(isCheckAll);
        }
    }
    //通知更改数量
    public synchronized void notifyNum(int position){
        for (ICartChange cartChange : cartChanges) {
            cartChange.onNum(position);
        }
    }


    public interface ICartChange {
        void onShowCart(List<CartBean.ResultBean> carts);
        void onAddCart(int position);
        void onCheck(int position,boolean isCheck);
        void onCheckAll(boolean isChcekAll);
        void onNum(int position);
    }


}
