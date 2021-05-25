package com.example.electricityproject.shopp;

import android.util.Log;
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
import com.example.common.bean.UpdateProductNumBean;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;
import com.example.manager.BusinessBuyCarManger;
import com.example.view.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private Map<String, Boolean> map = new HashMap<>();
    private boolean isSelect = false;
    private SelectAllProductBean list;
    private List<ShortcartProductBean.ResultBean> result;
    private double allPrice;
    private int num = 0;
    private boolean isShow = false;

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);


        shoppingSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean selectAllBol = SpUtils.getSelectAllBol(getContext());
                if (selectAllBol) {
                    SpUtils.putSelectAllBol(getContext(), false);
                } else {
                    SpUtils.putSelectAllBol(getContext(), true);
                }
                map.put("selected", selectAllBol);
                //httpPresenter.postSelectAllProductData(map);
                shoppingAdapter.notifyDataSetChanged();

            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelect) {
                    httpPresenter.postSelectAllProductData(isSelect);

                } else {

                    httpPresenter.postSelectAllProductData(isSelect);

                }
            }
        });

        shoppingAdapter.setChildItemClickListener(new ShoppingAdapter.iChildItemClickListener() {
            @Override
            public void OnChildItemListener(View view, int position) {
                num = 0;
                switch (view.getId()) {
                    case R.id.is_select:
                        ImageView img = (ImageView) view;
                        isShow = result.get(position).isAll();
                        if (isShow) {
                            img.setImageResource(R.drawable.checkbox_unselected);
                            result.get(position).setAll(false);
                        } else {
                            img.setImageResource(R.drawable.checkbox_selected);
                            result.get(position).setAll(true);

                        }

                        for (ShortcartProductBean.ResultBean resultBean : result) {
                            if (resultBean.isAll()) {
                                num++;
                            }
                        }
                        Log.i("aa", "OnChildItemListener: num=" + num);
                        Log.i("aa", "OnChildItemListener: size=" + result.size());
                        if (num == result.size()) {
                            all.setImageResource(R.drawable.checkbox_selected);
                        } else {
                            all.setImageResource(R.drawable.checkbox_unselected);
                        }
                        count();
                        shoppingAdapter.notifyDataSetChanged();
                        break;
                    case R.id.image_add:
                        Toast.makeText(getContext(), "111", Toast.LENGTH_SHORT).show();
                        int plus = Integer.parseInt(result.get(position).getProductNum());
                        result.get(position).setProductNum(plus + 1 + "");
                        httpPresenter.getUpdateProduct(result.get(position).getProductId(), result.get(position).getProductNum(), result.get(position).getProductName(), result.get(position).getUrl(), result.get(position).getProductPrice());
                        shoppingAdapter.notifyDataSetChanged();
                        count();

                        break;
                    case R.id.image_sub:
                        Toast.makeText(getContext(), "111", Toast.LENGTH_SHORT).show();
                        int lose = Integer.parseInt(result.get(position).getProductNum());
                        if (lose<=0){
                            Toast.makeText(getContext(), "不能小于0", Toast.LENGTH_SHORT).show();
                        }
                        result.get(position).setProductNum(lose - 1 + "");
                        httpPresenter.getUpdateProduct(result.get(position).getProductId(), result.get(position).getProductNum(), result.get(position).getProductName(), result.get(position).getUrl(), result.get(position).getProductPrice());
                        shoppingAdapter.notifyDataSetChanged();
                        count();
                        break;
                }
            }
        });


    }

    @Subscribe
    public void getEvent(String eve) {
        if (eve.equals("login_success")) {
            httpPresenter.getShortProductsData();
        }
    }

    @Subscribe
    public void getBuyCarData(String eve) {
        if (eve.equals("request_buyCar")) {
            Toast.makeText(getContext(), "接收到广播", Toast.LENGTH_SHORT).show();
            ShortcartProductBean shortProductBean = BusinessBuyCarManger.getInstance().getShortcartProductBean();

            if (shortProductBean != null) {

                result = shortProductBean.getResult();
                shoppingAdapter.updateData(shortProductBean.getResult());
                buyCarRv.setAdapter(shoppingAdapter);
                shoppingAdapter.notifyDataSetChanged();

            } else {
                Toast.makeText(getContext(), "欢迎页面没有加载数据", Toast.LENGTH_SHORT).show();
            }
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

        shoppingAdapter = new ShoppingAdapter();
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
        Log.i("zx", "showError: " + error);
    }

    @Override
    public void getShortProductData(ShortcartProductBean shortcartProductBean) {

        result = shortcartProductBean.getResult();
        if (shortcartProductBean.getCode().equals("200")) {
            BusinessBuyCarManger.getInstance().setShortcartProductBean(shortcartProductBean);

            shoppingAdapter.updateData(shortcartProductBean.getResult());
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public void amendProductData(UpdateProductNumBean updateProductNumBean) {
        Log.i("zx", "amendProductData: " + updateProductNumBean.toString());
    }

    @Override
    public void postSelectAllProductData(SelectAllProductBean selectAllProductBean) {
        list = selectAllProductBean;

        if (!isSelect) {
            if (list.getCode().equals("200")) {
                isSelect = true;
                all.setImageResource(R.drawable.checkbox_selected);
                for (ShortcartProductBean.ResultBean resultBean : result) {
                    resultBean.setAll(true);
                }
                count();
            }
        } else {
            all.setImageResource(R.drawable.checkbox_unselected);
            if (list.getCode().equals("200")) {
                isSelect = false;
                for (ShortcartProductBean.ResultBean resultBean : result) {
                    resultBean.setAll(false);
                }
                count();
            }
        }
        shoppingAdapter.notifyDataSetChanged();


    }

    public void count() {
        allPrice = 0;
        for (ShortcartProductBean.ResultBean resultBean : result) {
            if (resultBean.isAll()) {
                if (resultBean != null) {
                    Log.i("zx", "num: " + resultBean.getProductNum() + "price:" + resultBean.getProductPrice());
                    int num = Integer.parseInt(resultBean.getProductNum());
                    double price = Double.parseDouble(resultBean.getProductPrice());

                    allPrice += (double) (num * price);
                }

            }
        }
        shoppingMoney.setText("￥" + allPrice + "");

    }
}