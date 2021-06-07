package com.example.user.addresslist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.db.AddressTable;
import com.example.framework.manager.AddressManager;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.RegisterBean;
import com.example.user.R;
import com.example.user.address.AddressPresenter;
import com.example.user.address.IAddress;

import java.util.List;

@Route(path = "/address/AddressListActivity")
public class AddressListActivity extends BaseActivity<AddressPresenter> implements IAddress, AddressManager.IAddressListener {

    private com.example.framework.view.ToolBar toolbar;
    private androidx.recyclerview.widget.RecyclerView addressListRv;
    private TextView addAddress;
    private AddressListAdapter addressListAdapter;
    private List<AddressTable> list;

    private int num;
    private boolean isHttp=true;
    private boolean isPhone=false;
    private boolean isAddress=false;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            LogUtils.e("2:",isAddress);
            LogUtils.e("2:",isPhone);

            if (isPhone&&isAddress){
                AddressManager.getInstance().updateSelect(list.get(num),num);

                ShopeUserManager.getInstance().addPhoneAddress(list.get(num).getPhone(),list.get(num).getAddress());

                isPhone=false;
                isAddress=false;
                isHttp=true;
//                finish();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void initData() {
        AddressManager.getInstance().register(this::IAddressListData);

        addressListAdapter = new AddressListAdapter();

        AddressManager.getInstance().getAddress(list -> {
            this.list=list;
            addressListAdapter.updateDate(list);
            addressListRv.setLayoutManager(new LinearLayoutManager(this));
            addressListRv.setAdapter(addressListAdapter);
        });

        addressListAdapter.setShopItemListener(new AddressListAdapter.IAddressList() {
            @Override
            public void onItemChildClick(int position, View view) {
                int id = view.getId();
                num=position;

                if (id == R.id.addr_default) {
                    if (isHttp){
                        httpPresenter.getUpDataAddress(list.get(position).getAddress());
                        httpPresenter.getUpDataPhone(list.get(position).getPhone());

                        isHttp=false;
                    }
                }else if (id==R.id.addr_remove){
                    LogUtils.json(list.get(position));
                    AddressManager.getInstance().deleteAddress(list.get(position),position);
                }
            }
        });

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ShopConstants.SHOP_ADDRESS).navigation();
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
        addressListRv = (RecyclerView) findViewById(R.id.addressList_rv);
        addAddress = (TextView) findViewById(R.id.addAddress);
    }

    @Override
    public void onUpDataPhone(RegisterBean registerBean) {
        LogUtils.e("1:",isAddress);
        LogUtils.e("1:",isPhone);
        if (registerBean.getCode().equals("200")){
            isPhone=true;
            handler.sendEmptyMessage(1);
        }
    }

    @Override
    public void onUpDataAddress(RegisterBean registerBean) {
        LogUtils.e("3:",isAddress);
        LogUtils.e("3:",isPhone);
        if (registerBean.getCode().equals("200")){
            isAddress=true;
            handler.sendEmptyMessage(1);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void IAddressListData(List<AddressTable> list, int position) {
        if (position==-1){
            addressListAdapter.notifyDataSetChanged();
            return;
        }
        addressListAdapter.notifyItemChanged(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AddressManager.getInstance().unregister();
        handler.removeCallbacks(null);
    }
}