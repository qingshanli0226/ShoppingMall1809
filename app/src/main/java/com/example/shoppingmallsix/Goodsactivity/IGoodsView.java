package com.example.shoppingmallsix.Goodsactivity;

import com.example.framework.IBaseView;
import com.example.net.bean.business.AddOneProductBean;
import com.example.net.bean.business.CheckInventoryBean;
import com.example.net.bean.business.UpdateProductNumBean;

public interface IGoodsView extends IBaseView {
    void onAddCart(AddOneProductBean productBean);

    void onCheckInventory(CheckInventoryBean bean);
}
