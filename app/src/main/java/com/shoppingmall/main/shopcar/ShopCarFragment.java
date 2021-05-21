package com.shoppingmall.main.shopcar;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.mvp.BaseFragment;


public class ShopCarFragment extends BaseFragment {


    private ImageView xxx1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_car;
    }

    public void initView() {
        xxx1 = (ImageView) mView.findViewById(R.id.xxx1);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        Glide.with(getActivity()).load("https://browser9.qhimg.com/bdm/1000_618_80/t01753453b660de14e9.jpg")
                .into(xxx1);
    }
}