package com.example.electricityproject.classify.kind.type;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.KindAccessoryBean;
import com.example.common.bean.KindBagBean;
import com.example.common.bean.KindDigitBean;
import com.example.common.bean.KindDressBean;
import com.example.common.bean.KindGameBean;
import com.example.common.bean.KindHomeProductsBean;
import com.example.common.bean.KindJacketBean;
import com.example.common.bean.KindOvercoatBean;
import com.example.common.bean.KindPantsBean;
import com.example.common.bean.KindSkirtBean;
import com.example.common.bean.KindStationeryBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public
class IKindPresenter extends BasePresenter<IKindView> {

    public IKindPresenter(IKindView iKindView) {
        attachView(iKindView);
    }

    public void getSkirtData(){
        RetrofitCreate.getFiannceApiService()
                .getSkirtData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindSkirtBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindSkirtBean kindSkirtBean) {
                        if (IView!=null){
                            IView.onSkirtData(kindSkirtBean);
                            LogUtils.json(kindSkirtBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getJacketData(){
        RetrofitCreate.getFiannceApiService()
                .getJacketData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindJacketBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindJacketBean jacketBean) {
                        if (IView!=null){
                            IView.onJacketData(jacketBean);
                            LogUtils.json(jacketBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPantData(){
        RetrofitCreate.getFiannceApiService()
                .getPantsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindPantsBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindPantsBean kindPantsBean) {
                        if (IView!=null){
                            IView.onPantData(kindPantsBean);
                            LogUtils.json(kindPantsBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getOvercoatData(){
        RetrofitCreate.getFiannceApiService()
                .getOvercoatData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindOvercoatBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindOvercoatBean overcoatBean) {
                        if (IView!=null){
                            IView.onOvercoatData(overcoatBean);
                            LogUtils.json(overcoatBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAccessoryData(){
        RetrofitCreate.getFiannceApiService()
                .getAccessoryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindAccessoryBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindAccessoryBean accessoryBean) {
                        if (IView!=null){
                            IView.onAccessoryData(accessoryBean);
                            LogUtils.json(accessoryBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBagData(){
        RetrofitCreate.getFiannceApiService()
                .getBagData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindBagBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindBagBean bagBean) {
                        if (IView!=null){
                            IView.onBagData(bagBean);
                            LogUtils.json(bagBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDressData(){
        RetrofitCreate.getFiannceApiService()
                .getDressData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindDressBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindDressBean kindDressBean) {
                        if (IView!=null){
                            IView.onDressBean(kindDressBean);
                            LogUtils.json(kindDressBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHomeProducts(){
        RetrofitCreate.getFiannceApiService()
                .getHomeProductsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindHomeProductsBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindHomeProductsBean kindHomeProductsBean) {
                        if (IView!=null){
                            IView.onHomeProductsBean(kindHomeProductsBean);
                            LogUtils.json(kindHomeProductsBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getStationeryData(){
        RetrofitCreate.getFiannceApiService()
                .getStationeryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindStationeryBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindStationeryBean kindStationeryBean) {
                        if (IView!=null){
                            IView.onStationeryBean(kindStationeryBean);
                            LogUtils.json(kindStationeryBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDigitData(){
        RetrofitCreate.getFiannceApiService()
                .getDigitData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindDigitBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindDigitBean kindDigitBean) {
                        if (IView!=null){
                            IView.onDigitBean(kindDigitBean);
                            LogUtils.json(kindDigitBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGameData(){
        RetrofitCreate.getFiannceApiService()
                .getGameData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindGameBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull KindGameBean kindGameBean) {
                        if (IView!=null){
                            IView.onGameBean(kindGameBean);
                            LogUtils.json(kindGameBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
