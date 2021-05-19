package com.example.shoppingmallsix.fragment.classifyfragment;


import android.graphics.Color;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.ClassFragment;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.LabelFragment;

import static com.example.shoppingmallsix.R.drawable.select_left;
import static com.example.shoppingmallsix.R.drawable.select_left_show;
import static com.example.shoppingmallsix.R.drawable.select_right;
import static com.example.shoppingmallsix.R.drawable.select_right_show;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment {

    private RadioButton buttonLeft;
    private RadioButton buttonRight;
    private RadioGroup radioGroup;
    private ToolBar toolbar;


    @Override
    protected void initPresenter() {
    }

    private void inits() {
        initViewGroup();
        initRecyclerView();
    }

    private void initRecyclerView() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ClassifyFrgme, new ClassFragment()).commitAllowingStateLoss();
    }

    private void initViewGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.buttonLeft:
                        buttonLeft.setBackgroundResource(select_left_show);
                        buttonLeft.setTextColor(Color.WHITE);
                        buttonRight.setBackgroundResource(select_right);
                        buttonRight.setTextColor(Color.RED);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ClassifyFrgme, new ClassFragment()).commitAllowingStateLoss();
                        break;
                    case R.id.buttonRight:
                        buttonRight.setBackgroundResource(select_right_show);
                        buttonRight.setTextColor(Color.WHITE);
                        buttonLeft.setBackgroundResource(select_left);
                        buttonLeft.setTextColor(Color.RED);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ClassifyFrgme, new LabelFragment()).commitAllowingStateLoss();
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        buttonLeft = mBaseView.findViewById(R.id.buttonLeft);
        buttonRight = mBaseView.findViewById(R.id.buttonRight);
        radioGroup = mBaseView.findViewById(R.id.radioGroup);
        toolbar = (ToolBar) mBaseView.findViewById(R.id.toolbar);
        inits();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

}