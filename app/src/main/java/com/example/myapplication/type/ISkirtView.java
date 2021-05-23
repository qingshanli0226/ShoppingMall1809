package com.example.myapplication.type;

import com.example.net.bean.AccessoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.HomeProductBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.Overconat;
import com.example.net.bean.PantsBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;

import mvp.view.IBaseVIew;

public interface ISkirtView extends IBaseVIew {
    void onSkirt(SkirtBean skirtBean);

}
