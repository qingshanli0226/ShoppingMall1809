package com.example.electricityproject.classify.kind.type;

import com.example.common.bean.GoodsBean;
import com.example.framework.IBaseView;

public interface IKindView extends IBaseView {
    void onTypeData(GoodsBean goodsBean);
}
