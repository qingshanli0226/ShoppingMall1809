package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.ClassBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Url;

public class ClassPresenter extends BasePresenter<IClassView> {

    public ClassPresenter(IClassView view){
        attachView(view);
    }
    public void getClassData(String url,boolean mBoolean){
        RetrofitCreator.getFiannceApiService()
                .getClassData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        if (!mBoolean){
                            iView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (!mBoolean){
                            iView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<ClassBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassBean classBean) {
                        if (iView !=null){
                            iView.onClassData(classBean,mBoolean,url);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView !=null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
