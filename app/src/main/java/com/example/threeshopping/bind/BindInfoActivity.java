package com.example.threeshopping.bind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.SelectBean;
import com.example.threeshopping.R;


public class BindInfoActivity extends BaseActivity<BindPresenter> implements IBindView {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText number;
    private android.widget.Button bindNumber;
    private android.widget.EditText location;
    private android.widget.Button bindLocation;
    private boolean oneFlag = false;
    private boolean twoFlag = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_info;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        number = (EditText) findViewById(R.id.number);
        bindNumber = (Button) findViewById(R.id.bind_number);
        location = (EditText) findViewById(R.id.location);
        bindLocation = (Button) findViewById(R.id.bind_location);
        toolbar.setToolbarOnClickLisenter(this);
    }

    @Override
    public void initPresenter() {
        mPresenter = new BindPresenter(this);
    }

    @Override
    public void initData() {
        //绑定电话
        bindNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(number.getText().toString())){
                    //为空
                    Toast.makeText(BindInfoActivity.this, "电话为空", Toast.LENGTH_SHORT).show();
                } else{
                    mPresenter.setPhone(number.getText().toString());
                }
            }
        });
        //绑定地址
        bindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(location.getText().toString())){
                    //为空
                    Toast.makeText(BindInfoActivity.this, "地址为空", Toast.LENGTH_SHORT).show();
                } else{
                    mPresenter.setAddr(location.getText().toString());

                }
            }
        });

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
        Toast.makeText(this, "电话成功", Toast.LENGTH_SHORT).show();
        if(oneFlag && twoFlag){
            finish();
            Bundle bundle = new Bundle();
            bundle.putInt("page",3);
            CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
        }
    }

    @Override
    public void onAddr(SelectBean selectBean) {
        oneFlag = true;
        Toast.makeText(this, "地址成功", Toast.LENGTH_SHORT).show();
        if(oneFlag && twoFlag){
            finish();
            Bundle bundle = new Bundle();
            bundle.putInt("page",3);
            CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
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