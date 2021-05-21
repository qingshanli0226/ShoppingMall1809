package com.example.threeshopping.type.typechild.classify;

import com.example.framework.BasePresenter;
import com.example.net.IHttpApiService;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;
import com.example.net.bean.TypeBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ClassPrensenter extends BasePresenter<IClassView> {
    public ClassPrensenter(IClassView classView) {
        attchView(classView);
    }
    public void getSkirt(int position){


        IHttpApiService httpApiService = RetrofitManager.getHttpApiService();
        Observable<TypeBean> skirt=null;
        switch (position){
            case 0:
                skirt = httpApiService.getSkirt();
                break;
            case 1:
                skirt = httpApiService.getJacket();
                break;
            case 2:
                skirt = httpApiService.getPants();
                break;
            case 3:
                skirt = httpApiService.getOvercoat();
                break;
            case 4:
                skirt = httpApiService.getAccessory();
                break;
            case 5:
                skirt = httpApiService.getBag();
                break;
            case 6:
                skirt = httpApiService.getDress();
                break;
            case 7:
                skirt = httpApiService.getProducts();
                break;
            case 8:
                skirt = httpApiService.getStationery();
                break;
            case 9:
                skirt = httpApiService.getDigit();
                break;
            case 10:
                skirt = httpApiService.getGame();
                break;

        }

        skirt.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        if (mView != null) {
                            mView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<TypeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TypeBean typeBean) {
                        if (mView != null) {
                            mView.onType(typeBean);
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
