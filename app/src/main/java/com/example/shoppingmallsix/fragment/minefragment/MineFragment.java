package com.example.shoppingmallsix.fragment.minefragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.shoppingmallsix.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {
    private ToolBar toolbar;
    private LinearLayout obligation;
    private LinearLayout sendgoods;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


        toolbar = (ToolBar) mBaseView.findViewById(R.id.toolbar);
        obligation = (LinearLayout) mBaseView.findViewById(R.id.obligation);
        sendgoods = (LinearLayout)mBaseView. findViewById(R.id.sendgoods);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
