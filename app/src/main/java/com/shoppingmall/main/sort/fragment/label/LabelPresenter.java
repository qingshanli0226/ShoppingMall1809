package com.shoppingmall.main.sort.fragment.label;

import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.LabelBean;
import com.shoppingmall.net.model.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LabelPresenter extends BasePresenter<ILabelView> {
    public LabelPresenter(ILabelView iLabelView){
        attachView(iLabelView);
    }
    public void getLabelData(){
        RetrofitCreate.getShoppingMallApiService().getLabelData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LabelBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LabelBean labelBean) {
                     iView.getLabelData(labelBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
