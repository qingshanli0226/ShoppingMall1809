package com.shoppingmall.main.shopcar;


import android.view.View;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.mvp.BaseFragment;

import org.greenrobot.eventbus.EventBus;


public class ShopCarFragment extends BaseFragment {

    private TextView text;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_car;
    }

    @Override
    public void initView() {
        text =mView.findViewById(R.id.text);
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post("111");
//            }
//        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}