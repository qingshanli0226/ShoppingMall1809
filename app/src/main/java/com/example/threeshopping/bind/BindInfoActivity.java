package com.example.threeshopping.bind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheAddrManager;
import com.example.framework.manager.CacheMessageManager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.SelectBean;
import com.example.threeshopping.R;
import com.fiannce.sql.bean.AddrBean;

import java.io.Serializable;

import retrofit2.http.Body;


public class BindInfoActivity extends BaseActivity<BindPresenter> implements IBindView {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText number;
    private android.widget.EditText location;
    private boolean oneFlag = false;
    private boolean twoFlag = false;
    private String phone;
    private String addr;
    private Button bindSave;
    private CheckBox setDefault;
    private int position;
    private String mAddr;
    private String mPhone;
    private Boolean mFlag;
    private TextView locaYes;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_info;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        number = (EditText) findViewById(R.id.number);
        location = (EditText) findViewById(R.id.location);
        toolbar.setToolbarOnClickLisenter(this);
        bindSave = (Button) findViewById(R.id.bindSave);
        setDefault = (CheckBox) findViewById(R.id.setDefault);
        locaYes = (TextView) findViewById(R.id.locaYes);
    }

    @Override
    public void initPresenter() {
        mPresenter = new BindPresenter(this);
    }

    @Override
    public void initData() {
        //传数据
        Bundle bundle = CommonArouter.getInstance().getBundle();
        position = bundle.getInt(Constants.DEFAULT_ADDR);
        mAddr = bundle.getString(Constants.ADD_ADDR);
        mPhone = bundle.getString(Constants.PHONE_ADDR);
        mFlag = bundle.getBoolean(Constants.FLAG_ADDR);
        setDefault.setChecked(mFlag);
        isPhoneShow();

        //内容添加
        if (mAddr != null && mPhone != null) {
            number.setText(mPhone);
            location.setText(mAddr);
        }
        //绑定
        bindSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = number.getText().toString();
                addr = location.getText().toString();
                if(TextUtils.isEmpty(phone) && TextUtils.isEmpty(addr)){
                    Toast.makeText(BindInfoActivity.this, "有一项为空", Toast.LENGTH_SHORT).show();
                } else{
                        if(setDefault.isChecked()){
                            //进行网络请求
                            mPresenter.setPhone(phone);
                            mPresenter.setAddr(addr);
                        } else{
                            //修改数据库地址
                            if(position != -1){
                                update();
                            } else{
                                addAddr();
                            }
                        }
                }

            }
        });
        setDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPhoneShow();
            }
        });

        locaYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.PHONE_ADDR,number.getText().toString());
                bundle.putInt(Constants.DEFAULT_ADDR,position);
                bundle.putBoolean(Constants.FLAG_ADDR,setDefault.isChecked());
                CommonArouter.getInstance().build(Constants.PATH_MAP).with(bundle).navigation();

            }
        });

    }

    private void isPhoneShow() {
        if(setDefault.isChecked()){
            setDefault.setBackground(getDrawable(R.drawable.toggle_on));
        } else{
            setDefault.setBackground(getDrawable(R.drawable.toggle_off));
        }
    }

    private void addAddr() {
        if(setDefault.isChecked()){
            CacheUserManager.getInstance().getLoginBean().getResult().setAddress(addr);
            CacheUserManager.getInstance().getLoginBean().getResult().setPhone(phone);
        }

            //添加数据库一个
        AddrBean addrBean = new AddrBean(null, CacheUserManager.getInstance().getLoginBean().getResult().getName(),
                addr,
                phone,
                setDefault.isChecked());
        CacheAddrManager.getInstance().addAddr(addrBean);
        Bundle bundle = new Bundle();
        bundle.putInt("page", 3);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
        finish();

    }

    private void update() {
        //修改数据库一个
        if(setDefault.isChecked()){
            CacheUserManager.getInstance().getLoginBean().getResult().setAddress(addr);
            CacheUserManager.getInstance().getLoginBean().getResult().setPhone(phone);
        }
        CacheAddrManager.getInstance().update(position,addr,phone,setDefault.isChecked());
        Bundle bundle = new Bundle();
        bundle.putInt("page", 3);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
        finish();

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        finish();
    }

    @Override
    public void onClickRight() {

    }
    @Override
    public void onPhone(SelectBean selectBean) {
        twoFlag = true;
        if(oneFlag && twoFlag){
            if(position != -1){
                update();
            } else{
                addAddr();

            }
        }
    }

    @Override
    public void onAddr(SelectBean selectBean) {
        oneFlag = true;
        if(oneFlag && twoFlag){
            if(position != -1){
                update();
            } else{
                addAddr();
            }
        }
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