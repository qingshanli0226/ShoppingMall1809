package com.example.threeshopping.cart;

import com.example.framework.IBaseView;
import com.example.net.bean.CartBean;

import java.util.List;

public interface ICarView extends IBaseView {
    void onShowCart(List<CartBean.ResultBean> carts);
    void onCheck(int position,boolean isCheck);
    void onCheckAll(boolean isChcekAll);
    void onNum(int position);
}
