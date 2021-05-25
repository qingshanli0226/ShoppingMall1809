package com.example.myapplication.type;

import com.example.net.bean.SkirtBean;

import mvp.view.IBaseVIew;

public interface ISkirtView extends IBaseVIew {
    void onSkirt(SkirtBean skirtBean);
}
