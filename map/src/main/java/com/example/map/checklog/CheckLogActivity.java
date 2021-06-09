package com.example.map.checklog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.map.R;
import com.example.map.overlayutil.DrivingRouteOverlay;

import java.util.List;

public class CheckLogActivity extends BaseActivity {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private com.example.framework.view.ToolBar toolbar;
    private MapView bmapView;
    private android.widget.TextView locationText;
    private android.widget.Button locationBtn;
    private android.widget.Button confirm;
    private double longitude = 0l;
    private double latitude = 0;
    private PoiSearch mPoiSearch;
    private int position;
    private String mAddr;
    private String mPhone;
    private Boolean mFlag;
    @Override
    public int getLayoutId() {
        return R.layout.activity_check_log;
    }

    @Override
    public void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        bmapView = (MapView) findViewById(R.id.bmapView);
        locationText = (TextView) findViewById(R.id.locationText);
        locationBtn = (Button) findViewById(R.id.locationBtn);
        confirm = (Button) findViewById(R.id.confirm);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {


        //接受
        Bundle bundle = CommonArouter.getInstance().getBundle();
        position = bundle.getInt(Constants.DEFAULT_ADDR);
        mPhone = bundle.getString(Constants.PHONE_ADDR);
        mFlag = bundle.getBoolean(Constants.FLAG_ADDR);

        mBaiduMap.setMyLocationEnabled(true);

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name);
        MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, bitmapDescriptor);
        mBaiduMap.setMyLocationConfiguration(myLocationConfiguration);

        LocationClient mLocationClient = new LocationClient(CheckLogActivity.this);

//通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

//设置locationClientOption
        mLocationClient.setLocOption(option);

//注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
        mLocationClient.start();


        //检索监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //检索
                mPoiSearch.searchNearby(new PoiNearbySearchOption()
                        .location(new LatLng(latitude, longitude))
                        .pageCapacity(10)
                        .radius(1000)
                        //支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
                        .keyword("餐厅$大学$银行$超市")
                        .pageNum(0));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.ADD_ADDR,mAddr);
                bundle.putString(Constants.PHONE_ADDR,mPhone);
                bundle.putInt(Constants.DEFAULT_ADDR,position);
                bundle.putBoolean(Constants.FLAG_ADDR,mFlag);
                CommonArouter.getInstance().build(Constants.PATH_BIND).with(bundle).navigation();
            }
        });

    }

    //检索监听
    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            LogUtil.d("zybt");
            if(poiResult != null){
                LogUtil.d("zybt1");

                List<PoiInfo> allPoi = poiResult.getAllPoi();
                if(allPoi.size() > 0){
                    LogUtil.d("zybt2");
                    PoiInfo poiInfo = allPoi.get(0);
                    mAddr = poiInfo.address;
                    locationText.setText(poiInfo.address);
                }

            }

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            longitude = locData.longitude;
            latitude = locData.latitude;
        }
    }

    @Override
    public void destroy() {
        super.destroy();

    }
}