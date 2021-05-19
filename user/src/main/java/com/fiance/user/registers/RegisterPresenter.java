package com.fiance.user.registers;

import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.RegisterBean;
import com.shoppingmall.net.model.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterView> {
      public RegisterPresenter(RegisterView registerView){
          attachView(registerView);
      }
      public void getLoginData(String name,String password){
          RetrofitCreate.getShoppingMallApiService().getRegisterData(name, password)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<RegisterBean>() {
                      @Override
                      public void onSubscribe(@NonNull Disposable d) {

                      }

                      @Override
                      public void onNext(@NonNull RegisterBean registerBean) {
                         iView.onRegisterData(registerBean);
                      }

                      @Override
                      public void onError(@NonNull Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
      }
}
