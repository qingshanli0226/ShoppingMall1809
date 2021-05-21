package com.example.threeshopping.type.typechild.classify;


import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.TypeBean;
import com.example.threeshopping.R;
import com.example.threeshopping.type.typechild.classify.adapter.ClassAdapter;
import com.example.threeshopping.type.typechild.classify.adapter.LeftAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment<ClassPrensenter> implements IClassView {


    private RecyclerView classRv;
    private ArrayAdapter<String> stringArrayAdapter;
    private List<String> title;
    private List<Object> typeList = new ArrayList<>();
    private ClassAdapter classAdapter;
    private LeftAdapter leftAdapter;
    private RecyclerView leftRv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        classRv = (RecyclerView) findViewById(R.id.classRv);
        leftRv = (RecyclerView) findViewById(R.id.leftRv);
    }

    @Override
    protected void initPrensenter() {
        mPresenter = new ClassPrensenter(this);
    }

    @Override
    protected void initData() {
        title = new ArrayList<>();
        title.add("小裙子");
        title.add("上衣");
        title.add("下装");
        title.add("小外套");
        title.add("配件");
        title.add("包包");
        title.add("装扮");
        title.add("居家宅品");
        title.add("办公文具");
        title.add("数码周边");
        title.add("游戏专区");
        leftAdapter = new LeftAdapter();
        leftAdapter.updata(title);
        leftAdapter.setPosition(0);
        leftRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        leftRv.setAdapter(leftAdapter);
        mPresenter.getSkirt(0);

        leftAdapter.setRvItemOnClickListener(new BaseRvAdapter.IRvItemOnClickListener() {
            @Override
            public void onItemClick(int position,View view) {
                mPresenter.getSkirt(position);
                leftAdapter.setPosition(position);
                leftAdapter.notifyDataSetChanged();



            }

            @Override
            public boolean onLongItemClick(int position,View view) {
                return false;
            }
        });

        classAdapter = new ClassAdapter();
        classRv.setAdapter(classAdapter);
        classRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onType(TypeBean typeBean) {
        typeList.clear();
        typeList.add(typeBean.getResult().get(0).getHot_product_list());
        typeList.add(typeBean.getResult().get(0).getChild());
        classAdapter.updata(typeList);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        loadPage.showErrorText(error);
    }
}
