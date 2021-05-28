package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.mode.FocusBean;
import com.fiannce.bawei.net.mode.VersionBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//定义一MVVM框架的Model类，该类类似于MVP框架的Presenter类，在该类里获取数据后，把数据存储在
//LiveData实例里。LiveData通过回调接口将数据传递到UI这块.其中这个回调接口不需要用户实现，这个回调接口
//是LiveData本身就已经实现了
public class ShopModel extends ViewModel {

    //LiveData可以监听页面的生命周期，当页面销毁时，页面注册到LiveData的Observer匿名内部类会被自动在LiveData里销毁，解除关联，不会造成内存泄漏问题
    private MutableLiveData<MVVMBean> liveData = new MutableLiveData<>();


    public void getUpdateVersion() {
        RetrofitCreator.getFiannceApiService().getServerVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        MVVMBean mvvmBean = new MVVMBean(0);
                        mvvmBean.setStatus(1);
                    }
                })
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {
                        MVVMBean mvvmBean = new MVVMBean(1);
                        mvvmBean.setVersionBean(versionBean);
                        getLiveData().setValue(mvvmBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getFocusBean() {
        RetrofitCreator.getFiannceApiService().findFocus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FocusBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FocusBean focusBean) {
                        MVVMBean mvvmBean = new MVVMBean(2);
                        mvvmBean.setFocusBean(focusBean);
                        getLiveData().setValue(mvvmBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<MVVMBean> getLiveData() {
        return liveData;
    }
}
