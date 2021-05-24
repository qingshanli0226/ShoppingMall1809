package com.example.electricityproject.shopp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.SpUtils;
import com.example.common.bean.LogBean;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;
import com.example.view.ToolBar;

import java.util.HashMap;
import java.util.Map;

public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView {


    private ToolBar toolbar;
    private Button goHome;
    private ImageView shoppingImg;
    private ShoppingAdapter shoppingAdapter;
    private RecyclerView buyCarRv;
    private TextView shoppingSelectAll;
    private TextView shoppingMoney;
    private Button goZfb;
    private Map<String,Boolean> map = new HashMap<>();

    @Override
    protected void initData() {
        httpPresenter.getShortProductsData();

        shoppingSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean selectAllBol = SpUtils.getSelectAllBol(getContext());
                if (selectAllBol){
                    SpUtils.putSelectAllBol(getContext(),false);
                }else {
                    SpUtils.putSelectAllBol(getContext(),true);
                }
                map.put("selected",selectAllBol);
                httpPresenter.postSelectAllProductData(map);
                shoppingAdapter.notifyDataSetChanged();

            }
        });


    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ShoppingPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        buyCarRv = (RecyclerView) findViewById(R.id.buy_car_rv);
        buyCarRv.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingSelectAll = (TextView) findViewById(R.id.shopping_selectAll);
        shoppingMoney = (TextView) findViewById(R.id.shopping_money);
        goZfb = (Button) findViewById(R.id.go_zfb);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping;
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
    public void getShortProductData(ShortcartProductBean shortcartProductBean) {
        if (shortcartProductBean.getCode().equals("200")) {
            Toast.makeText(getContext(), "获得服务端数据", Toast.LENGTH_SHORT).show();
            shoppingAdapter = new ShoppingAdapter(shortcartProductBean.getResult());
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void postSelectAllProductData(SelectAllProductBean selectAllProductBean) {
        Toast.makeText(getContext(), ""+selectAllProductBean.getCode(), Toast.LENGTH_SHORT).show();
        if (selectAllProductBean.getCode().equals("200")){
            shoppingAdapter.notifyDataSetChanged();
        }
    }
}