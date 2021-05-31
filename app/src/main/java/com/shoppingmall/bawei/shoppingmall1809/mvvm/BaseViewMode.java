package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewMode<T>  extends ViewModel {
    //LiveData可以监听页面的生命周期，当页面销毁时，页面注册到LiveData的Observer匿名内部类会被自动在LiveData里销毁，解除关联，不会造成内存泄漏问题

    protected MutableLiveData<T> liveData = new MutableLiveData<>();

    protected MutableLiveData<T> getLiveData() {
        return liveData;
    }
}
