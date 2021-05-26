package com.example.electricityproject.shopp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.SpUtils;
import com.example.common.bean.LogBean;
import com.example.common.bean.RegBean;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.bean.UpdateProductNumBean;
import com.example.electricityproject.R;
import com.example.electricityproject.shopp.userinfo.BindUserInfoActivity;
import com.example.framework.BaseFragment;
import com.example.manager.BusinessBuyCarManger;
import com.example.manager.BusinessUserManager;
import com.example.view.ToolBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView {

    private ImageView all;
    private ToolBar toolbar;

    private ShoppingAdapter shoppingAdapter;
    private RecyclerView buyCarRv;
    private TextView shoppingSelectAll;
    private TextView shoppingMoney;
    private Button goZfb;
    private Map<String, Boolean> map = new HashMap<>();
    private boolean isSelect = false;
    private boolean isDelAll = false;
    private SelectAllProductBean list;
    private List<ShortcartProductBean.ResultBean> result;
    private double allPrice;
    private int num = 0;
    private boolean isShow = false;

    private int DelPosition = 0;
    private boolean isChange = false;
    private LinearLayout shoppingSta;
    private LinearLayout shoppingEnd;

    private ImageView delAll;
    private TextView delShop;
    private TextView collectShop;


    @Override
    protected void initData() {

        BusinessBuyCarManger.getInstance().Register(new BusinessBuyCarManger.iShopBeanChange() {
            @Override
            public void OnShopBeanChange(ShortcartProductBean shortcartProductBean) {
                if (shortcartProductBean!=null){
                    result = shortcartProductBean.getResult();
                    buyCarRv.setVisibility(View.VISIBLE);
                    shoppingAdapter.updateData(shortcartProductBean.getResult());
                    buyCarRv.setAdapter(shoppingAdapter);
                    shoppingAdapter.notifyDataSetChanged();
                }
            }
        });

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
                shoppingAdapter.notifyDataSetChanged();
            }
        });
        //全选
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
            }
        });
        //删除全选
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
            }
        });

        //适配器点击
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
                        if (num == result.size()) {
                            all.setImageResource(R.drawable.checkbox_selected);
                            delAll.setImageResource(R.drawable.checkbox_selected);
                        } else {
                            all.setImageResource(R.drawable.checkbox_unselected);
                            delAll.setImageResource(R.drawable.checkbox_unselected);
                        }
                        shoppingAdapter.notifyDataSetChanged();
                        count();
                        break;
                    //数量加
                    case R.id.image_add:
                        //判断当前库存
                        DelPosition = position;
                        httpPresenter.getCheckShopBean(result.get(position).getProductId(), result.get(position).getProductNum());
                        break;
                    //数量减
                    case R.id.image_sub:
                        int lose = Integer.parseInt(result.get(position).getProductNum());
                        if (lose <= 0) {
                            Toast.makeText(getContext(), "不能小于0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        result.get(position).setProductNum(lose - 1 + "");
                        httpPresenter.getUpdateProduct(result.get(position).getProductId(), result.get(position).getProductNum(), result.get(position).getProductName(), result.get(position).getUrl(), result.get(position).getProductPrice());
                        break;
                }
            }
        });
        isChange = false;
        //tob右边(编辑)点击弹出Pop
        toolbar.setToolbarListener(new ToolBar.IToolbarListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightImgClick() {
                if (!isChange) {
                    toolbar.setRightTvs("完成");
                    shoppingSta.setVisibility(View.GONE);
                    shoppingEnd.setVisibility(View.VISIBLE);
                    isChange = true;
                } else {
                    toolbar.setRightTvs("编辑");
                    shoppingEnd.setVisibility(View.GONE);
                    shoppingSta.setVisibility(View.VISIBLE);
                    isChange = false;
                }
            }

            @Override
            public void onRightTvClick() {

            }
        });
        delShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        LogBean isLog = BusinessUserManager.getInstance().getIsLog();
//        BusinessUserManager.getInstance().setLogList(isLog.getResult());
        //去结算
        goZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BusinessUserManager.getInstance().isBindAddress() && BusinessUserManager.getInstance().isBindTel()){
                    Log.i("xxx", "onClick: "+"qqq");
                }else {
                    Intent intent = new Intent(getContext(), BindUserInfoActivity.class);
                    startActivity(intent);
                    Log.i("xxx", "onClick: "+"sss");
                }
            }
        });
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
        shoppingSta = (LinearLayout) findViewById(R.id.shopping_sta);
        shoppingEnd = (LinearLayout) findViewById(R.id.shopping_end);
        delAll = (ImageView) findViewById(R.id.del_all);
        delShop = (TextView) findViewById(R.id.del_shop);
        collectShop = (TextView) findViewById(R.id.collect_shop);

        shoppingAdapter = new ShoppingAdapter();
        ShortcartProductBean shortcartProductBean = BusinessBuyCarManger.getInstance().getShortcartProductBean();
        if (shortcartProductBean!=null){
            result = shortcartProductBean.getResult();
            shoppingAdapter.updateData(shortcartProductBean.getResult());
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();
        }
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
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        loadingPage.showErrorView();
    }

    @Override
    public void getShortProductData(ShortcartProductBean shortcartProductBean) {

        if (shortcartProductBean.getCode().equals("200")) {
            result = shortcartProductBean.getResult();
            loadingPage.showSuccessView();
            BusinessBuyCarManger.getInstance().setShortcartProductBean(shortcartProductBean);
            buyCarRv.setVisibility(View.VISIBLE);
            shoppingAdapter.updateData(shortcartProductBean.getResult());
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "加载失败，正在重新加载", Toast.LENGTH_SHORT).show();
            httpPresenter.getShortProductsData();
            BusinessBuyCarManger.getInstance().setShortcartProductBean(shortcartProductBean);
            shoppingAdapter.updateData(shortcartProductBean.getResult());
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();
        }
    }

    //修改产品返回Bean
    @Override
    public void amendProductData(UpdateProductNumBean updateProductNumBean) {
        Log.i("zx", "amendProductData: " + updateProductNumBean.toString());
        if (updateProductNumBean.getCode().equals("200")) {
            shoppingAdapter.notifyDataSetChanged();
            count();
        }
    }

    @Override
    public void CheckProductData(RegBean regBean) {
        if (regBean.getMessage().equals("请求成功")) {
            int plus = Integer.parseInt(result.get(DelPosition).getProductNum());
            result.get(DelPosition).setProductNum(plus + 1 + "");
            httpPresenter.getUpdateProduct(result.get(DelPosition).getProductId(), result.get(DelPosition).getProductNum(), result.get(DelPosition).getProductName(), result.get(DelPosition).getUrl(), result.get(DelPosition).getProductPrice());
        } else {
            Toast.makeText(getContext(), "库存不够", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    //全选返回的Bean
    @Override
    public void postSelectAllProductData(SelectAllProductBean selectAllProductBean) {
        list = selectAllProductBean;
        //全选
        if (!isSelect) {
            if (list.getCode().equals("200")) {
                isSelect = true;
                all.setImageResource(R.drawable.checkbox_selected);
                delAll.setImageResource(R.drawable.checkbox_selected);
                for (ShortcartProductBean.ResultBean resultBean : result) {
                    resultBean.setAll(true);
                }
                count();
            }
        } else {
            if (list.getCode().equals("200")) {
                isSelect = false;
                all.setImageResource(R.drawable.checkbox_unselected);
                delAll.setImageResource(R.drawable.checkbox_unselected);
                for (ShortcartProductBean.ResultBean resultBean : result) {
                    resultBean.setAll(false);
                }
                count();
            }
        }
        shoppingAdapter.notifyDataSetChanged();
    }

    //计算价钱
    public void count() {
        allPrice = 0;
        for (ShortcartProductBean.ResultBean resultBean : result) {
            if (resultBean.isAll()) {
                if (resultBean != null) {
                    int a = Integer.parseInt(resultBean.getProductNum());
                    double b = Double.parseDouble(resultBean.getProductPrice());
                    allPrice += (double) (a * b);
                }
            }
        }
        shoppingMoney.setText("￥" + allPrice + "");
    }

    public void del() {
        if (isSelect) {
            httpPresenter.postSelectAllProductData(isSelect);
        } else {
            httpPresenter.postSelectAllProductData(isSelect);
        }
    }

}