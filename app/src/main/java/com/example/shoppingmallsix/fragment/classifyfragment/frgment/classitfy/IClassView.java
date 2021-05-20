package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;

import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;

public interface IClassView {
    void onSkirtData(SkirtBean skirtBean);
    void onJacketData(JacketBean jacketBean);
    void onPantsBean(PantsBean pantsBean);
    void onOvercoatBean(OvercoatBean overcoatBean);
    void onAccrssoryBean(AccrssoryBean accrssoryBean);
    void onBagBean(BagBean bagBean);
    void onDressBean(DressBean dressBean);
    void onProductsBean(ProductsBean productsBean);
    void onStationeryBean(StationeryBean stationeryBean);
    void onDigitBean(DigitBean digitBean);
    void onGameBean(GameBean gameBean);

}
