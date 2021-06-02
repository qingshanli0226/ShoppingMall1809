package com.example.electricityproject.shopp;

import com.example.common.bean.CheckInventoryBean;
import com.example.common.bean.OrderInfoBean;
import com.example.common.bean.RegBean;
import com.example.common.bean.RemoveManyProductBean;
import com.example.common.bean.RemoveOneProductBean;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.bean.UpdateProductNumBean;
import com.example.framework.IBaseView;

public
interface IShoppingView extends IBaseView {
    //全选
    void postSelectAllProductData(SelectAllProductBean selectAllProductBean);
    //单选
    void postSelectOneProductData(SelectAllProductBean selectAllProductBean);
    //获取服务端购物车产品信息
    void getShortProductData(ShortcartProductBean shortcartProductBean);
    //更改购物车数据
    void amendProductData(UpdateProductNumBean updateProductNumBean);
    //检查一个库存是否充足
    void CheckProductData(RegBean regBean);
    //删除一个
    void removeOneShop(RemoveOneProductBean removeOneProductBean);
    //删除多个
    void removeManyShop(RemoveManyProductBean removeManyProductBean);
    //下订单
    void getOrderInfoData(OrderInfoBean orderInfoBean);
    //检查服务端多个产品是否库存充足
    void checkInventory(CheckInventoryBean checkInventoryBean);
}
