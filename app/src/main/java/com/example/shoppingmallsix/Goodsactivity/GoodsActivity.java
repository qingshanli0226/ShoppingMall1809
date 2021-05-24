package com.example.shoppingmallsix.Goodsactivity;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

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
    private String name;
    private String figure;
    private String price;
    private LinearLayout shopcar;
    private android.widget.Button insertShopcar;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        figure = intent.getStringExtra("figure");
        price = intent.getStringExtra("price");
        GoodAdapterBean goodAdapterBean = new GoodAdapterBean(name, figure, price);
        list.add(goodAdapterBean);
        list.add(goodAdapterBean);
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        moreRv = (RecyclerView) findViewById(R.id.moreRv);
        shopcar = findViewById(R.id.shopcar);
        insertShopcar = findViewById(R.id.insertShopcar);
        goodsAdapter = new GoodsAdapter(list);
        moreRv.setAdapter(goodsAdapter);
        moreRv.setLayoutManager(new LinearLayoutManager(this));
        insertShopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popupWindow = new PopupWindow();
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);

                View inflate = LayoutInflater.from(GoodsActivity.this).inflate(R.layout.item_particulars_pop, null);
                popupWindow.setContentView(inflate);

                popupWindow.showAsDropDown(toolbar,0,900,Gravity.BOTTOM);
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods;
    }


        @Override
        public void onRightImgClick() {
            super.onRightImgClick();
            PopupWindow popupWindow = new PopupWindow();

            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);



            View inflate = LayoutInflater.from(this).inflate(R.layout.item_pop, null);
            popupWindow.setContentView(inflate);


            LinearLayout viewById = inflate.findViewById(R.id.toMain);
            viewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    popupWindow.dismiss();
                }
            });

            popupWindow.showAsDropDown(toolbar);
        }

}
