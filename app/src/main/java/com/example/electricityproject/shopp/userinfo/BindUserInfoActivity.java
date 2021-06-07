package com.example.electricityproject.shopp.userinfo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapView;
import com.example.common.LogUtils;
import com.example.common.bean.LogBean;
import com.example.common.bean.UpdateAddress;
import com.example.common.bean.UpdatePhoneBean;
import com.example.electricityproject.R;
import com.example.electricityproject.shopp.userinfo.infodb.DaoMaster;
import com.example.electricityproject.shopp.userinfo.infodb.MyLocationListener;
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
    private MapView mMapView;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    @Override
    public void updatePhone(UpdatePhoneBean updatePhoneBean) {
        if (updatePhoneBean.getCode().equals("200")){
            Toast.makeText(this, "电话绑定成功", Toast.LENGTH_SHORT).show();
            isBindPhone = true;
        }
    }

    @Override
    public void updateAddress(UpdateAddress updateAddress) {
        if (updateAddress.getCode().equals("200")){
            Toast.makeText(this, "地址绑定成功", Toast.LENGTH_SHORT).show();
            isBindAddress = true;

            if (isBindAddress && isBindPhone){
                long insert = daoMaster.newSession().insert(new UserInfoTable(null, name, address, phone,false));
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

        daoMaster = UserInfoTableManger.getInstance().getDaoMaster(this);

        LogBean isLog = BusinessUserManager.getInstance().getIsLog();
        if (isLog!=null){
            name = isLog.getResult().getName();
        }

        confirmPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 phone = editPhone.getText().toString().trim();
                if (phone.length() == 11){
                    httpPresenter.postUpdatePhoneData(phone);
                }else {
                    Toast.makeText(BindUserInfoActivity.this, "电话格式不对", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //获取乡镇街道
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setNeedNewVersionRgc(true);
        mLocationClient.setLocOption(option);

        mLocationClient.start();

        confirmAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = editAddress.getText().toString().trim();
                if (!address.equals("")){
                    httpPresenter.postUpdateAddressData(address);
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
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
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
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}