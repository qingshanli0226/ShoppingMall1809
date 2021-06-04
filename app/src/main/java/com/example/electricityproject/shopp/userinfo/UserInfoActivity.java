package com.example.electricityproject.shopp.userinfo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.BaseAdapter;
import com.example.common.LogUtils;
import com.example.common.bean.UpdateAddress;
import com.example.common.bean.UpdatePhoneBean;
import com.example.electricityproject.R;
import com.example.electricityproject.shopp.userinfo.infodb.DaoMaster;
import com.example.electricityproject.shopp.userinfo.infodb.UserInfoTable;
import com.example.electricityproject.shopp.userinfo.infodb.UserInfoTableManger;
import com.example.framework.BaseActivity;
import com.example.manager.BusinessUserManager;
import com.example.view.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends BaseActivity<BindUserInfoPresenter> implements ToolBar.IToolbarListener,IBindUserInfoView{

    private com.example.view.ToolBar toolbar;
    private UserInfoAdapter userInfoAdapter;
    private List<UserInfoTable> list = new ArrayList<>();
    private androidx.recyclerview.widget.RecyclerView bindAddress;
    private android.widget.Button conAddInfo;
    private DaoMaster daoMaster;
    private String phone;
    private String address;

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);

        //查找数据
        DaoMaster daoMaster = UserInfoTableManger.getInstance().getDaoMaster(this);

        list = daoMaster.newSession().loadAll(UserInfoTable.class);
        if (list !=null){
            userInfoAdapter = new UserInfoAdapter();
            userInfoAdapter.updateData(this.list);
            LogUtils.i(this.list.toString());
            bindAddress.setAdapter(userInfoAdapter);
            userInfoAdapter.notifyDataSetChanged();
        }

        //点击选择地址
        userInfoAdapter.setRecyclerItemClickListener(new BaseAdapter.iRecyclerItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(UserInfoActivity.this, ""+position, Toast.LENGTH_SHORT).show();

                if (list.get(position).getIsShow()){
                    list.get(position).setIsShow(false);
                }else {
                    list.get(position).setIsShow(true);
                }
                userInfoAdapter.notifyItemChanged(position);
            }

            @Override
            public void OnItemLongClick(int position) {

            }
        });

        conAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (UserInfoTable userInfoTable : list) {
                    if (userInfoTable.getIsShow()){
                        address = userInfoTable.getAddress();
                        phone = userInfoTable.getPhone();
                    }
                }
                LogUtils.i(address+"");
                LogUtils.i(phone+"");
                httpPresenter.postUpdateAddressData(address);
            }
        });


    }

    @Override
    protected void initPresenter() {
        httpPresenter = new BindUserInfoPresenter(this);
    }

    @Subscribe
    public void event(String even){
        LogUtils.i(even+"");

        if (even.equals("bindinfo")){
            daoMaster = UserInfoTableManger.getInstance().getDaoMaster(this);
            list = daoMaster.newSession().loadAll(UserInfoTable.class);
            LogUtils.i(list.toString());
            userInfoAdapter.updateData(list);
            bindAddress.setAdapter(userInfoAdapter);
            userInfoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        bindAddress = (RecyclerView) findViewById(R.id.bind_address);
        bindAddress.setLayoutManager(new LinearLayoutManager(this));
        conAddInfo = (Button) findViewById(R.id.con_add_info);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
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
    public void onRightImgClick() {
        super.onRightImgClick();
        startActivity(new Intent(this,BindUserInfoActivity.class));
    }

    @Override
    public void updatePhone(UpdatePhoneBean updatePhoneBean) {
        if (updatePhoneBean.getCode().equals("200")){
            BusinessUserManager.getInstance().setBindTel(phone);
            Toast.makeText(this, "选择地址成功", Toast.LENGTH_SHORT).show();
            BusinessUserManager.getInstance().setBindTel(true);
            BusinessUserManager.getInstance().setBindAddress(true);
            finish();
        }
    }

    @Override
    public void updateAddress(UpdateAddress updateAddress) {
        if (updateAddress.getCode().equals("200")){
            httpPresenter.postUpdatePhoneData(phone);
            BusinessUserManager.getInstance().setBindAddress(address);
        }
    }
}