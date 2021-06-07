package com.example.shoppingmallsix.address;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.user.UpdateAddressBean;
import com.example.net.bean.user.UpdatePhoneBean;
import com.example.shoppingmallsix.R;

public class AddressActivity extends BaseActivity<AddressPresenter> implements IAddress {
    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText consignee;
    private android.widget.EditText phone;
    private android.widget.EditText detailedAddress;
    private android.widget.TextView use;

    private boolean isPhone=false;
    private boolean isAddress=false;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (isPhone&&isAddress){
                ShopeUserManager.getInstance().addPhoneAddress(phone.getText().toString().trim(),detailedAddress.getText().toString().trim());
                finish();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initData() {
        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpPresenter.getUpDataPhone(phone.getText().toString().trim());
                httpPresenter.getUpDataAddress(detailedAddress.getText().toString().trim());
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new AddressPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        consignee = (EditText) findViewById(R.id.consignee);
        phone = (EditText) findViewById(R.id.phone);
        detailedAddress = (EditText) findViewById(R.id.detailedAddress);
        use = (TextView) findViewById(R.id.use);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpDataPhone(UpdatePhoneBean updatePhoneBean) {
        if (updatePhoneBean.getCode().equals("200")){
            isPhone=true;
            handler.sendEmptyMessage(1);
        }
    }

    @Override
    public void onUpDataAddress(UpdateAddressBean updateAddressBean) {
        if (updateAddressBean.getCode().equals("200")){
            isAddress=true;
            handler.sendEmptyMessage(1);
        }
    }
}
