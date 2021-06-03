package com.example.electricityproject.person.dropshipment;

import com.example.common.bean.FindForSendBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DropShipmentPresenter extends BasePresenter<IDropShipmentView> {
    public DropShipmentPresenter(IDropShipmentView dropShipmentView ) {
        attachView(dropShipmentView);
    }
    public void getDropShipment(){
        RetrofitCreate.getFiannceApiService()
                .getFindSendData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (IView!=null){
                            add(disposable);
                            IView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<FindForSendBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForSendBean findForSendBean) {
                        if (IView!=null){
                            IView.onDropShipmentBean(findForSendBean);
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
