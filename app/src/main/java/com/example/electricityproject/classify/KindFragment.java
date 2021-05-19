package com.example.electricityproject.classify;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.electricityproject.classify.kind.KindAdapter;
import com.example.electricityproject.classify.kind.KindBean;
import com.example.framework.BaseFragment;
import com.example.view.ToolBar;

import java.util.ArrayList;
import java.util.List;


public class KindFragment extends BaseFragment {

    private List<KindBean> list = new ArrayList<>();
    private ListView kindListview;
    private KindAdapter adapter;

    @Override
    protected void initData() {

        list.add(new KindBean("小裙子"));
        list.add(new KindBean("上衣"));
        list.add(new KindBean("下装"));
        list.add(new KindBean("外套"));
        list.add(new KindBean("配件"));
        list.add(new KindBean("包包"));
        list.add(new KindBean("装扮"));
        list.add(new KindBean("居家宅品"));
        list.add(new KindBean("办公文具"));
        list.add(new KindBean("数据周边"));
        list.add(new KindBean("游戏专区"));

        KindAdapter kindAdapter = new KindAdapter(getContext(), R.layout.item_kind, list);
        kindListview.setAdapter(kindAdapter);

        kindListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        kindListview = (ListView) findViewById(R.id.kind_listview);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kind;
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