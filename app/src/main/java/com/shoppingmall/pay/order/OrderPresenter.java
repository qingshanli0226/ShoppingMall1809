package com.shoppingmall.pay.order;

import com.shoppingmall.framework.mvp.BasePresenter;
import com.shoppingmall.net.bean.ProductBean;

import org.json.JSONObject;

import okhttp3.MediaType;

public class OrderPresenter extends BasePresenter<IOrderView> {
    public OrderPresenter(IOrderView iOrderView){
        attachView(iOrderView);
    }

    public void setOrderInfo(String subject,String totalPrice,String name){

    }
}
