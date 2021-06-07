package com.example.electricityproject.shopp.userinfo.infodb;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.example.common.LogUtils;

public
class MyLocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        String addr = bdLocation.getAddrStr();    //获取详细地址信息
        String country = bdLocation.getCountry();    //获取国家
        String province = bdLocation.getProvince();    //获取省份
        String city = bdLocation.getCity();    //获取城市
        String district = bdLocation.getDistrict();    //获取区县
        String street = bdLocation.getStreet();    //获取街道信息
        String adcode = bdLocation.getAdCode();    //获取adcode
        String town = bdLocation.getTown();    //获取乡镇信息
        LogUtils.i(addr);
        LogUtils.i(country);
        LogUtils.i(province);
        LogUtils.i(city);
        LogUtils.i(district);
        LogUtils.i(street);
        LogUtils.i(adcode);
        LogUtils.i(town);
    }
}
