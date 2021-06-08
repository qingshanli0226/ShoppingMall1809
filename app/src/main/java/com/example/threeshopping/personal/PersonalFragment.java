package com.example.threeshopping.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.threeshopping.R;


public class PersonalFragment extends BaseFragment {


    private ToolBar toolbar;
    private ImageView message;
    private ImageView payment;
    private ImageView shipments;
    private LinearLayout personAddr;
    private ImageView setting;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) rootView.findViewById(R.id.toolbar);
        message = (ImageView) rootView.findViewById(R.id.message);//消息
        payment = (ImageView) rootView.findViewById(R.id.payment);//待付款
        shipments = (ImageView) rootView.findViewById(R.id.shipments);//待发货
        personAddr = (LinearLayout) findViewById(R.id.personAddr);
        setting = (ImageView) findViewById(R.id.setting);
    }

    @Override
    protected void initPrensenter() {
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_MESSAGE).navigation();
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_AWAITPAYMENT).navigation();
            }
        });
        shipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_SHIPMENTS).navigation();
            }
        });
        //收获地址管理
        personAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_ADDRMANAGER).navigation();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonArouter.getInstance().build(Constants.PATH_SETTING).navigation();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }
}