package com.example.shoppingmall1809.main.type.category;

import android.graphics.Color;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.model.CategoryBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.type.category.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends BaseFragment<CategoryPresenter> implements ICategoryView {

    private ListView fragTypeCateLv;
    private RecyclerView fragTypeCateRv;
    private List<Object> resultBeans;
    private CategoryAdapter categoryAdapter;
    private SparseArray<View> viewSparseArray;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initData() {

        ArrayList<String> data = new ArrayList<>();
        data.add(getResources().getString(R.string.smallSkirt));
        data.add(getResources().getString(R.string.jacket));
        data.add(getResources().getString(R.string.pants));
        data.add(getResources().getString(R.string.coat));
        data.add(getResources().getString(R.string.parts));
        data.add(getResources().getString(R.string.handbag));
        data.add(getResources().getString(R.string.dressUp));
        data.add(getResources().getString(R.string.theHouseIsTasted));
        data.add(getResources().getString(R.string.stationery));
        data.add(getResources().getString(R.string.peripheral));
        data.add(getResources().getString(R.string.game));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        fragTypeCateLv.setAdapter(arrayAdapter);

        viewSparseArray = new SparseArray<>();


        fragTypeCateLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(Color.WHITE);
                if (viewSparseArray.get(i) == null) {
                    viewSparseArray.put(i, view);
                }
                for (int i1 = 0; i1 < viewSparseArray.size(); i1++) {
                    int i2 = viewSparseArray.keyAt(i1);
                    if (i2 != i) {
                        View item = viewSparseArray.get(i2);
                        item.setBackgroundResource(R.color.darkAshen);
                    }
                }
                httpPresenter.getCategoryData(i);
            }
        });
        categoryAdapter = new CategoryAdapter(resultBeans);
        fragTypeCateRv.setAdapter(categoryAdapter);
        httpPresenter.getCategoryData(0);
    }

    @Override
    public void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                View startView = fragTypeCateLv.getChildAt(fragTypeCateLv.getFirstVisiblePosition());
/*
                View startView = fragTypeCateLv.getChildAt(0 - fragTypeCateLv.getFirstVisiblePosition());
*/
                startView.setBackgroundColor(Color.WHITE);
                viewSparseArray.put(0, startView);
            }
        },1000);

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new CategoryPresenter(this);
    }

    @Override
    protected void initView() {
        fragTypeCateLv = (ListView) findViewById(R.id.frag_type_cate_lv);
        fragTypeCateRv = (RecyclerView) findViewById(R.id.frag_type_cate_rv);
        fragTypeCateRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        resultBeans = new ArrayList<>();
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSucessView();
    }

    @Override
    public void Error(String error) {
        loadingPage.showErrorView(error);
    }

    @Override
    public void getCategoryData(CategoryBean categoryBean) {
        LogUtils.json(categoryBean);
        CategoryBean.ResultBean resultBean = categoryBean.getResult().get(0);
//        resultBeans = new ArrayList<>();
        resultBeans.clear();
        resultBeans.add(resultBean.getHot_product_list());
        resultBeans.add(resultBean.getChild());
        categoryAdapter.notifyDataSetChanged();
    }
}