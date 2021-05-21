package com.shoppingmall.main.sort;

import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.shoppingmall.R;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.adapter.ComAdapter;
import com.shoppingmall.main.sort.fragment.ClassificationFragment;
import com.shoppingmall.main.sort.fragment.label.LabelFragment;

import java.util.ArrayList;
import java.util.List;


public class SortFragment extends BaseFragment {


    private TextView sortLeft;
    private TextView xxx;
    private TextView sortRight;
    private ViewPager sortVp;
    private RadioGroup sortRadioGroup;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sort;
    }

    @Override
    public void initView() {

        sortLeft = (TextView) mView.findViewById(R.id.sort_left);
        sortRight = (TextView) mView.findViewById(R.id.sort_right);
        sortVp = (ViewPager) mView.findViewById(R.id.sortVp);
        sortRadioGroup = (RadioGroup) mView.findViewById(R.id.sortRadioGroup);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ClassificationFragment());
        fragmentList.add(new LabelFragment());
        ComAdapter comAdapter = new ComAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        sortVp.setAdapter(comAdapter);

        sortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.sort_left:
                        sortVp.setCurrentItem(0);
                        break;
                    case R.id.sort_right:
                        sortVp.setCurrentItem(1);
                        break;
                }
            }
        });
    }
}