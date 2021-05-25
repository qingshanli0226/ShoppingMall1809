package com.example.shoppingmallsix.fragment.shoppingcarfragment;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.user.LoginBean;
import com.example.shoppingmallsix.R;
import com.example.user.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCarFragment extends BaseFragment<ShoppingPresenter> implements IShopping, CacheUserManager.ILoginChange {

    private TextView shopText;
    private String bianji = "编辑";
    private String wancheng = "完成";
    private TextView shopTotal;
    private TextView shopDoaller;
    private TextView shopMoney;
    private Button buyBt;
    private Button deleteBt;
    private Button shouCangBt;
    private RecyclerView recyclerView;
    private int index;
    private List<GetShortcartProductsBean.ResultBean> resultBeans = new ArrayList<>();
    private ShoppingCarAdapter shoppingCarAdapter;
    private ToolBar toolbar;
    private RecyclerView shopcarRv;
    private CheckBox shopCheck;

    @Override
    protected void initPresenter() {
        httpPresenter = new ShoppingPresenter(this);
    }

    @Override
    protected void initData() {
        LoginBean loginBean1 = CacheUserManager.getInstance().getLoginBean();
        if (loginBean1 != null) {
            httpPresenter.getShoppingData();
        } else {
            Toast.makeText(getActivity(), "请先登录账户", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
        shopCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpPresenter.getSelectAllProduct(shopCheck.isChecked());
                shopCheck.setChecked(!shopCheck.isChecked());
            }
        });

    }

    @Override
    protected void initView() {
        shopText = mBaseView.findViewById(R.id.shopText);
        shopTotal = mBaseView.findViewById(R.id.shopTotal);
        shopDoaller = mBaseView.findViewById(R.id.shopDoaller);
        shopMoney = mBaseView.findViewById(R.id.shopMoney);
        buyBt = mBaseView.findViewById(R.id.buyBt);
        deleteBt = mBaseView.findViewById(R.id.deleteBt);
        shouCangBt = mBaseView.findViewById(R.id.shouCangBt);
        recyclerView = mBaseView.findViewById(R.id.shopcarRv);
        shopCheck = mBaseView.findViewById(R.id.shopCheck);
        shoppingCarAdapter = new ShoppingCarAdapter(resultBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(shoppingCarAdapter);

        shopText.setText(bianji);
        shopText.setTag(false);
        shopText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = (boolean) shopText.getTag();
                if (!flag) {
                    shopText.setText(wancheng);
                    shopText.setTag(true);
                    shopTotal.setVisibility(View.GONE);
                    shopDoaller.setVisibility(View.GONE);
                    shopMoney.setVisibility(View.GONE);
                    buyBt.setVisibility(View.GONE);
                    deleteBt.setVisibility(View.VISIBLE);
                    shouCangBt.setVisibility(View.VISIBLE);
                } else {
                    shopText.setText(bianji);
                    shopText.setTag(false);
                    shopTotal.setVisibility(View.VISIBLE);
                    shopDoaller.setVisibility(View.VISIBLE);
                    shopMoney.setVisibility(View.VISIBLE);
                    buyBt.setVisibility(View.VISIBLE);
                    deleteBt.setVisibility(View.GONE);
                    shouCangBt.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    public void onShopping(GetShortcartProductsBean shoppingCarBean) {

        if (shoppingCarBean.getCode().equals("200")) {
            List<GetShortcartProductsBean.ResultBean> result = shoppingCarBean.getResult();
            resultBeans.addAll(result);
            shoppingCarAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSelectAllProductBean(SelectAllProductBean selectAllProductBean) {
        if (selectAllProductBean.getCode().equals("200")){
            shopCheck.setChecked(!shopCheck.isChecked());
            if (shopCheck.isChecked()){
                for (int i = 0; i <resultBeans.size() ; i++) {
                    GetShortcartProductsBean.ResultBean resultBean = resultBeans.get(i);
                    resultBean.setProductSelected(true);
                }
            }else {
                for (int i = 0; i <resultBeans.size() ; i++) {
                    GetShortcartProductsBean.ResultBean resultBean = resultBeans.get(i);
                    resultBean.setProductSelected(false);
                }
            }

            shoppingCarAdapter.notifyDataSetChanged();
        }
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
    public void onLoginChange(LoginBean loginBean) {
        if (loginBean != null) {
            httpPresenter.getShoppingData();
        }
    }
}
