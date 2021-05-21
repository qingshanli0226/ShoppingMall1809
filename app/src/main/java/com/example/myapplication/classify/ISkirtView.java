package com.example.myapplication.classify;

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

import mvp.view.BaseVIew;

public interface ISkirtView extends BaseVIew {
    void onSkirt(SkirtBean skirtBean);
    void onJack(JacketBean jacketBean);
    void onPants(PantsBean pantsBean);
    void onOver(Overconat overconat);
    void onAccess(AccessoryBean accessoryBean);
    void onBagurl(BagBean bagBean);
    void onDress(DressBean dressBean);
    void onHomee(HomeProductBean homeProductBean);
    void onStart(StationeryBean stationeryBean);
    void onDigit(DigitBean digitBean);
    void onGame(GameBean gameBean);
}
