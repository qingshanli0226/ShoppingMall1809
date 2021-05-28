package com.example.electricityproject.person.findforpay;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.bean.FindForPayBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;

public class FindForPayActivity extends BaseActivity<FindForPayPresenter> implements IFindForPayView {
    private FindForPayAdapter findForPayAdapter;
    private RecyclerView unpaidRe;

    @Override
    public void getFindForPayData(FindForPayBean findForPayBean) {
        if (findForPayBean.getCode().equals("200")){
            findForPayAdapter.updateData(findForPayBean.getResult());

        }
    }

    @Override
    protected void initData() {
        httpPresenter.getForPayData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new FindForPayPresenter(this);
        unpaidRe.setLayoutManager(new LinearLayoutManager(FindForPayActivity.this));

        unpaidRe.setAdapter(findForPayAdapter);
    }

    @Override
    protected void initView() {
        unpaidRe = (RecyclerView) findViewById(R.id.unpaid_re);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_for_pay;
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
        loadingPage.showErrorView();

    }
    //网络从断开变为已连接,重新加载数据
    @Override
    public void OnConnect() {
        Toast.makeText(this, "网络重新连接,重新加载数据", Toast.LENGTH_SHORT).show();
        httpPresenter.getForPayData();
    }

    @Override
    public void DisConnect() {

    }
}