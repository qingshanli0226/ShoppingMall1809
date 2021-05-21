package com.example.electricityproject.shopp;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.IDetailsView;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public
class ShoppingPresenter extends BasePresenter<IShoppingView> {


    public ShoppingPresenter(IShoppingView iShoppingView) {
        attachView(iShoppingView);
    }


    public void getShortProductsData(){
        RetrofitCreate.getFiannceApiService()
                .getShortProductData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShortcartProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull ShortcartProductBean shortcartProductBean) {
                        if (IView!=null){
                            IView.getShortProductData(shortcartProductBean);
                            LogUtils.json(shortcartProductBean);
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
