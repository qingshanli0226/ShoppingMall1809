package com.example.shoppingmallsix.fragment.classify.frgment.lab;


import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.TabBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LabPresenter extends BasePresenter<ILabView> {

    public LabPresenter(ILabView iLabView){
        attachView(iLabView);
    }
    public void getLabData(){
        RetrofitCreator.getFiannceApiService()
                .getTabData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        if (iView !=null){
                            iView.onTabBean(tabBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
