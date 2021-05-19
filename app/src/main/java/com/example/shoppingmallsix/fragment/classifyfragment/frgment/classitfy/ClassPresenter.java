package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.JacketBean;
import com.example.net.bean.PantsBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassPresenter extends BasePresenter<IClassView> {

    public ClassPresenter(IClassView view){
        attachView(view);
    }
    public void getJacketData(){
        RetrofitCreator.getFiannceApiService()
                .getJacketData()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JacketBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JacketBean jacketBean) {
                            if (iView !=null){
                                iView.onJacketData(jacketBean);
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
    public void getPantsBean(){
        RetrofitCreator.getFiannceApiService()
                .getPantsData()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PantsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PantsBean pantsBean) {
                        if (iView !=null){
                            iView.onPantsBean(pantsBean);
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
