package com.example.shoppingcar.user.findforpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.FindForBean;
import com.example.shoppingcar.R;
import com.example.shoppingcar.user.findforpay.adapter.FindForPayAdapter;

import java.util.List;

@Route(path = "/shoppingCar/FindForPayActivity")
public class FindForPayActivity extends BaseActivity{

    private RecyclerView shopActPayRv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_for_pay;
    }

    @Override
    protected void initData() {
        List<FindForBean.ResultBean> forPay = ShoppingCarManager.getInstance().getForPay();
        if (forPay!=null){
            FindForPayAdapter findForPayAdapter = new FindForPayAdapter(forPay);
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