package com.example.shoppingmall1809.main.type.sort;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.model.SortBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.main.type.sort.adapter.SortAdapter;


public class SortFragment extends BaseFragment<SortPresenter> implements ISortView {

    private RecyclerView fragTypeSortRv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initData() {
        httpPresenter.getSortData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new SortPresenter(this);
    }

    @Override
    protected void initView() {
        fragTypeSortRv = (RecyclerView) findViewById(R.id.frag_type_sort_rv);
    }

    @Override
    public void getSortData(SortBean sortBean) {
        LogUtils.json(sortBean);
        SortAdapter sortAdapter = new SortAdapter(sortBean.getResult());
        fragTypeSortRv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        fragTypeSortRv.setAdapter(sortAdapter);
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

}