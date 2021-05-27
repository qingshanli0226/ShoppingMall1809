package com.example.framework.manager;

import android.widget.Toast;

import com.example.net.AppMoudel;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;
import com.example.net.bean.AddShoppingCartBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShoppingCartBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

//缓存类
public class CaCheMannager {
    private static CaCheMannager mannager;

    public static CaCheMannager getInstance() {
        if (mannager == null) {
            mannager = new CaCheMannager();
        }
        return mannager;
    }

    private HomeBean homeBean;

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    //购物车存接口
    public interface IShoppingCartInterface {
        void onShoppinCartgData(List<ShoppingCartBean.ResultBean> shoppingCartBean);

        void onShoppingCartAdd();

//        void onShoppingCartInventory();//检查缓存

        void onShoppingCartSub();

        void onShoppingCartUpdata();
    }

    private List<IShoppingCartInterface> list = new ArrayList<>();
    private ShoppingCartBean shoppingCartBean;//购物车请求返回
    private List<ShoppingCartBean.ResultBean> shoppingCartBeanList;//购物车数据

    public ShoppingCartBean getShoppingCartBean() {
        return shoppingCartBean;
    }

    public void setShoppingCartBean(ShoppingCartBean shoppingCartBean) {
        this.shoppingCartBean = shoppingCartBean;
    }

    public List<ShoppingCartBean.ResultBean> getShoppingCartBeanList() {
        return shoppingCartBeanList;
    }

    public void setShoppingCartBeanList(List<ShoppingCartBean.ResultBean> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
    }

    //注册
    public void registerIShoppingCart(IShoppingCartInterface iShoppingCartInterface) {
        if (!list.contains(iShoppingCartInterface)) {
            list.add(iShoppingCartInterface);
        }
    }

    public void unregisterIShoppingCart(IShoppingCartInterface iShoppingCartInterface) {
        if (list.contains(iShoppingCartInterface)) {
            list.remove(iShoppingCartInterface);
        }
    }

    public void showShoppingData(){
        this.shoppingCartBean=getShoppingCartBean();
        for (IShoppingCartInterface iShoppingCartInterface:list) {
            iShoppingCartInterface.onShoppinCartgData(CaCheMannager.getInstance().getShoppingCartBeanList());
        }
    }

    //添加购物车数据
//    public synchronized void onShoppingCartAdd(String id, String num, String name, String url, String price) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("productId", id);
//            jsonObject.put("productNum", num);
//            jsonObject.put("productName", name);
//            jsonObject.put("url", url);
//            jsonObject.put("productPrice", price);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
//
//        RetrofitManager.getApi().getAddShoppingCart(body)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AddShoppingCartBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(@NonNull AddShoppingCartBean addShoppingCartBean) {
//                        if (addShoppingCartBean.getCode().equals("200")){//添加成功
//                            Toast.makeText(AppMoudel.getContext(), "添加成功", Toast.LENGTH_SHORT).show();
//                           getShoppingCart();
//                        }else { //添加失败
//                            Toast.makeText(AppMoudel.getContext(), "添加失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }
    //库存
//    public synchronized void onShoppingCartInventory(String id, String num,String name, String url, String price) {
//
//        RetrofitManager.getApi().getInventory(id,num)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<RegisterBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(@NonNull RegisterBean registerBean) {
//                        if (registerBean.getCode().equals("200")){//库存够
//                            Toast.makeText(AppMoudel.getContext(), "库存够", Toast.LENGTH_SHORT).show();
//                            onShoppingCartAdd(id,num,name,url,price);//添加购物车
//                        }else { //添加失败
//                            Toast.makeText(AppMoudel.getContext(), "库存不足 添加失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }


}
