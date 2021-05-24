package com.example.threeshopping.welcome;

import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView> {
    public HomePresenter(IHomeView iHomeView) {
        attchView(iHomeView);
    }
    public void getHome(){
        RetrofitManager.getHttpApiService()
                .getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                        if (mView != null) {
                            mView.onHome(homeBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
