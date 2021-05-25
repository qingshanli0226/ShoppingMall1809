package com.example.shoppingmallsix.goods;

import com.example.framework.IBaseView;
import com.example.net.bean.business.AddOneProductBean;
import com.example.net.bean.business.CheckInventoryBean;
import com.example.net.bean.business.CheckOneInventoryBean;
import com.example.net.bean.business.UpdateProductNumBean;

public interface IGoodsView extends IBaseView {
    void onAddCart(AddOneProductBean productBean);

    void onCheckInventory(CheckOneInventoryBean bean);

    void onUpdateProductNum(UpdateProductNumBean updateProductNumBean);
}
