package com.example.shoppingmallsix.fragment.classifyfragment;


import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.example.shoppingmallsix.R;

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
        radioGroup = mBaseView.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.buttonLeft:
                        buttonLeft.setBackgroundResource(select_left_show);
                        buttonLeft.setTextColor(Color.WHITE);
                        buttonRight.setBackgroundResource(select_right);
                        buttonRight.setTextColor(Color.RED);
                        break;
                    case R.id.buttonRight:
                        buttonRight.setBackgroundResource(select_right_show);
                        buttonRight.setTextColor(Color.WHITE);
                        buttonLeft.setBackgroundResource(select_left);
                        buttonLeft.setTextColor(Color.RED);
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

}
