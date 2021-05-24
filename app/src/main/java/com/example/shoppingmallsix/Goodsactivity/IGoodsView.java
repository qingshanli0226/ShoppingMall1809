package com.example.shoppingmallsix.Goodsactivity;

import com.example.framework.IBaseView;
import com.example.net.bean.business.AddOneProductBean;

public interface IGoodsView extends IBaseView {
    void onAddCart(AddOneProductBean productBean);
}
