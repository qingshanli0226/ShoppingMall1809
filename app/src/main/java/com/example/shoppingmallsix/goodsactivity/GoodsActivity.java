package com.example.shoppingmallsix.goodsactivity;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;

import com.example.net.bean.business.AddOneProductBean;
import com.example.net.bean.business.UpdateProductNumBean;
import com.example.net.bean.store.GoodAdapterBean;
import com.example.shoppingmallsix.goodsactivity.adapter.GoodsAdapter;
import com.example.shoppingmallsix.R;

import java.util.ArrayList;
import java.util.List;

public class  GoodsActivity extends BaseActivity<GoodsPresenter> implements IGoodsView {


    private com.example.framework.view.ToolBar toolbar;
    private RecyclerView moreRv;
    private List<GoodAdapterBean> list = new ArrayList<>();
    private GoodsAdapter goodsAdapter;
    private String name;
    private String figure;
    private String price;
    private LinearLayout shopcar;
    private android.widget.Button insertShopcar;
    private int num = 1;
    private String id;
    private GoodAdapterBean goodAdapterBean;

    @Override
    protected void initPresenter() {
       httpPresenter = new GoodsPresenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        figure = intent.getStringExtra("figure");
        price = intent.getStringExtra("price");
        goodAdapterBean = new GoodAdapterBean(id,name, figure, price);
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

                ImageView popImg = inflate.findViewById(R.id.pop_img);
                TextView popText = inflate.findViewById(R.id.pop_text);
                TextView popPrice = inflate.findViewById(R.id.pop_price);
                ImageView popSub = inflate.findViewById(R.id.pop_sub);
                ImageView popAdd = inflate.findViewById(R.id.pop_add);
                TextView popNum = inflate.findViewById(R.id.pop_num);
                TextView popCancel = inflate.findViewById(R.id.pop_cancel);
                TextView popConfirm = inflate.findViewById(R.id.pop_confirm);
                popupWindow.setContentView(inflate);

                popNum.setText(num+"");
                Glide.with(GoodsActivity.this)
                        .load(figure)
                        .into(popImg);
                popText.setText(name);
                popPrice.setText(price);

                popAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        num++;
                        popNum.setText(num+"");
                    }
                });
                popSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        num--;
                        if (num<=0){
                            num=1;
                        }
                        popNum.setText(num+"");
                    }
                });
                popCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                popConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GoodsPresenter goodsPresenter = new GoodsPresenter(GoodsActivity.this);
                        goodsPresenter.ddOneProduct(id,num+"",name,figure,price);
                        popupWindow.dismiss();
                    }
                });
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
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,"" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddCart(AddOneProductBean productBean) {
        if (productBean.getCode().equals("200")){
            Toast.makeText(this, getString(R.string.add), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, getString(R.string.dda), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateNum(UpdateProductNumBean bean) {

    }

    @Override
    public void onUpDataError(String msg) {

    }


}
