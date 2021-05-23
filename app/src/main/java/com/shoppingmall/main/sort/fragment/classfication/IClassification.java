package com.shoppingmall.main.sort.fragment.classfication;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.GoodsBean;

public interface IClassification extends IBaseView {
    void getGoods(GoodsBean goodsBean);
}
