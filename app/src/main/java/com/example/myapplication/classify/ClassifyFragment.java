package com.example.myapplication.classify;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.framework.BaseFragment;
import com.example.myapplication.R;
import com.example.user.frag.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;


public class ClassifyFragment extends BaseFragment {


    private ViewPager vp;
    private List<Fragment> list = new ArrayList<>();
    private RadioButton type;
    private RadioButton biao;
    private FragmentAdapter fragmentAdapter;
    private RadioGroup rp;

    @Override
    public int bandLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initView() {

        vp = (ViewPager) findViewById(R.id.vp);


        type = (RadioButton) findViewById(R.id.type);
        biao = (RadioButton) findViewById(R.id.biao);
        rp = (RadioGroup) findViewById(R.id.rp);

        list.add(new TypeFragment());
        list.add(new ShowFragment());
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), list);
        vp.setAdapter(fragmentAdapter);
          rp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                  if (checkedId==R.id.type){
                      vp.setCurrentItem(0);
                  }else if (checkedId==R.id.biao){
                      vp.setCurrentItem(1);
                  }
              }
          });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}