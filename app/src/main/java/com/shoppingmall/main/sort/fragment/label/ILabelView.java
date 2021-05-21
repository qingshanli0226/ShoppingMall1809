package com.shoppingmall.main.sort.fragment.label;

import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.net.bean.LabelBean;

public interface ILabelView extends IBaseView {
    void getLabelData(LabelBean labelBean);
}
