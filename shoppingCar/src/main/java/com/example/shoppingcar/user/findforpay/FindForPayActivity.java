package com.example.shoppingcar.user.findforpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.manager.ShoppingCarManager;
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
        FindForBean findForBean = ShoppingCarManager.getInstance().getFindForPayBean();
        LogUtils.json(findForBean);
        if (findForBean!=null){
            List<FindForBean.ResultBean> result = findForBean.getResult();
            LogUtils.json(result);
            FindForPayAdapter findForPayAdapter = new FindForPayAdapter(result);
            shopActPayRv.setAdapter(findForPayAdapter);
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        shopActPayRv = (RecyclerView) findViewById(R.id.shop_act_pay_rv);
        shopActPayRv.setLayoutManager(new LinearLayoutManager(FindForPayActivity.this));
    }
}