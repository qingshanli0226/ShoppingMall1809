package com.example.electricityproject.person.findforpay;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.bean.ConfirmServerPayResultBean;
import com.example.common.bean.FindForPayBean;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreate;
import com.example.pay.PayResult;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public
class FindForPayPresenter extends BasePresenter<IFindForPayView> {

    public FindForPayPresenter(IFindForPayView iPersonView) {
        attachView(iPersonView);
    }

    public void getForPayData(){

        RetrofitCreate.getFiannceApiService()
                .getFindForPayData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (IView!=null){
                            IView.showLoading();
                            add(disposable);
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {
                        if (IView!=null){
                            IView.getFindForPayData(findForPayBean);
                            LogUtils.json(findForPayBean);
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

    public void confirmServerPayResult(String outTradeNo, PayResult result, Boolean clientPayResult) {
        JSONObject object = new JSONObject();
        try {
            object.put("outTradeNo",outTradeNo);
            object.put("result",result.getResult());
            object.put("clientPayResult",clientPayResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        RetrofitCreate.getFiannceApiService().setConfirmServerPayResult(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConfirmServerPayResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConfirmServerPayResultBean bean) {
                        IView.onConfirmServerPayResultOk(bean);
                        com.example.common.LogUtils.i(bean.getCode());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
