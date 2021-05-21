package com.shoppingmall.main.sort.fragment.label;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shoppingmall.R;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.sort.fragment.adapter.LabelAdapter;
import com.shoppingmall.net.bean.LabelBean;

import java.util.ArrayList;


public class LabelFragment extends BaseFragment<LabelPresenter> implements ILabelView {

    private RecyclerView vp;
    private ArrayList<LabelBean.ResultBean> list=new ArrayList<>();
    private LabelAdapter labelAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_label;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {
        httpPresenter = new LabelPresenter(this);
    }

    @Override
    public void initData() {
        httpPresenter.getLabelData();
    }


    @Override
    public void getLabelData(LabelBean labelBean) {
        vp =mView.findViewById(R.id.vp);
        labelAdapter = new LabelAdapter();
        vp.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        vp.setAdapter(labelAdapter);
        labelAdapter.updateData(labelBean.getResult());
    }
}