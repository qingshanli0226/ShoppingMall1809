package com.example.threeshopping.cart;

import com.example.framework.IBaseView;
import com.example.net.bean.CartBean;
import com.example.net.bean.CheckNumAll;
import com.example.net.bean.OrderBean;

import java.util.List;

public interface ICartView extends IBaseView {

    void onCheck(int position,boolean isCheck);
    void onCheckAll(boolean isChcekAll);
    void onNum(int position);



    //查询所有
    void onCheckNumAll(CheckNumAll checkNumAll);

}
