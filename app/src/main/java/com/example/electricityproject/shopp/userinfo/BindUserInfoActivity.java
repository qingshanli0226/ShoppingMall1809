package com.example.electricityproject.shopp.userinfo;

import android.Manifest;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.example.common.LogUtils;
import com.example.common.bean.LogBean;
import com.example.common.bean.UpdateAddress;
import com.example.common.bean.UpdatePhoneBean;
import com.example.electricityproject.R;
import com.example.electricityproject.shopp.userinfo.infodb.DaoMaster;
import com.example.electricityproject.shopp.userinfo.infodb.UserInfoTable;
import com.example.electricityproject.shopp.userinfo.infodb.UserInfoTableManger;
import com.example.framework.BaseActivity;
import com.example.manager.BusinessUserManager;
import com.example.view.ToolBar;

import org.greenrobot.eventbus.EventBus;

public class BindUserInfoActivity extends BaseActivity<BindUserInfoPresenter> implements IBindUserInfoView {

    private com.example.view.ToolBar toolbar;
    private android.widget.EditText editPhone;
    private android.widget.Button confirmPhone;
    private android.widget.EditText editAddress;
    private android.widget.Button confirmAddress;
    private DaoMaster daoMaster;
    private String name;
    private String phone;
    private String address;
    private boolean isBindPhone;
    private boolean isBindAddress;
    private MapView bmapView;
    public LocationClient mLocationClient = null;
    private MyBDA myListener = new MyBDA();
    private String Newaddress;

    @Override
    public void updatePhone(UpdatePhoneBean updatePhoneBean) {
        if (updatePhoneBean.getCode().equals("200")){
            Toast.makeText(this, getResources().getString(R.string.bind_UserInfo_phone), Toast.LENGTH_SHORT).show();
            isBindPhone = true;
        }
    }

    @Override
    public void updateAddress(UpdateAddress updateAddress) {
        if (updateAddress.getCode().equals("200")){
            Toast.makeText(this, getResources().getString(R.string.bind_UserInfo_address), Toast.LENGTH_SHORT).show();
            isBindAddress = true;
            if (isBindAddress && isBindPhone){
                long insert = daoMaster.newSession().insert(new UserInfoTable(null, name, Newaddress, phone,false));
                LogUtils.i(insert+"");
                if (insert!=0){
                    EventBus.getDefault().post("bindinfo");
                    finish();
                }
            }
        }
    }

    @Override
    protected void initData() {
        //数据库初始化
        daoMaster = UserInfoTableManger.getInstance().getDaoMaster(this);
        //加入获取当前地址的动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
            }, 100);
        }

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        //如果开发者需要获得当前点的位置信息，此处必须为true
        option.setIsNeedLocationDescribe(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        option.setAddrType("all"); // 定位的街道类型，只有设定为all才可以全部显示
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true
        mLocationClient.setLocOption(option);
        mLocationClient.start();

        LogBean isLog = BusinessUserManager.getInstance().getIsLog();
        if (isLog!=null){
            name = isLog.getResult().getName();
        }
        //点击绑定电话
        confirmPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 phone = editPhone.getText().toString().trim();
                if (phone.length() == 11){
                    httpPresenter.postUpdatePhoneData(phone);
                }else {
                    Toast.makeText(BindUserInfoActivity.this, getResources().getString(R.string.bind_UserInfo_phone_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //点击绑定地址
        confirmAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Newaddress.equals("") && Newaddress!=null){
                    editAddress.setHint(""+Newaddress);
                    httpPresenter.postUpdateAddressData(Newaddress);
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new BindUserInfoPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        confirmPhone = (Button) findViewById(R.id.confirm_phone);
        editAddress = (EditText) findViewById(R.id.edit_address);
        confirmAddress = (Button) findViewById(R.id.confirm_address);
        bmapView = (MapView) findViewById(R.id.bmapView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_user_info;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bmapView.onDestroy();
    }

    public class MyBDA extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Newaddress = bdLocation.getAddrStr();    //获取详细地址信息
            String country = bdLocation.getCountry();    //获取国家
            String province = bdLocation.getProvince();    //获取省份
            String city = bdLocation.getCity();    //获取城市
            String district = bdLocation.getDistrict();    //获取区县
            String street = bdLocation.getStreet();    //获取街道信息
            int locType = bdLocation.getLocType();    //获取街道信息

            //mapView 销毁后不在处理新接收的位置
            if (bdLocation == null || bmapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(bdLocation.getDirection())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
        }
    }


}