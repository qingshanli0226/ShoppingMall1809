package com.example.electricityproject.classify;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.electricityproject.classify.KindFragment;
import com.example.electricityproject.classify.MyFragmentAdapter;
import com.example.electricityproject.classify.TagFragment;
import com.example.framework.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends BaseFragment {

    private TabLayout tableLayout;
    private ViewPager vp;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private MyFragmentAdapter adapter;

    @Override
    protected void initData() {

        fragmentList.add(new KindFragment());
        fragmentList.add(new TagFragment());

        stringList.add("分类");
        stringList.add("标签");

        adapter = new MyFragmentAdapter(getChildFragmentManager(),fragmentList,stringList);
        vp.setAdapter(adapter);
        tableLayout.setupWithViewPager(vp);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        tableLayout = (TabLayout) findViewById(R.id.table_layout);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}