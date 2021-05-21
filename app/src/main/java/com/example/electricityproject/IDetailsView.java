package com.example.electricityproject;

import com.example.common.bean.AddOneProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.framework.IBaseView;

public
interface IDetailsView extends IBaseView {

    void getAddOneProduct(AddOneProductBean addOneProductBean);

}
