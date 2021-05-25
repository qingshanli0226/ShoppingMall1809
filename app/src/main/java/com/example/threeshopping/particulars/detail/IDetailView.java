package com.example.threeshopping.particulars.detail;

import com.example.framework.IBaseView;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.SelectBean;
import com.example.net.bean.UpdateProductNumBean;

public interface IDetailView extends IBaseView {
    void onAddCart(SelectBean selectBean);
    void onInventory(ProductBean inventoryBean);
//    void onUpdateProductNum(UpdateProductNumBean updateProductNumBean);
}
