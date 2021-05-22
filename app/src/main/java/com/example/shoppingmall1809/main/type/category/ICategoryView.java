package com.example.shoppingmall1809.main.type.category;

import com.example.framework.IBaseView;
import com.example.net.model.CategoryBean;

public interface ICategoryView extends IBaseView {
    void onCategoryData(CategoryBean categoryBean);
}
