package com.example.threeshopping.cart;

import com.example.framework.IBaseView;
import com.example.net.bean.CartBean;
import com.example.net.bean.OrderBean;

import java.util.List;

public interface ICartView extends IBaseView {
    void onCheck(int position,boolean isCheck);
    void onCheckAll(boolean isChcekAll);
    void onNum(int position);
    //删除一个
    void removeProduct(int position);

    //删错多个
    void removeMany(List<CartBean.ResultBean> resultBeans);

    //支付订单
    void onOrder(OrderBean orderBean);
}
