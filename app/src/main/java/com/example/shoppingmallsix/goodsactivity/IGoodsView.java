package com.example.shoppingmallsix.goodsactivity;

import com.example.framework.IBaseView;
import com.example.net.bean.business.AddOneProductBean;
import com.example.net.bean.business.UpdateProductNumBean;

public interface IGoodsView extends IBaseView {
    void onAddCart(AddOneProductBean productBean);

    void onUpdateNum(UpdateProductNumBean bean);
    void onUpDataError(String msg);
}
