package com.example.threeshopping.personal;

import android.widget.ImageView;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.threeshopping.R;


public class PersonalFragment extends BaseFragment {


    private ToolBar toolbar;
    private ImageView message;
    private ImageView payment;
    private ImageView shipments;

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
    }

    @Override
    protected void initPrensenter() {

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