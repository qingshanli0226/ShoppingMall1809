package com.example.user.phoneaddress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.framework.manager.CaCheLoginUserMannager;
import com.example.net.bean.LoginBean;
import com.example.net.bean.UpdateAddress;
import com.example.net.bean.UpdatePhone;
import com.example.user.R;

public class PhoneAddressMainActivity extends BaseActivity<PhoneAddressPresenter> implements IPhoneAndAddressView,CaCheLoginUserMannager.UserPhoneAndAddress {

    private com.example.framework.view.MyToorbar toorbar;
    private android.widget.EditText etBindTelandadressPhone;
    private android.widget.Button btnphone;
    private android.widget.EditText etBindTelandadressAdress;
    private android.widget.Button btnaddress;
    private android.widget.Button confirm;
    private String address;
    private String phone;
    private LoginBean bean;
    private boolean isPhone = false;
    private boolean isAddress = false;

    @Override
    protected int bandLayout() {
        return R.layout.activity_phone_address_main;
    }

    @Override
    public void onBindphone(UpdatePhone updatePhone) {
        if (updatePhone.getCode().equals("200")) {
            Toast.makeText(this, getString(R.string.addPhoneSuccess), Toast.LENGTH_SHORT).show();
            isPhone = true;
            bean.getResult().setPhone(phone);
            CaCheLoginUserMannager.getInstance().setBean(bean);//更新缓存类的用户信息
            CaCheLoginUserMannager.getInstance().setUserPhoneOrAddress();//通知
        } else {
            isPhone = false;
            Toast.makeText(this, getString(R.string.addPhoneError), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindAddress(UpdateAddress updateAddress) {
        if (updateAddress.getCode().equals("200")) {
            Toast.makeText(this, getString(R.string.addAddressSuccess), Toast.LENGTH_SHORT).show();
            isAddress = true;
            bean.getResult().setAddress(address);
            CaCheLoginUserMannager.getInstance().setBean(bean); //更新缓存类的用户信息
            CaCheLoginUserMannager.getInstance().setUserPhoneOrAddress();//通知
        } else {
            isAddress = false;
            Toast.makeText(this, getString(R.string.addAddressError), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initView() {
        toorbar = findViewById(R.id.toorbar);
        etBindTelandadressPhone = findViewById(R.id.et_bind_telandadress_phone);
        btnphone = findViewById(R.id.btnphone);
        etBindTelandadressAdress = findViewById(R.id.et_bind_telandadress_adress);
        btnaddress = findViewById(R.id.btnaddress);
        confirm = (Button) findViewById(R.id.confirm);
    }

    @Override
    public void initPresenter() {
        mPresenter = new PhoneAddressPresenter(this);
    }

    @Override
    public void initData() {
        bean = CaCheLoginUserMannager.getInstance().getBean();

        btnphone.setOnClickListener(v -> {  //绑定电话按钮
            phone = etBindTelandadressPhone.getText().toString().trim();
            mPresenter.onPhone(phone);
        });

        btnaddress.setOnClickListener(v -> { //绑定地址按钮
            address = etBindTelandadressAdress.getText().toString().trim();
            mPresenter.onAddress(address);
        });

        confirm.setOnClickListener(v -> { //点击完成  判断是否已经绑定位置  如已完成则退出
            //如果地址没绑定   判断
            if (address == null || address.equals("")) {
                if (bean.getResult().getAddress() != null) {
                    isAddress = true;
                }else {
                    Toast.makeText(this, getString(R.string.notAddress), Toast.LENGTH_SHORT).show();
                }
            }
            //如果电话没填绑定  判断
            if (phone == null || phone.equals("")) {
                if (bean.getResult().getPhone() != null) {
                    isPhone = true;
                }else {
                    Toast.makeText(this, getString(R.string.notPhone), Toast.LENGTH_SHORT).show();
                }
            }
            if (isPhone && isAddress) {
                finish();//如果两个都绑定了则退出绑定页面
            }
        });
    }

    @Override
    public void setPhoneOrAddress(LoginBean loginBean) {
        bean=loginBean;
    }
}