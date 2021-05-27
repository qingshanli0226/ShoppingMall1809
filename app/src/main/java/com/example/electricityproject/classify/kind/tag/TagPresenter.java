package com.example.electricityproject.classify.kind.tag;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.ClassifyBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TagPresenter extends BasePresenter<ITagView> {

    public TagPresenter(ITagView iTagView) {
        attachView(iTagView);
    }

    public void getTagData(){
        RetrofitCreate.getFiannceApiService()
                .getTagData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (IView!=null){
                            IView.showLoading();
                            add(disposable);
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<ClassifyBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull ClassifyBean classifyBean) {
                        if (IView!=null){
                            IView.onTagData(classifyBean);
                            LogUtils.json(classifyBean);
                            Log.i("zrfs", "onNext: "+classifyBean.getResult().toString());
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
