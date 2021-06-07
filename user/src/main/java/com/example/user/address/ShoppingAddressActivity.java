package com.example.user.address;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.framework.db.AddressTable;
import com.example.framework.manager.AddressManager;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.RegisterBean;
import com.example.user.R;

@Route(path = "/address/ShoppingAddressActivity")
public class ShoppingAddressActivity extends BaseActivity<AddressPresenter> {

    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText consignee;
    private android.widget.EditText phone;
    private android.widget.EditText detailedAddress;
    private android.widget.TextView use;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_address;
    }

    @Override
    protected void initData() {
        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressManager.getInstance().addAddress(new AddressTable(null,
                        consignee.getText().toString().trim(),phone.getText().toString().trim(),
                        detailedAddress.getText().toString().trim(),false));

                finish();

            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        consignee = (EditText) findViewById(R.id.consignee);
        phone = (EditText) findViewById(R.id.phone);
        detailedAddress = (EditText) findViewById(R.id.detailedAddress);
        use = (TextView) findViewById(R.id.use);
    }
}