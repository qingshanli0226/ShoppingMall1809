package com.example.pay.user.findforpay;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.FindForBean;
import com.example.pay.R;
import com.example.pay.user.adapter.FindForAdapter;

import java.util.List;

@Route(path = "/pay/FindForPayActivity")
public class FindForPayActivity extends BaseActivity{

    private RecyclerView shopActPayRv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_for_pay;
    }

    @Override
    protected void initData() {
        List<FindForBean.ResultBean> forPay = ShoppingCarManager.getInstance().getForPay();
        LogUtils.json(forPay);
        if (forPay!=null){
            FindForAdapter findForPayAdapter = new FindForAdapter(forPay);
            shopActPayRv.setAdapter(findForPayAdapter);


            findForPayAdapter.setRecyclerItemClickListener(new BaseRVAdapter.IRecyclerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FindForPayActivity.this);
                    builder.setTitle(getResources().getString(R.string.hint));
                    builder.setMessage(getResources().getString(R.string.areYouSureYouWantToContinuePaying));
                    builder.setPositiveButton(getResources().getString(R.string.confirm),(dialogInterface, i) -> {

                    });
                    builder.setNegativeButton(getResources().getString(R.string.cancel),(dialogInterface, i) -> {
                    });
                    builder.show();
                }

                @Override
                public void onItemLongClick(int position) {

                }
            });
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        shopActPayRv = (RecyclerView) findViewById(R.id.shop_act_pay_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FindForPayActivity.this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        shopActPayRv.setLayoutManager(linearLayoutManager);
    }
}