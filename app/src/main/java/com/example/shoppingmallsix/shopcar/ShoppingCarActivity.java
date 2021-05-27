package com.example.shoppingmallsix.shopcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.SoppingCartMemoryDataManager;
import com.example.net.bean.business.CheckOneInventoryBean;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.RemoveManyProductBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.business.UpdateProductNumBean;
import com.example.net.bean.business.UpdateProductSelectedBean;
import com.example.net.bean.user.LoginBean;
import com.example.pay.demo.PayDemoActivity;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.shoppingcar.IShopping;
import com.example.shoppingmallsix.fragment.shoppingcar.ShoppingCarAdapter;
import com.example.shoppingmallsix.fragment.shoppingcar.ShoppingPresenter;
import com.example.user.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarActivity extends BaseActivity<ShoppingPresenter> implements IShopping, SoppingCartMemoryDataManager.ISoppingDateChange {

    private String bianji = "编辑";
    private String wancheng = "完成";
    private TextView shopcarText;
    private androidx.recyclerview.widget.RecyclerView shopRv;
    private android.widget.CheckBox shopcarCheck;
    private TextView shopcarTotal;
    private TextView shopcarDoaller;
    private TextView shopcarMoney;
    private android.widget.Button buycarBt;
    private android.widget.Button deleteCarBt;
    private android.widget.Button shouCangCarBt;
    private List<GetShortcartProductsBean.ResultBean> resultBeans = new ArrayList<>();
    private List<GetOrderInfoBean.ResultBean> order = new ArrayList<>();
    private List<ConfirmServerPayResultBean> confirmServerPayResultBeans = new ArrayList<>();
    private ShoppingCarAdapter shoppingCarAdapter;
    private float price = 0;
    private List<GetShortcartProductsBean.ResultBean> listDelete = new ArrayList<>();

    @Override
    protected void initPresenter() {
        httpPresenter = new ShoppingPresenter(this);
    }

    @Override
    protected void initData() {
        SoppingCartMemoryDataManager.getInstance().registerHoppingCartMemory(this);

        LoginBean loginBean1 = CacheUserManager.getInstance().getLoginBean();
        if (loginBean1 != null) {
            handler.sendEmptyMessageDelayed(1, 1000);
        } else {
            Toast.makeText(ShoppingCarActivity.this, "请先登录账户", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ShoppingCarActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        shopcarCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpPresenter.getSelectAllProduct(shopcarCheck.isChecked(), false);
                shopcarCheck.setChecked(!shopcarCheck.isChecked());
            }
        });

        buycarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingCarActivity.this, PayDemoActivity.class);
                startActivity(intent);
            }
        });

        //切换后删除数据
        deleteCarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteServeData();
            }
        });
    }

    //子线程获取数据 实时刷新
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            GetShortcartProductsBean resultBean = SoppingCartMemoryDataManager.getResultBean();
            if (resultBean != null) {
                resultBeans.clear();
                List<GetShortcartProductsBean.ResultBean> result = resultBean.getResult();
                if (result != null) {
                    resultBeans.addAll(result);
                    AllProductBeanAndProductSelect();
                    shoppingCarAdapter.notifyDataSetChanged();
                }
            } else {
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    protected void initView() {
        shopcarText = findViewById(R.id.shopcarText);
        shopRv = findViewById(R.id.shopRv);
        shopcarCheck = findViewById(R.id.shopcarCheck);
        shopcarTotal = findViewById(R.id.shopcarTotal);
        shopcarDoaller = findViewById(R.id.shopcarDoaller);
        shopcarMoney = findViewById(R.id.shopcarMoney);
        buycarBt = findViewById(R.id.buycarBt);
        deleteCarBt = findViewById(R.id.deleteCarBt);
        shouCangCarBt = findViewById(R.id.shouCangCarBt);
        shoppingCarAdapter = new ShoppingCarAdapter(resultBeans);
        shopRv.setLayoutManager(new LinearLayoutManager(this));
        shopRv.setAdapter(shoppingCarAdapter);

        shopcarText.setText(bianji);
        shopcarText.setTag(false);
        shopcarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = (boolean) shopcarText.getTag();
                if (!flag) {
                    shopcarText.setText(wancheng);
                    shopcarText.setTag(true);
                    shopcarTotal.setVisibility(View.GONE);
                    shopcarDoaller.setVisibility(View.GONE);
                    shopcarMoney.setVisibility(View.GONE);
                    buycarBt.setVisibility(View.GONE);
                    deleteCarBt.setVisibility(View.VISIBLE);
                    shouCangCarBt.setVisibility(View.VISIBLE);
                } else {
                    shopcarText.setText(bianji);
                    shopcarText.setTag(false);
                    shopcarTotal.setVisibility(View.VISIBLE);
                    shopcarDoaller.setVisibility(View.VISIBLE);
                    shopcarMoney.setVisibility(View.VISIBLE);
                    buycarBt.setVisibility(View.VISIBLE);
                    deleteCarBt.setVisibility(View.GONE);
                    shouCangCarBt.setVisibility(View.GONE);
                }
            }
        });

        shoppingCarAdapter.setItemListener(new ShoppingCarAdapter.IItemChildClick() {
            @Override
            public void onItemChildClick(int position, View view) {
                switch (view.getId()) {
                    case R.id.shoppingTrolley_CheckBox:
                        GetShortcartProductsBean.ResultBean resultBean = resultBeans.get(position);
                        httpPresenter.getUpProductSelect(resultBean.getProductId(), resultBean.getProductNum(), resultBean.getProductName(), resultBean.getUrl(), (String) resultBean.getProductPrice(), position);
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_car;
    }

    @Override
    public void onOrderinfo(GetOrderInfoBean getOrderInfoBean) {
        if (getOrderInfoBean.getCode().equals("200")) {
            GetOrderInfoBean.ResultBean result = getOrderInfoBean.getResult();
            order.add(result);
        }
    }

    @Override
    public void onConfiemserverpayresult(ConfirmServerPayResultBean confirmServerPayResultBean) {
        if (confirmServerPayResultBean.getCode().equals("200")) {

            confirmServerPayResultBeans.add(confirmServerPayResultBean);
        }
    }

    @Override
    public void onSelectAllProductBean(SelectAllProductBean selectAllProductBean, boolean mBooleans) {
        if (selectAllProductBean.getCode().equals("200")) {
            //服务端更改成功
            if (mBooleans) {
                //点击单选 多选改变
            } else {
                //点击多选
                shopcarCheck.setChecked(!shopcarCheck.isChecked());
                if (shopcarCheck.isChecked()) {
                    for (int i = 0; i < resultBeans.size(); i++) {
                        resultBeans.get(i).setProductSelected(true);
                    }
                } else {
                    for (int i = 0; i < resultBeans.size(); i++) {
                        resultBeans.get(i).setProductSelected(false);
                    }
                }
                //通知个个页面数据刷新
                SoppingCartMemoryDataManager.setResultBean(resultBeans);
            }
            //刷新金额
            allPrice();
        }
    }

    @Override
    public void onUpdateProductSelect(UpdateProductSelectedBean updateProductSelectedBean, int position) {
        if (updateProductSelectedBean.getCode().equals("200")) {
            //服务端请求成功  刷新内存数据
            resultBeans.get(position).setProductSelected(!resultBeans.get(position).isProductSelected());
            //刷新多选
            AllProductBeanAndProductSelect();
            //通知内存数据刷新
            SoppingCartMemoryDataManager.setResultBean(resultBeans);
        }
    }

    @Override
    public void onRemoveManyProductBean(RemoveManyProductBean removeManyProductBean) {
        if (removeManyProductBean.getCode().equals("200")) {
            //成功后 清除内存数据
            DeleteMemoryData();
        }
    }

    @Override
    public void onCheckInventory(CheckOneInventoryBean bean, int position) {

    }

    @Override
    public void onUpdateProductNum(UpdateProductNumBean updateProductNumBean, int position, boolean mBoolean) {

    }

    @Override
    public void onSoppingDataChange(List<GetShortcartProductsBean.ResultBean> resultBeanList) {
        if (resultBeanList != null) {
            shoppingCarAdapter.notifyDataSetChanged();
        }
    }

    //判断刷新是否全选
    public void AllProductBeanAndProductSelect() {
        int index = 0;
        for (int i = 0; i < resultBeans.size(); i++) {
            if (resultBeans.get(i).isProductSelected()) {
                index++;
            }
        }
        if (index == resultBeans.size()) {
            shopcarCheck.setChecked(true);
            httpPresenter.getSelectAllProduct(true, true);
        } else {
            shopcarCheck.setChecked(false);
            httpPresenter.getSelectAllProduct(false, true);
        }
        //刷新金额
        allPrice();
    }

    //获取全部物品的金额
    public void allPrice() {
        for (int i = 0; i < resultBeans.size(); i++) {
            if (resultBeans.get(i).isProductSelected()) {
                String productPrice = (String) resultBeans.get(i).getProductPrice();
                LogUtils.e(productPrice);
                float v = Float.parseFloat(productPrice);
                LogUtils.e(v);
                String productNum = resultBeans.get(i).getProductNum();
                int parseInt = Integer.parseInt(productNum);
                price = price + (v * parseInt);
            }
        }
        shopcarMoney.setText("" + price);
        price = 0;
    }

    //删除服务端选中的商品
    public void DeleteServeData() {
        for (int i = 0; i < resultBeans.size(); i++) {
            if (resultBeans.get(i).isProductSelected()) {
                GetShortcartProductsBean.ResultBean resultBean = new GetShortcartProductsBean.ResultBean();
                resultBean.setProductNum(resultBeans.get(i).getProductNum());
                resultBean.setUrl(resultBeans.get(i).getUrl());
                resultBean.setProductPrice(resultBeans.get(i).getProductPrice());
                resultBean.setProductName(resultBeans.get(i).getProductName());
                resultBean.setProductId(resultBeans.get(i).getProductId());
                resultBean.setProductSelected(resultBeans.get(i).isProductSelected());
                listDelete.add(resultBean);
            }
        }
        httpPresenter.removeManyProduct(listDelete);
        //删除完进行刷新判断
    }

    //删除内存数据
    public void DeleteMemoryData() {
        for (int i = 0; i < resultBeans.size(); i++) {
            if (resultBeans.get(i).isProductSelected()) {
                resultBeans.remove(i);
                i--;
            }
        }
        allPrice();
        AllProductBeanAndProductSelect();
        SoppingCartMemoryDataManager.setResultBean(resultBeans);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoppingCartMemoryDataManager.getInstance().unHoppingCartMemory(this);
    }


}
