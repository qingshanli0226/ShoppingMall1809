package com.fiance.user.logins;

import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.LoginBean;
import com.shoppingmall.net.model.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {
      public LoginPresenter(LoginView loginView){
          attachView(loginView);
      }
      public void getLoginData(String name,String password){
          RetrofitCreate.getShoppingMallApiService().getLoginData(name, password)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<LoginBean>() {
                      @Override
                      public void onSubscribe(@NonNull Disposable d) {

                      }

                      @Override
                      public void onNext(@NonNull LoginBean loginBean) {
                         iView.onLoginData(loginBean);
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
