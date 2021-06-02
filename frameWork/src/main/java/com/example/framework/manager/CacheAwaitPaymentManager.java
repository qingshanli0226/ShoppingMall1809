package com.example.framework.manager;

import com.example.net.RetrofitManager;
import com.example.net.bean.AwaitPaymentBean;
import com.example.net.bean.AwaitPaymentBean.ResultBean;
import com.example.net.bean.CartBean;
import com.example.net.bean.LoginBean;

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

    private CacheAwaitPaymentManager() {

    }

    public synchronized static CacheAwaitPaymentManager getInstance() {
        if (cacheAwaitPaymentManager == null) {
            cacheAwaitPaymentManager = new CacheAwaitPaymentManager();
        }
        return cacheAwaitPaymentManager;
    }

    public void init(){
        iUserChange = new UserManager.IUserChange() {
            @Override
            public void onUserChange(LoginBean loginBean) {
                getAwaitPay();
            }
        };
        UserManager.getInstance().registerLogin(iUserChange);
    }

    private UserManager.IUserChange iUserChange;
    private List<ResultBean> awaitPayment = new ArrayList<>();
    private List<IAwaitPay> iAwaitPays = new LinkedList<>();


    public List<ResultBean> getAwaitPayment() {
        return awaitPayment;
    }

    public void setAwaitPayment(List<ResultBean> awaitPayment) {
        this.awaitPayment.clear();
        this.awaitPayment.addAll(awaitPayment)  ;
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
                            setAwaitPayment(awaitPaymentBean.getResult());
                            notifyPay(awaitPayment);
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

    public synchronized void notifyPay(List<AwaitPaymentBean.ResultBean> awaitPayment){
        for (IAwaitPay iAwaitPay : iAwaitPays) {
            iAwaitPay.onAwaitPay(awaitPayment);
        }
    }

    public interface IAwaitPay {
        void onAwaitPay(List<AwaitPaymentBean.ResultBean> awaitPayment);
    }

    public void destory(){
        UserManager.getInstance().unregisterLogin(iUserChange);
    }
}
