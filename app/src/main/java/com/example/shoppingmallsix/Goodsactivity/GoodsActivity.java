package com.example.shoppingmallsix.Goodsactivity;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.bean.store.GoodAdapterBean;
import com.example.shoppingmallsix.Goodsactivity.adapter.GoodsAdapter;
import com.example.shoppingmallsix.R;

import java.util.ArrayList;
import java.util.List;

public class GoodsActivity extends BaseActivity {


    private com.example.framework.view.ToolBar toolbar;
    private RecyclerView moreRv;
    private List<GoodAdapterBean> list = new ArrayList<>();
    private GoodsAdapter goodsAdapter;
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String figure = intent.getStringExtra("figure");
        String price = intent.getStringExtra("price");
        GoodAdapterBean goodAdapterBean = new GoodAdapterBean(name, figure, price);
        list.add(goodAdapterBean);
        list.add(goodAdapterBean);
        goodsAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        moreRv = (RecyclerView) findViewById(R.id.moreRv);
        goodsAdapter = new GoodsAdapter(list);
        moreRv.setAdapter(goodsAdapter);
        moreRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods;
    }


}
