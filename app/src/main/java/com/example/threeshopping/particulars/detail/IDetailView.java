package com.example.threeshopping.particulars.detail;

import com.example.framework.IBaseView;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.ProductBean;

public interface IDetailView extends IBaseView {
    void onAddCart(ProductBean productBean);
    void onInventory(InventoryBean inventoryBean);
}
