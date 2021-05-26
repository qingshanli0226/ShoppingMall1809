package com.example.electricityproject.shopp.userinfo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.bean.UpdateAddress;
import com.example.common.bean.UpdatePhoneBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;
import com.example.view.ToolBar;

public class BindUserInfoActivity extends BaseActivity<BindUserInfoPresenter> implements IBindUserInfoView {

    private com.example.view.ToolBar toolbar;
    private android.widget.EditText editPhone;
    private android.widget.Button confirmPhone;
    private android.widget.EditText editAddress;
    private android.widget.Button confirmAddress;

    @Override
    public void updatePhone(UpdatePhoneBean updatePhoneBean) {
        if (updatePhoneBean.getCode().equals("200")){
            Toast.makeText(this, "电话绑定成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateAddress(UpdateAddress updateAddress) {
        if (updateAddress.getCode().equals("200")){
            Toast.makeText(this, "地址绑定成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {
        confirmPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editPhone.getText().toString().trim();
                httpPresenter.postUpdatePhoneData(phone);
            }
        });

        confirmAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = editAddress.getText().toString().trim();
                httpPresenter.postUpdateAddressData(address);
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
}