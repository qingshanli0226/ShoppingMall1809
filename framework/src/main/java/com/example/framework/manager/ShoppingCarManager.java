package com.example.framework.manager;

import android.os.UserManager;

import com.blankj.utilcode.util.LogUtils;
import com.example.net.RetrofitCreator;
import com.example.net.model.FindForBean;
import com.example.net.model.LoginBean;
import com.example.net.model.OrderInfoParamBean;
import com.example.net.model.ShoppingTrolleyBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCarManager implements ShopeUserManager.IUserLoginChanged {


    //监听登录
    @Override
    public void onLoginChange(LoginBean loginBean) {
        getShoppingData();
        getFindForPayData();
        getFindForSendData();

    }
    //注册登录的方法
    public void initLogin() {
        ShopeUserManager.getInstance().register(this::onLoginChange);
    }

    //待付款
    List<FindForBean.ResultBean> forPay= new ArrayList<>();
    //待发货
    List<FindForBean.ResultBean> forSend = new ArrayList<>();

    public void deleteForPayLast(FindForBean.ResultBean bean){
        forPay.remove(bean);
    }

    public List<FindForBean.ResultBean> getForPay() {
        return forPay;
    }

    public void addForPay(FindForBean.ResultBean bean) {
        forPay.add(bean);
    }

    public void setForPay(List<FindForBean.ResultBean> forPay) {
        this.forPay = forPay;
    }

    public List<FindForBean.ResultBean> getForSend() {
        return forSend;
    }

    public void setForSend(List<FindForBean.ResultBean> forSend) {
        this.forSend = forSend;
    }

    public void addForSend(FindForBean.ResultBean bean) {
        forSend.add(bean);
    }

    public void getFindForPayData() {
        RetrofitCreator.getShopApiService().getFindForPayData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindForBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForBean findForBean) {
                        setForPay(findForBean.getResult());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getFindForSendData() {
        RetrofitCreator.getShopApiService().getFindForSendData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindForBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForBean findForBean) {
                        setForSend(findForBean.getResult());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    //购物车集合
    private List<ShoppingTrolleyBean.ResultBean> result;

    public List<ShoppingTrolleyBean.ResultBean> getResult() {
        return result;
    }

    //初始化购物车集合
    private synchronized void initResult(List<ShoppingTrolleyBean.ResultBean> result) {
        this.result = result;
        for (IShoppingCar iShoppingCar : list) {
            iShoppingCar.onShoppingCarAdapter(result);
        }
        refreshData();
    }

    //添加购物车集合
    public synchronized void insertResultBean(ShoppingTrolleyBean.ResultBean resultBean) {
        result.add(resultBean);
        refreshData();
    }

    //删除购物车集合
    public synchronized void deleteResultBean(ShoppingTrolleyBean.ResultBean resultBean) {
        result.remove(resultBean);
        refreshData();
    }

    //修改购物车集合
    public synchronized void upDataResultBean(ShoppingTrolleyBean.ResultBean resultBean) {
        for (ShoppingTrolleyBean.ResultBean bean : result) {
            if (bean.getProductId().equals(resultBean.getProductId())) {
                bean.setProductNum(resultBean.getProductNum());
            }
        }
        refreshData();
    }

    //查询集合
    public synchronized ShoppingTrolleyBean.ResultBean selectResultBean(String productId) {
        for (ShoppingTrolleyBean.ResultBean bean : result) {
            if (bean.getProductId().equals(productId)) {
                return bean;
            }
        }
        return null;
    }

    //全选
    public synchronized boolean checkAll() {
        for (ShoppingTrolleyBean.ResultBean resultBean : result) {
            resultBean.setProductSelected(true);
        }
        refreshData();
        return true;
    }

    //取消全选
    public synchronized boolean unCheckAll() {
        for (ShoppingTrolleyBean.ResultBean resultBean : result) {
            resultBean.setProductSelected(false);
        }
        refreshData();
        return true;
    }

    //删除一部分
    public synchronized void deletePartResult() {
        for (int i = result.size() - 1; i >= 0; i--) {
            if (result.get(i).isProductSelected()){
                result.remove(i);
            }
        }
        refreshData();
    }

    //单例
    private static ShoppingCarManager shoppingCarManager;

    private ShoppingCarManager() {
    }

    public static ShoppingCarManager getInstance() {
        if (shoppingCarManager == null) {
            shoppingCarManager = new ShoppingCarManager();
        }
        return shoppingCarManager;
    }

    //接口回调
    private List<IShoppingCar> list = new ArrayList<>();

    public interface IShoppingCar {
        void onShoppingCar(List<ShoppingTrolleyBean.ResultBean> result);

        void onShoppingCarAdapter(List<ShoppingTrolleyBean.ResultBean> result);
    }

    //注册接口
    public synchronized void register(IShoppingCar iShoppingCar) {
        list.add(iShoppingCar);
    }

    //取消注册接口
    public synchronized void unregister(IShoppingCar iShoppingCar) {
        list.remove(iShoppingCar);
    }

    //刷新接口回调
    public synchronized void refreshData() {
        for (IShoppingCar iShoppingCar : list) {
            iShoppingCar.onShoppingCarAdapter(result);
            iShoppingCar.onShoppingCar(result);
        }
    }

    private void getShoppingData() {
        RetrofitCreator.getShopApiService()
                .getShoppingTrolley()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                })
                .subscribe(new Observer<ShoppingTrolleyBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ShoppingTrolleyBean shoppingTrolleyBean) {
                        initResult(shoppingTrolleyBean.getResult());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private ArrayList<ShoppingTrolleyBean.ResultBean> deleteBean =new ArrayList<>();


    public ArrayList<ShoppingTrolleyBean.ResultBean> addDeleteBean() {
        deleteBean.clear();
        for (ShoppingTrolleyBean.ResultBean resultBean : result) {
            if (resultBean.isProductSelected()) {
                deleteBean.add(resultBean);
            }
        }
        return deleteBean;
    }


    public ArrayList<ShoppingTrolleyBean.ResultBean> getDeleteBean() {
        return deleteBean;
    }

    public void setDeleteBean(ArrayList<ShoppingTrolleyBean.ResultBean> deleteBean) {
        this.deleteBean = deleteBean;
    }
}
