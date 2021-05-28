package com.example.threeshopping.type.typechild.label;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LabelBean;
import com.example.threeshopping.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LabelFragment extends BaseFragment<LabelPresenter> implements ILabelView {


    private ToolBar toolbar;
    private RecyclerView LabelRv;
    List<LabelBean.ResultBean> list = new ArrayList<>();
    LabelAdapter labelAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_label;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        LabelRv = (RecyclerView) findViewById(R.id.LabelRv);
    }

    @Override
    protected void initPrensenter() {
        mPresenter = new LabelPresenter(this);
    }

    @Override
    protected void initData() {
        mPresenter.getLabel();
        labelAdapter = new LabelAdapter();

        LabelRv.setAdapter(labelAdapter);
        LabelRv.setLayoutManager(new GridLayoutManager(getActivity(),3));
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
    public void onILabel(LabelBean labelBean) {
        List<LabelBean.ResultBean> result = labelBean.getResult();
        list.addAll(result);
        labelAdapter.getData().addAll(list);
        labelAdapter.notifyDataSetChanged();
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
