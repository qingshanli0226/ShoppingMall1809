package com.example.shoppingmallsix.fragment.classifyfragment;


import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.example.shoppingmallsix.R;

import static com.example.shoppingmallsix.R.drawable.select_left_show;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment {

    private Button buttonLeft;
    private Button buttonRight;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        buttonLeft = mBaseView.findViewById(R.id.buttonLeft);
        buttonRight = mBaseView.findViewById(R.id.buttonRight);
        
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

}
