package com.example.electricityproject.welcome;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.ShortcartProductBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public
class WelcomePresenter extends BasePresenter<IWelcomeView> {

    public WelcomePresenter(IWelcomeView iWelcomeView) {
        attachView(iWelcomeView);
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
                            List<ShortcartProductBean.ResultBean> result = shortcartProductBean.getResult();
                            for (int i = 0; i < result.size(); i++) {
                                Log.i("zrf", "onNext: "+result.get(i).getProductPrice());
                            }
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
