package com.example.electricityproject.shopp;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.SpUtils;
import com.example.common.bean.LogBean;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;
import com.example.manager.BusinessBuyCarManger;
import com.example.view.ToolBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView {

    private ImageView all;
    private ToolBar toolbar;
    private Button goHome;
    private ImageView shoppingImg;
    private ShoppingAdapter shoppingAdapter;
    private RecyclerView buyCarRv;
    private TextView shoppingSelectAll;
    private TextView shoppingMoney;
    private Button goZfb;
    private Map<String,Boolean> map = new HashMap<>();
    private boolean isSelect =false;
    private SelectAllProductBean list;
    private List<ShortcartProductBean.ResultBean> result;
    private double allPrice;

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
                //httpPresenter.postSelectAllProductData(map);
                shoppingAdapter.notifyDataSetChanged();

            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelect){
                    httpPresenter.postSelectAllProductData(isSelect);

                }else {
                    httpPresenter.postSelectAllProductData(isSelect);

                }
            }
        });
        ShortcartProductBean shortProductBean = BusinessBuyCarManger.getBusinessBuyCarManger().getShortProductBean();

        if (shortProductBean!=null){
            shoppingAdapter = new ShoppingAdapter();
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();
        }


    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ShoppingPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = mView.findViewById(R.id.toolbar);
        buyCarRv = mView.findViewById(R.id.buy_car_rv);
        buyCarRv.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingSelectAll = mView.findViewById(R.id.shopping_selectAll);
        shoppingMoney = mView.findViewById(R.id.shopping_money);
        goZfb = mView.findViewById(R.id.go_zfb);
        all = mView.findViewById(R.id.all);
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
        Log.i("zx", "showError: "+error);
    }

    @Override
    public void getShortProductData(ShortcartProductBean shortcartProductBean) {

        result = shortcartProductBean.getResult();
        if (shortcartProductBean.getCode().equals("200")) {
            shoppingAdapter = new ShoppingAdapter();
            shoppingAdapter.updateData(shortcartProductBean.getResult());

            BusinessBuyCarManger.getBusinessBuyCarManger().setShortProductBean(shortcartProductBean);
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void postSelectAllProductData(SelectAllProductBean selectAllProductBean) {
        list=selectAllProductBean;

        if (!isSelect) {
            if (list.getCode().equals("200")) {
                isSelect = true;
                all.setImageResource(R.drawable.checkbox_selected);
                for (ShortcartProductBean.ResultBean resultBean : result) {
                    resultBean.setAll(true);
                }
                count();
            }
        }else {
            all.setImageResource(R.drawable.checkbox_unselected);
            if (list.getCode().equals("200")){
                isSelect=false;
                for (ShortcartProductBean.ResultBean resultBean : result) {
                    resultBean.setAll(false);
                }
                count();
            }
        }
        shoppingAdapter.notifyDataSetChanged();



    }
    public void count(){
        allPrice=0;
        for (ShortcartProductBean.ResultBean resultBean : result) {
            if (resultBean.isAll()){
                Log.i("zx", "num: "+resultBean.getProductNum()+"price:"+resultBean.getProductPrice());
                int num = Integer.parseInt(resultBean.getProductNum());
                double price = Double.parseDouble(resultBean.getProductPrice());

                allPrice+= (double) (num*price);
            }
        }
        shoppingMoney.setText("￥"+allPrice+"");

    }
}