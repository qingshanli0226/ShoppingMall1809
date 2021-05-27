package com.example.electricityproject.classify.kind.tag;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.ClassifyBean;
import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;

import java.util.List;


public class TagFragment extends BaseFragment<TagPresenter> implements ITagView {

    private RecyclerView tagRv;

    @Override
    protected void initData() {
        httpPresenter.getTagData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new TagPresenter(this);
    }

    @Override
    protected void initView() {
        tagRv = (RecyclerView) findViewById(R.id.tag_rv);
        tagRv.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tag;
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

    @Override
    public void onTagData(ClassifyBean classifyBean) {

        List<ClassifyBean.ResultBean> result = classifyBean.getResult();
        TagAdapter adapter = new TagAdapter();
        adapter.updateData(classifyBean.getResult());
        tagRv.setAdapter(adapter);

    }
}