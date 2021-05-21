package com.shoppingmall.bawei.shoppingmall1809.glide;

import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.mode.FocusBean;
import com.shoppingmall.bawei.shoppingmall1809.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GlideActivity extends BaseActivity<GlidePresenterImpl> implements GlideContract.IGLideView {

    private GlideAdapter glideAdapter = new GlideAdapter();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new GlidePresenterImpl();
        httpPresenter.attachView(this);

    }

    @Override
    protected void initData() {
        httpPresenter.getFocusData();

    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(glideAdapter);
    }

    @Override
    public void onGlide(FocusBean focusBean) {
        glideAdapter.updateData(focusBean.getResult());
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
