package com.example.myapplication.personalcenter.map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.myapplication.R;
import com.example.overlay.DrivingRouteOverlay;

import java.util.List;

public class MapActivity extends AppCompatActivity {
    MapView mMapView = null;
    private LatLonPoint startPoint;
    private LatLonPoint endPoint;
    private AMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

         map = mMapView.getMap();
        this.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startPoint = new LatLonPoint(39.916527,116.397128);
                endPoint = new LatLonPoint(39.71755,117.30983);
                RouteSearch routeSearch = new RouteSearch(MapActivity.this);

                routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
                    @Override
                    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

                    }

                    @Override
                    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {//驾车
                        Toast.makeText(MapActivity.this, "i:" + i, Toast.LENGTH_SHORT).show();
                        if (driveRouteResult != null && i == 1000) {
                            List<DrivePath> paths = driveRouteResult.getPaths();
                            DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(MapActivity.this, map, paths.get(0), startPoint, endPoint, null);
                            drivingRouteOverlay.addToMap();
                            drivingRouteOverlay.zoomToSpan();
                        }
                    }

                    @Override
                    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

                    }

                    @Override
                    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

                    }
                });
                RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint.copy(),endPoint.copy());
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, 0, null, null, "");
                routeSearch.calculateDriveRouteAsyn(query);
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}