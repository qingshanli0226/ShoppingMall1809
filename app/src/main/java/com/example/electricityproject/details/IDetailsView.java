package com.example.electricityproject.details;

import com.example.common.bean.AddOneProductBean;
import com.example.common.bean.RegBean;
import com.example.framework.IBaseView;

public
interface IDetailsView extends IBaseView {
    /**
     * 添加一个商品的接口和检查库存的接口
     * 用来求情网络数据
     * @param addOneProductBean
     */
    void getAddOneProduct(AddOneProductBean addOneProductBean);
    void checkOneProductInventory(RegBean checkInventoryBean);

}
