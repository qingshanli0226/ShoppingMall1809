package com.example.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {
    private List<Disposable> list = new ArrayList<>();
    public synchronized void add(Disposable disposable){
        list.add(disposable);
    }

    protected V mView;
    public void attchView(V mView){
        this.mView = mView;
    }

    public void detachView(){
        this.mView = null;
        for (Disposable disposable : list) {
            if(disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
