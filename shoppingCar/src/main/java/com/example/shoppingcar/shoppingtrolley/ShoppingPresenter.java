package com.example.shoppingcar.shoppingtrolley;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.ShoppingTrolleyBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShoppingPresenter extends BasePresenter<IShopping> {
    public ShoppingPresenter(IShopping iShopping) {
        attachView(iShopping);
    }

    public void getShoppingData(){
       RetrofitCreator.getShopApiService()
               .getShoppingTrolley()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .doOnSubscribe(new Consumer<Disposable>() {
                   @Override
                   public void accept(Disposable disposable) throws Exception {
                       if (iView!=null){
                           iView.showLoading();
                       }
                   }
               })
               .doFinally(new Action() {
                   @Override
                   public void run() throws Exception {
                       if (iView!=null){
                           iView.hideLoading();
                       }
                   }
               })
               .subscribe(new Observer<ShoppingTrolleyBean>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onNext(@NonNull ShoppingTrolleyBean shoppingTrolleyBean) {
                       LogUtils.json(shoppingTrolleyBean);
                       if (iView!=null){
                           iView.onShopping(shoppingTrolleyBean);
                       }
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       if (iView!=null){
                           iView.Error(e.getMessage());
                       }
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }
}
