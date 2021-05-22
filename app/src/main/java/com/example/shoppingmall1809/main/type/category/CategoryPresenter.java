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

    void getCategoryData(int position) {
        ShopApiService shopApiService = RetrofitCreator.getShopApiService();

        Observable<CategoryBean> observable;

        switch (position) {
            case 1:
                observable = shopApiService.getJacketData();
                break;
            case 2:
                observable = shopApiService.getPantsData();
                break;
            case 3:
                observable = shopApiService.getOvercoatData();
                break;
            case 4:
                observable = shopApiService.getAccessoryData();
                break;
            case 5:
                observable = shopApiService.getBugData();
                break;
            case 6:
                observable = shopApiService.getDressData();
                break;
            case 7:
                observable = shopApiService.getProductsData();
                break;
            case 8:
                observable = shopApiService.getStationeryData();
                break;
            case 9:
                observable = shopApiService.getDigitData();
                break;
            case 10:
                observable = shopApiService.getGameData();
                break;
            default:
                observable = shopApiService.getSkirtData();
                break;
        }

        if (observable == null) {
            if (iView != null) {
                iView.Error("对象为空");
            }
            return;
        }

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
