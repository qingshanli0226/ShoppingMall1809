package com.shoppingmall.framework.mvp;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {
    private CompositeDisposable compositeDisposable;
    public synchronized void add(Disposable disposable){
        compositeDisposable.add(disposable);
    }
    protected V iView;

    public void attachView(V iView){
        this.iView = iView;
    }

    public void detachView(){
        this.iView = null;
        if (compositeDisposable==null){
            compositeDisposable.clear();
        }
    }
}
