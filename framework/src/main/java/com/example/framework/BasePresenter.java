package com.example.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    private List<Disposable> disposableList = new ArrayList<>();
    protected V iView;

    protected synchronized void add(Disposable disposable) {
        disposableList.add(disposable);
    }

    protected void attachView(V iView) {
        this.iView = iView;
    }

    protected void detachView() {
        this.iView = null;

        for (Disposable disposable : disposableList) {
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }
}
