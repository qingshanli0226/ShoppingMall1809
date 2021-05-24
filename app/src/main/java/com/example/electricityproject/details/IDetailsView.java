package com.example.electricityproject.details;

import com.example.common.bean.AddOneProductBean;
import com.example.common.bean.RegBean;
import com.example.framework.IBaseView;

public
interface IDetailsView extends IBaseView {

    void getAddOneProduct(AddOneProductBean addOneProductBean);
    void checkOneProductInventory(RegBean checkInventoryBean);

}
