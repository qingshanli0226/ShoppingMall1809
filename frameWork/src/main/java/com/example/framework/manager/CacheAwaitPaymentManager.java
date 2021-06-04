package com.example.framework.manager;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.LogUtil;
import com.example.net.RetrofitManager;
import com.example.net.bean.AwaitPaymentBean;
import com.example.net.bean.AwaitPaymentBean.ResultBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ShipmentBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheAwaitPaymentManager {

    private static CacheAwaitPaymentManager cacheAwaitPaymentManager;
    private CacheUserManager.IUserChange iUserChange;
    //待支付
    private List<AwaitPaymentBean.ResultBean> Awaitpayment = new ArrayList<>();
    private List<IAwaitPay> iAwaitPays = new LinkedList<>();
    //代发货
    private List<ShipmentBean.ResultBean> Shipment = new ArrayList<>();
    private List<IShip> iShips = new LinkedList<>();

    private CacheAwaitPaymentManager() {
    }

    public  static CacheAwaitPaymentManager getInstance() {
        if (cacheAwaitPaymentManager == null) {
            synchronized (CacheAwaitPaymentManager.class){
                if (cacheAwaitPaymentManager == null) {
                    cacheAwaitPaymentManager = new CacheAwaitPaymentManager();
                }
            }
        }
        return cacheAwaitPaymentManager;
    }

    public void init(){
        iUserChange = new CacheUserManager.IUserChange() {
            @Override
            public void onUserChange(LoginBean loginBean) {
                getAwaitPay();
                getShip();
            }
        };
        CacheUserManager.getInstance().registerLogin(iUserChange);
    }


    //待支付
    public List<ResultBean> getAwaitpayment() {
        return Awaitpayment;
    }

    public void setAwaitpayment(List<ResultBean> awaitpayment) {
        this.Awaitpayment.addAll(awaitpayment) ;

    }

    public synchronized void registerPay(IAwaitPay iAwaitPay) {
        iAwaitPays.add(iAwaitPay);
    }

    public synchronized void unRegisterPay(IAwaitPay iAwaitPay) {
        iAwaitPays.remove(iAwaitPay);
    }


    public synchronized void getAwaitPay() {
        RetrofitManager.getHttpApiService()
                .getAwaitPayment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AwaitPaymentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AwaitPaymentBean awaitPaymentBean) {
                        if (awaitPaymentBean.getCode().equals("200")) {
                            setAwaitpayment(awaitPaymentBean.getResult());
                            LogUtil.i("awaitPaymentBean"+awaitPaymentBean.getResult());
                            notifyPay(Awaitpayment);
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

    public synchronized void addPay(AwaitPaymentBean.ResultBean resultBean){
        Awaitpayment.add(resultBean);
        notifyAdd(Awaitpayment.size()-1);
    }

    public synchronized void notifyPay(List<AwaitPaymentBean.ResultBean> awaitPayment){
        for (IAwaitPay iAwaitPay : iAwaitPays) {
            iAwaitPay.onAwaitPay(awaitPayment);
        }
    }

    public synchronized void notifyAdd(int position){
        for (IAwaitPay iAwaitPay : iAwaitPays) {
            iAwaitPay.onAddPay(position);
        }
    }

    public interface IAwaitPay {
        void onAwaitPay(List<AwaitPaymentBean.ResultBean> awaitPayment);
        void onAddPay(int position);
    }

    //代发货
    public List<ShipmentBean.ResultBean> getShipment() {
        return Shipment;
    }

    public void setShipment(List<ShipmentBean.ResultBean> shipment) {
        this.Shipment.addAll(shipment);
    }

    public synchronized void registerShip(IShip iShip) {
        iShips.add(iShip);
    }

    public synchronized void unRegisterShip(IShip iShip) {
        iShips.remove(iShip);
    }


    public synchronized void getShip() {
        RetrofitManager.getHttpApiService()
                .getShipment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShipmentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext( ShipmentBean shipmentBean) {
                       if (shipmentBean.getCode().equals("200")){
                           setShipment(shipmentBean.getResult());
                           notifyShip(Shipment);
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

    public synchronized void addShip(ShipmentBean.ResultBean resultBean){
        Shipment.add(resultBean);
        notifyAddShip(Shipment.size()-1);
    }


    public synchronized void notifyShip(List<ShipmentBean.ResultBean> shipment){
        for (IShip iShip : iShips) {
            iShip.onShip(shipment);
        }
    }

    public synchronized void notifyAddShip(int position){
        for (IShip iShip : iShips) {
            iShip.onAddShip(position);
        }
    }

    public interface IShip{
        void onShip(List<ShipmentBean.ResultBean> shipment);
        void onAddShip(int position);
    }

}
