package com.example.shoppingmallsix.fragment.homefragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.shoppingmallsix.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    private ToolBar toolbar;
    private EditText edtname;
    private RecyclerView rv;




    public HomeFragment() {
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
        edtname = (EditText) mBaseView.findViewById(R.id.edtname);
        rv = (RecyclerView) mBaseView.findViewById(R.id.rv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}
