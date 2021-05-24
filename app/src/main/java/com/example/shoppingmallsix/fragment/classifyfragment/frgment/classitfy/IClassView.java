package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;

import com.example.framework.IBaseView;
import com.example.net.bean.classify.ClassBean;

public interface IClassView extends IBaseView {
    void onClassData(ClassBean classBean,boolean mBoolean,String url);


}
