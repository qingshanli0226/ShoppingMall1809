package com.shoppingmall.main.shopcar;

import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.HomeBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.ShopCarBean;
import com.shoppingmall.net.model.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShopCarPresenter extends BasePresenter<IShopCarView> {
    public ShopCarPresenter(IShopCarView iShopCarView){
        attachView(iShopCarView);
    }

    public void getShopCarData(){
        RetrofitCreate.getShoppingMallApiService()
                .getShopCarProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //该函数当RxJava发起网络请求时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //将disposable存储起来，当页面销毁时，可以通过它去判断当前获取数据的线程是否已经停止，如果没有停止，则停止
                        add(disposable);
                        iView.showLoading();
                    }
                })
                //当网络请求结束时回调该方法
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<ShopCarBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ShopCarBean shopCarBean) {
                        if (iView!=null){
                            iView.getShopCarData(shopCarBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
