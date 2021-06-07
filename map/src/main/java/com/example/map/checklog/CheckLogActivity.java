package com.example.map.checklog;

import androidx.appcompat.app.AppCompatActivity;


import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.framework.BaseActivity;
import com.example.map.R;

public class CheckLogActivity extends BaseActivity {
    private MapView mMapView = null;
    private BaiduMap map;

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_log;
    }

    @Override
    public void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
         map = mMapView.getMap();

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}