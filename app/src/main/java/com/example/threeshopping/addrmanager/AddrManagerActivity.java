package com.example.threeshopping.addrmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.CacheAddrManager;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.SelectBean;
import com.example.threeshopping.R;
import com.example.threeshopping.addrmanager.adapter.AddrAdapter;
import com.example.threeshopping.bind.BindPresenter;
import com.example.threeshopping.bind.IBindView;
import com.fiannce.sql.bean.AddrBean;

import java.util.List;

public class AddrManagerActivity extends BaseActivity<BindPresenter> implements CacheAddrManager.IAddrListener {

    private AddrAdapter addrAdapter;
    private RecyclerView addrRv;
    private boolean oneFlag = false;
    private boolean twoFlag = false;
    private int mPosition = -1;


    @Override
    public int getLayoutId() {
        return R.layout.activity_addr_manager;
    }

    @Override
    public void initView() {
        addrRv = (RecyclerView) findViewById(R.id.addrRv);
        addrAdapter = new AddrAdapter();
        addrRv.setLayoutManager(new LinearLayoutManager(this));
        addrRv.setAdapter(addrAdapter);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //注册
        CacheAddrManager.getInstance().registerListener(this);

        //更新数据
        addrAdapter.updata(CacheAddrManager.getInstance().getAddrBeans());
        addrAdapter.setAddrListener(new AddrAdapter.IAddrListener() {
            @Override
            public void onEditClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.ADD_ADDR,addrAdapter.getData().get(position).getAddr());
                bundle.putString(Constants.PHONE_ADDR,addrAdapter.getData().get(position).getPhone());
                bundle.putBoolean(Constants.FLAG_ADDR,addrAdapter.getData().get(position).getIsDefault());
                bundle.putInt(Constants.DEFAULT_ADDR,position);
                CommonArouter.getInstance().build(Constants.PATH_BIND).with(bundle).navigation();
            }
        });
    }
    //全部
    @Override
    public void onrefreshAll(List<AddrBean> addrBeans) {
        addrAdapter.updata(addrBeans);
    }
    //刷新一个
    @Override
    public void onrefreshOne(int position) {
        addrAdapter.notifyItemChanged(position);
    }

    @Override
    public void destroy() {
        super.destroy();
        CacheAddrManager.getInstance().unRegisterListener(this);

    }


    @Override
    public void onClickRight() {
        super.onClickRight();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ADD_ADDR,null);
        bundle.putString(Constants.PHONE_ADDR,null);
        bundle.putInt(Constants.DEFAULT_ADDR,-1);
        bundle.putBoolean(Constants.FLAG_ADDR,false);
        CommonArouter.getInstance().build(Constants.PATH_BIND).with(bundle).navigation();
    }
}