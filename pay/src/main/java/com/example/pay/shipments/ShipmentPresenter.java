package com.example.pay.shipments;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.ShipmentBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
/**
 *代发货
 * 赵子裕
 */
public class ShipmentPresenter extends BasePresenter<IShipmentView> {

    public ShipmentPresenter(IShipmentView iShipmentView) {
        attchView(iShipmentView);
    }

    public void getpay(){
        RetrofitManager.getHttpApiService()
                .getShipment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        if (mView!= null){
                            mView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView!= null){
                            mView.hideLoading();
                        }

                    }
                })
                .subscribe(new Observer<ShipmentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext( ShipmentBean shipmentBean) {
                        if (mView != null) {
                            mView.onShipment(shipmentBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
