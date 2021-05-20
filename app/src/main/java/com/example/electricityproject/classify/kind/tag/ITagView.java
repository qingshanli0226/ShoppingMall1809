package com.example.electricityproject.classify.kind.tag;

import com.example.common.bean.ClassifyBean;
import com.example.common.bean.TagBean;
import com.example.framework.IBaseView;

public
interface ITagView extends IBaseView {
    void onTagData(ClassifyBean classifyBean);
}
