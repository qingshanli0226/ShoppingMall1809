package com.example.shoppingmall1809.main.type.sort;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.HoemBean;
import com.example.net.model.SortBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SortPresenter extends BasePresenter<ISortView> {
    public SortPresenter(ISortView iSortView) {
        attachView(iSortView);
    }
    public void getSortData(){
        RetrofitCreator.getShopApiService().getSortData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                    if (iView != null) {
                        iView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (iView != null) {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<SortBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SortBean sortBean) {
                        if (iView != null) {
                            iView.getSortData(sortBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.Error(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
