package com.example.shoppingmallsix.fragment.shoppingcarfragment;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.ShoppingCarBean;

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
       RetrofitCreator.getFiannceApiService()
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
               .subscribe(new Observer<ShoppingCarBean>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onNext(@NonNull ShoppingCarBean shoppingCarBean) {
                       LogUtils.json(shoppingCarBean);
                       if (iView!=null){
                           iView.onShopping(shoppingCarBean);
                       }
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       if (iView!=null){
                           iView.showToast(e.getMessage());
                       }
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }
}
