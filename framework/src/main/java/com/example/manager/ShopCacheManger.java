package com.example.manager;

import android.content.Context;
import android.widget.Toast;

import com.example.common.LogUtils;
import com.example.common.bean.FindForPayBean;
import com.example.common.bean.FindForSendBean;
import com.example.common.bean.LogBean;
import com.example.common.bean.SelectOrderBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.net.RetrofitCreate;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopCacheManger implements BusinessUserManager.IUserLoginChanged{

    private static ShopCacheManger cacheManger;
    //商品数量
    private String productNum;
    //购物车所有数据缓存
    private List<ShortcartProductBean.ResultBean> shortBeanList = new ArrayList<>();
    //选中商品缓存
    private List<ShortcartProductBean.ResultBean> selectList = new ArrayList<>();
    //提交订单缓存
    private List<SelectOrderBean> list = new ArrayList<>();
    //支付成功缓存
    private List<FindForPayBean.ResultBean> paySussList = new ArrayList<>();
    //支付失败缓存
    private List<FindForPayBean.ResultBean> payFailList = new ArrayList<>();
    //消息缓存
    private List<FindForPayBean.ResultBean> messageList = new ArrayList<>();
    //购物车数据发生改变时
    private List<iShopBeanChangeListener> shopBeanChangeListeners = new ArrayList<>();
    //待收货数据发生改变时
    private List<iFindShopChangeListener> findShopChangeListeners = new ArrayList<>();
    //待支付数据发生改变时
    private List<iFindPayChangeListener> findPayChangeListeners = new ArrayList<>();
    //待发货商品数据缓存
    private List<FindForSendBean.ResultBean> findShopList = new ArrayList<>();
    //带支付商品数据缓存
    private List<FindForPayBean.ResultBean> findPayList = new ArrayList<>();

    private Context mContext;

    public static ShopCacheManger getInstance() {
        synchronized (ShopCacheManger.class){
            if (cacheManger==null){
                cacheManger = new ShopCacheManger();
            }
        }
        return cacheManger;
    }

    public void init(Context context){
        this.mContext=context;
        BusinessUserManager.getInstance().Register(this);
    }

    public List<FindForSendBean.ResultBean> getFindShopList() {
        return findShopList;
    }

    public void setFindShopList(List<FindForSendBean.ResultBean> findShopList) {
        this.findShopList = findShopList;
        for (iFindShopChangeListener findShopChangeListener : findShopChangeListeners) {
            findShopChangeListener.OnFindShopChange();
        }
    }
    //添加代收获的数据 遍历接口刷新
    public void addFindShop(FindForSendBean.ResultBean resultBean){
        findShopList.add(resultBean);
        for (iFindShopChangeListener findShopChangeListener : findShopChangeListeners) {
            findShopChangeListener.OnFindShopChange();
        }
    }

    public List<FindForPayBean.ResultBean> getFindPayList() {
        return findPayList;
    }

    public void setFindPayList(List<FindForPayBean.ResultBean> findPayList) {
        this.findPayList = findPayList;
        for (iFindShopChangeListener findShopChangeListener : findShopChangeListeners) {
            findShopChangeListener.OnFindShopChange();
        }
    }
    //添加待支付的数据 遍历接口刷新
    public void addFindPay(FindForPayBean.ResultBean resultBean){
        findPayList.add(resultBean);
        for (iFindShopChangeListener findShopChangeListener : findShopChangeListeners) {
            findShopChangeListener.OnFindShopChange();
        }
    }

    //删除待支付的数据 遍历接口刷新
    public void removeFindPay(FindForPayBean.ResultBean resultBean){
        findPayList.remove(resultBean);
        for (iFindShopChangeListener findShopChangeListener : findShopChangeListeners) {
            findShopChangeListener.OnFindShopChange();
        }
    }

    //购物车接口注册
    public void registerShopBeanChange(iShopBeanChangeListener iShopBeanChangeListener){
        shopBeanChangeListeners.add(iShopBeanChangeListener);
    }
    //购物车接口取消注册
    public void unregisterShopBeanChange(iShopBeanChangeListener iShopBeanChangeListener){
        shopBeanChangeListeners.remove(iShopBeanChangeListener);
    }
    //代收货接口注册
    public void registerFindShopChange(iFindShopChangeListener iFindShopChangeListener){
        findShopChangeListeners.add(iFindShopChangeListener);
    }
    //代收获接口取消注册
    public void unregisterFindShopChange(iFindShopChangeListener iFindShopChangeListener){
        findShopChangeListeners.add(iFindShopChangeListener);
    }

    //待支付接口注册
    public void registerFindPayChange(iFindPayChangeListener iFindPayChangeListener){
        findPayChangeListeners.add(iFindPayChangeListener);
    }
    //待支付接口取消注册
    public void unregisterFindPayChange(iFindPayChangeListener iFindPayChangeListener){
        findPayChangeListeners.remove(iFindPayChangeListener);
    }

    //判断当期是否登录
    @Override
    public void onLoginChange(LogBean isLog) {
        //登录后请求购物车数据
        if (isLog!=null){
            requestShortProductData();
            getDropShipment();
            getForPayData();
        }
    }

    public List<ShortcartProductBean.ResultBean> getShortBeanList() {
        return shortBeanList;
    }

    public void setShortBeanList(List<ShortcartProductBean.ResultBean> shortBeanList) {
        this.shortBeanList = shortBeanList;
        ShopBeanNotify();
    }

    public List<SelectOrderBean> getList() {
        return list;
    }

    public void setList(List<SelectOrderBean> list) {
        this.list = list;
    }

    //当前选中的购物车商品集合
    public void setSelect(ShortcartProductBean.ResultBean select) {
        if (select != null) {
            if (!selectList.contains(select)) {
                if (select.isAll()){
                    selectList.add(select);
                }
            }else {
                if (!select.isAll()){
                    selectList.remove(select);
                }
            }
        }
    }

    //返回选中的集合
    public List<ShortcartProductBean.ResultBean> getSelectList() {
        return selectList;
    }

    public String getMoneyValue() {
        float sumPrice=0;
        for (ShortcartProductBean.ResultBean resultBean : shortBeanList) {
            if(resultBean.isAll()){
                float productPrice=Float.parseFloat(resultBean.getProductPrice()+"");
                int productNum=Integer.parseInt(resultBean.getProductNum());
                sumPrice=sumPrice+productPrice*productNum;
            }
        }
        return String.valueOf(sumPrice);
    }

    //详情页面添加数据
    public void addShopMessageNum(String productId,String productName,String productNum,String url,String productPrice,boolean isAll){
        ShortcartProductBean.ResultBean bean = new ShortcartProductBean.ResultBean(productId, productName, productNum, url, productPrice, false);

            for (ShortcartProductBean.ResultBean resultBean : shortBeanList) {
                if (resultBean.getProductName().equals(bean.getProductName())){
                    int i = Integer.parseInt(resultBean.getProductNum());
                    int j = Integer.parseInt(bean.getProductNum());
                    i+=j;
                    resultBean.setProductNum(i+"");
                    ShopBeanNotify();
                    return;
                }
            }
            shortBeanList.add(bean);

        ShopBeanNotify();
    }

    //购物车中删除
    public void ShopDelOne(ShortcartProductBean.ResultBean bean){
        shortBeanList.remove(bean);
        //删除完后刷新
        ShopBeanNotify();
    }
    //商品数量+1
    public void addShopNum(ShortcartProductBean.ResultBean bean){
        for (ShortcartProductBean.ResultBean resultBean : shortBeanList) {
            if (resultBean.getProductName().equals(bean.getProductName())){
                int num=Integer.parseInt(bean.getProductNum());
                resultBean.setProductNum(num+1+"");
            }
        }
        ShopBeanNotify();

    }
    //商品数量-1
    public void subShopNum(ShortcartProductBean.ResultBean bean){
        for (ShortcartProductBean.ResultBean resultBean : shortBeanList) {
            if (resultBean.getProductName().equals(bean.getProductName())){
                int num=Integer.parseInt(bean.getProductNum());
                resultBean.setProductNum(num-1+"");
            }
        }
       ShopBeanNotify();
    }

    public void setMessageList(List<FindForPayBean.ResultBean> messageList) {
        this.messageList = messageList;
    }

    public List<FindForPayBean.ResultBean> getPayFailList() {
        return payFailList;
    }

    public List<FindForPayBean.ResultBean> getPaySussList() {
        return paySussList;
    }

    //购物车页面数据
    public synchronized void requestShortProductData(){
        RetrofitCreate.getFiannceApiService()
                .getShortProductData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShortcartProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ShortcartProductBean shortcartProductBean) {
                        if (shortcartProductBean.getCode().equals("200")){
                            List<ShortcartProductBean.ResultBean> result = shortcartProductBean.getResult();
                            ShopCacheManger.getInstance().setShortBeanList(result);
                            LogUtils.i(shortcartProductBean.getResult().toString());
                        }else {
                            Toast.makeText(mContext, "没有拿到购物车数据", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //查找待发货的订单
    public void getDropShipment(){
        RetrofitCreate.getFiannceApiService()
                .getFindSendData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindForSendBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForSendBean findForSendBean) {
                        if (findForSendBean.getCode().equals("200")){
                            setFindShopList(findForSendBean.getResult());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //查找待支付的订单
    public void getForPayData(){
        RetrofitCreate.getFiannceApiService()
                .getFindForPayData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FindForPayBean findForPayBean) {
                        if (findForPayBean.getCode().equals("200")) {
                            setFindPayList(findForPayBean.getResult());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //判断登录成功后的收货地址
    public void isBind(LogBean logBean){
        if (logBean!=null){
            if (logBean.getResult().getAddress()!=null && logBean.getResult().getPhone()!=null){
                BusinessUserManager.getInstance().setBindTel(true);
                BusinessUserManager.getInstance().setBindAddress(true);
            }
        }
    }

    //购物车数据发生变化时,调用接口
   public  interface iShopBeanChangeListener{
        void OnShopBeanChange();
    }


    public void ShopBeanNotify(){
        for (iShopBeanChangeListener shopBeanChangeListener : shopBeanChangeListeners) {
            shopBeanChangeListener.OnShopBeanChange();
        }
    }

    //代收货数据发生改变时,调用接口
    public interface iFindShopChangeListener{
        void OnFindShopChange();
    }
    //待支付数据发生改变时,调用接口
    public interface iFindPayChangeListener{
        void OnFindPayChange();
    }

}
