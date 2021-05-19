package com.fiannce.bawei.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    private List<Disposable> disposableList = new ArrayList<>();

    public synchronized void add(Disposable disposable) {
        disposableList.add(disposable);
    }

    protected V iView;

    public void attachView(V iView) {
        this.iView = iView;
    }

    public void detachView() {
        this.iView = null;//当页面销毁时，需要解除关联，避免内存泄漏

        //当页面销毁时，将未结束的线程停掉，否则会影响性能
        for(Disposable disposable:disposableList) {
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }
}
