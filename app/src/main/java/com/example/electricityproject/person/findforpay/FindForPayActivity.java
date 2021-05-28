package com.example.electricityproject.person.findforpay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.BaseAdapter;
import com.example.common.bean.FindForPayBean;
import com.example.electricityproject.R;
import com.example.framework.BaseActivity;

public class FindForPayActivity extends BaseActivity<FindForPayPresenter> implements IFindForPayView {
    private FindForPayAdapter findForPayAdapter;
    private RecyclerView unpaidRe;

    @Override
    protected void initData() {
        httpPresenter = new FindForPayPresenter(this);
        httpPresenter.getForPayData();
        findForPayAdapter.setRecyclerItemClickListener(new BaseAdapter.iRecyclerItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FindForPayActivity.this);
                builder.setTitle("确认支付该订单");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }

            @Override
            public void OnItemLongClick(int position) {

            }
        });
    }

    @Override
    protected void initPresenter() {


    }

    @Override
    protected void initView() {
        unpaidRe = (RecyclerView) findViewById(R.id.unpaid_re);

        findForPayAdapter=new FindForPayAdapter();
        unpaidRe.setLayoutManager(new LinearLayoutManager(FindForPayActivity.this));
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

    @Override
    public void getFindForPayData(FindForPayBean findForPayBean) {
        if (findForPayBean.getCode().equals("200")){

            findForPayAdapter.updateData(findForPayBean.getResult());
            unpaidRe.setAdapter(findForPayAdapter);

        }
    }
}