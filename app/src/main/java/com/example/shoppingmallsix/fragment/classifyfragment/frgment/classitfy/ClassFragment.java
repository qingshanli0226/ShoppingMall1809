package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.StationeryBean;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.adapter.ClassifyAdapter;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.IClassView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends BaseFragment implements IClassView {

    private RecyclerView classifyLeftRecyclerView;
    private RecyclerView classifyRightRecyclerView;
    private String[] strings = new String[]{"小裙子","上衣","下装","外套","配件","包包","装扮","居家宅品","办公文具","数据周边","游戏专区"};
    private List<String> list = new ArrayList<>();
    @Override
    protected void initPresenter() {

    }
    private void inits() {
        initRecyclerView();
    }

    @Override
    protected void initData() {
        //存储list集合
        for (int i = 0; i <strings.length ; i++) {
            list.add(strings[i]);
        }
        inits();
    }

    private void initRecyclerView() {

        ClassifyAdapter classifyAdapter = new ClassifyAdapter(list);
        classifyLeftRecyclerView.setAdapter(classifyAdapter);
        classifyLeftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initView() {
        classifyLeftRecyclerView = (RecyclerView) mBaseView.findViewById(R.id.classify_left_recycler_view);
        classifyRightRecyclerView = (RecyclerView) mBaseView.findViewById(R.id.classify_right_recycler_view);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class;
    }








    @Override
    public void onJacketData(JacketBean jacketBean) {

    }

    @Override
    public void onPantsBean(PantsBean pantsBean) {

    }

    @Override
    public void onOvercoatBean(OvercoatBean overcoatBean) {

    }

    @Override
    public void onAccrssoryBean(AccrssoryBean accrssoryBean) {

    }

    @Override
    public void onBagBean(BagBean bagBean) {

    }

    @Override
    public void onDressBean(DressBean dressBean) {

    }

    @Override
    public void onProductsBean(ProductsBean productsBean) {

    }

    @Override
    public void onStationeryBean(StationeryBean stationeryBean) {

    }

    @Override
    public void onDigitBean(DigitBean digitBean) {

    }

    @Override
    public void onGameBean(GameBean gameBean) {

    }
}
