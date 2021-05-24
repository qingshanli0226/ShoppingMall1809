package com.example.shoppingmall1809.main.type.category;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.ShopApiService;
import com.example.net.model.CategoryBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter extends BasePresenter<ICategoryView> {
    public CategoryPresenter(ICategoryView iCategoryView) {
        attachView(iCategoryView);
    }

    void getCategoryData(String url) {
        ShopApiService shopApiService = RetrofitCreator.getShopApiService();

        Observable<CategoryBean> observable = shopApiService.getTypeData(url);

        observable.subscribeOn(Schedulers.io())
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
                .subscribe(new Observer<CategoryBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CategoryBean categoryBean) {
                        if (iView != null) {
                            iView.onCategoryData(categoryBean);
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
