package com.example.user.phoneaddress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.net.bean.UpdateAddress;
import com.example.net.bean.UpdatePhone;
import com.example.user.R;

public class PhoneAddressMainActivity extends BaseActivity<PhoneAddressPresenter> implements IPhoneAndAddressView {


    private com.example.framework.view.MyToorbar toorbar;
    private android.widget.EditText etBindTelandadressPhone;
    private android.widget.Button btnphone;
    private android.widget.EditText etBindTelandadressAdress;
    private android.widget.Button btnaddress;

    @Override
    protected int bandLayout() {
        return R.layout.activity_phone_address_main;
    }

    @Override
    public void onBindphone(UpdatePhone updatePhone) {
        if (updatePhone.getCode().equals("200")){
            Toast.makeText(this, "添加手机号成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindAddress(UpdateAddress updateAddress) {
        if (updateAddress.getCode().equals("200")){
            Toast.makeText(this, "添加收货地址成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initView() {

        toorbar = findViewById(R.id.toorbar);
        etBindTelandadressPhone = findViewById(R.id.et_bind_telandadress_phone);
        btnphone = findViewById(R.id.btnphone);
        etBindTelandadressAdress = findViewById(R.id.et_bind_telandadress_adress);
        btnaddress = findViewById(R.id.btnaddress);
    }

    @Override
    public void initPresenter() {
        mPresenter=new PhoneAddressPresenter(this);


    }

    @Override
    public void initData() {
        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etBindTelandadressPhone.getText().toString().trim();
                mPresenter.onPhone(phone);
            }
        });
        btnaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etBindTelandadressAdress.getText().toString().trim();
                mPresenter.onAddress(address);
            }
        });

    }
}