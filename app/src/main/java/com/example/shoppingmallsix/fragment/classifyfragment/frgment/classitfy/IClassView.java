package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;

import com.example.framework.IBaseView;
import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.ClassBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;

public interface IClassView extends IBaseView {
    void onClassData(ClassBean classBean,boolean mBoolean);


}
