package com.example.shoppingmall1809.main.type.sort;

import com.example.framework.IBaseView;
import com.example.net.model.CategoryBean;
import com.example.net.model.SortBean;

public interface ISortView extends IBaseView {
    void getSortData(SortBean sortBean);
}
