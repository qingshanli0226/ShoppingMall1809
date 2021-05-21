package com.shoppingmall.main.shopcar;

import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.shoppingmall.R;
import com.shoppingmall.framework.mvp.BaseFragment;


public class ShopCarFragment extends BaseFragment {


    private TextView goHomeFragment;
    private ViewPager viewPager;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_car;
    }

    @Override
    public void initView() {
        goHomeFragment = (TextView) mView.findViewById(R.id.goHomeFragment);
        viewPager = getActivity().findViewById(R.id.mainVp);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        goHomeFragment.setOnClickListener(v->{
            viewPager.setCurrentItem(0);
        });
    }
}