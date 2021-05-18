package com.example.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    protected V iView;

    private List<Disposable> disposableList = new ArrayList<>();

    public synchronized void add(Disposable disposable){
        disposableList.add(disposable);
    }

    public void attachView(V iView){
        this.iView = iView;
    }


    public void detachView(){
        if (this.iView!=null)
        this.iView =null;
        for (Disposable disposable : disposableList) {
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }


}
