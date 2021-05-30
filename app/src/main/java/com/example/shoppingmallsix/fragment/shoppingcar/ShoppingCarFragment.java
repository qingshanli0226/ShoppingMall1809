package com.example.shoppingmallsix.fragment.shoppingcar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.LogUtil;
import com.example.framework.manager.SoppingCartMemoryDataManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.business.CheckInventoryBean;
import com.example.net.bean.business.CheckOneInventoryBean;
import com.example.net.bean.business.ConfirmServerPayResultBean;
import com.example.net.bean.business.GetOrderInfoBean;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.business.RemoveManyProductBean;
import com.example.net.bean.business.RemoveOneProductBean;
import com.example.net.bean.business.SelectAllProductBean;
import com.example.net.bean.business.UpdateProductNumBean;
import com.example.net.bean.business.UpdateProductSelectedBean;
import com.example.net.bean.user.LoginBean;
import com.example.pay.order.GetOrderActivity;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.home.HomeFragment;
import com.example.shoppingmallsix.main.MainActivity;
import com.example.user.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 田伟泽&赵于赞
 * <p>
 * A simple {@link Fragment} subclass.
 */

public class ShoppingCarFragment extends BaseFragment<ShoppingPresenter> implements IShopping, SoppingCartMemoryDataManager.ISoppingDateChange {
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
    private List<GetShortcartProductsBean.ResultBean> resultBeans = new ArrayList<>();
    private List<GetShortcartProductsBean.ResultBean> checkInventoryBean = new ArrayList<>();
    private List<GetOrderInfoBean.ResultBean> order = new ArrayList<>();
    private List<ConfirmServerPayResultBean> confirmServerPayResultBeans = new ArrayList<>();
    private ShoppingCarAdapter shoppingCarAdapter;
    private CheckBox shopCheck;
    private float price = 0;
    private float fPrice = 0 ;
    private List<GetShortcartProductsBean.ResultBean> listDelete = new ArrayList<>();
    private ToolBar toolbar;
    private RecyclerView shopcarRv;
    private LinearLayout shoppingGuideLinear;
    private TextView shoppingGuideText;

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
            Toast.makeText(getActivity(), "请先登录账户", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
        shopCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpPresenter.getSelectAllProduct(shopCheck.isChecked(), false);
                shopCheck.setChecked(!shopCheck.isChecked());
            }
        });
        //支付所有按钮
        buyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <resultBeans.size() ; i++) {
                    if (resultBeans.get(i).isProductSelected()){
                        checkInventoryBean.add(resultBeans.get(i));
                    }
                }
                //查询所有库存
                httpPresenter.checkInventory(checkInventoryBean);
            }
        });
        //切换后删除数据
        deleteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteServeData();
            }
        });
        //指引页面
        shoppingGuideText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new HomeFragment()).commitAllowingStateLoss();
                MainActivity.mainCommon.setCurrentTab(0);
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
                    if (result.size()==0){
                        shoppingGuideLinear.setVisibility(View.VISIBLE);
                    }else {
                        shoppingGuideLinear.setVisibility(View.GONE);
                        resultBeans.addAll(result);
                        AllProductBeanAndProductSelect();
                        allPrice();
                        shoppingCarAdapter.notifyDataSetChanged();
                    }
                }
            } else {
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };


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
        shoppingGuideLinear = (LinearLayout) mBaseView.findViewById(R.id.shoppingGuideLinear);
        shoppingGuideText = (TextView) mBaseView.findViewById(R.id.shoppingGuideText);

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

        shoppingCarAdapter.setItemListener(new ShoppingCarAdapter.IItemChildClick() {
            @Override
            public void onItemChildClick(int position, View view) {
                GetShortcartProductsBean.ResultBean resultBean = resultBeans.get(position);
                switch (view.getId()) {
                    case R.id.shoppingTrolley_CheckBox:
                        httpPresenter.getUpProductSelect(resultBean.getProductId(), resultBean.getProductNum(), resultBean.getProductName(), resultBean.getUrl(), (String) resultBean.getProductPrice(), position);
                        break;
                    case R.id.shoppingTrolley_sub:
                        httpPresenter.updateProduceNum(resultBean.getProductId(), (Integer.parseInt(resultBean.getProductNum()) - 1) + "", resultBean.getProductName(), resultBean.getUrl(), "" + price, position, true);
                        break;
                    case R.id.shoppingTrolley_add:
                        httpPresenter.checkOneInventory(resultBeans.get(position).getProductId(), resultBeans.get(position).getProductNum(), position);
                        break;
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_car;
    }

    //全选回调
    @Override
    public void onSelectAllProductBean(SelectAllProductBean selectAllProductBean, boolean mBooleans) {
        if (selectAllProductBean.getCode().equals("200")) {
            //服务端更改成功
            if (mBooleans) {
                //点击单选 多选改变
            } else {
                //点击多选
                shopCheck.setChecked(!shopCheck.isChecked());
                if (shopCheck.isChecked()) {
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

    //修改单选的回调
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
    public void onOrderinfo(GetOrderInfoBean getOrderInfoBean) {
        if (getOrderInfoBean.getCode().equals("200")) {
            GetOrderInfoBean.ResultBean result = getOrderInfoBean.getResult();
            order.add(result);

            String orderInfo = getOrderInfoBean.getResult().getOrderInfo();
            String outTradeNo = getOrderInfoBean.getResult().getOutTradeNo();

            Intent intent = new Intent(getContext(), GetOrderActivity.class);
            intent.putExtra("orderInfo", orderInfo);
            intent.putExtra("outTradeNo", outTradeNo);
            intent.putExtra("key", "main");
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "" + getOrderInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConfiemserverpayresult(ConfirmServerPayResultBean confirmServerPayResultBean) {
        if (confirmServerPayResultBean.getCode().equals("200")) {
            confirmServerPayResultBeans.add(confirmServerPayResultBean);
        }

    }

    //删除多个的回调
    @Override
    public void onRemoveManyProductBean(RemoveManyProductBean removeManyProductBean) {
        if (removeManyProductBean.getCode().equals("200")) {
            //成功后 清除内存数据
            DeleteMemoryData();
        }
    }


    //删除一个物品的回调
    @Override
    public void onRemoveOneProductBean(RemoveOneProductBean removeOneProductBean, int position) {
        if (removeOneProductBean.getCode().equals("200")) {
            //服务端删除后删除缓存
            resultBeans.remove(position);
        }
        //判断购物车是否还有数据
        if (resultBeans.size()==0){
            shoppingGuideLinear.setVisibility(View.VISIBLE);
        }else {
            shoppingGuideLinear.setVisibility(View.GONE);
        }
        allPrice();
        //通知缓存改变
        SoppingCartMemoryDataManager.setResultBean(resultBeans);
    }

    //检查库存的回调
    @Override
    public void onCheckOneInventory(CheckOneInventoryBean checkOneInventoryBean, int position) {
        if (checkOneInventoryBean.getCode().equals("200")) {
            Toast.makeText(getActivity(), "检查库存有", Toast.LENGTH_SHORT).show();

            GetShortcartProductsBean.ResultBean resultBean = resultBeans.get(position);
            //修改
            httpPresenter.updateProduceNum(resultBean.getProductId(), (Integer.parseInt(resultBean.getProductNum()) + 1) + "", resultBean.getProductName(), resultBean.getUrl(), "" + price, position, false);
            SoppingCartMemoryDataManager.setResultBean(resultBeans);
        } else {
            Toast.makeText(getActivity(), "检查库存没有", Toast.LENGTH_SHORT).show();
        }
    }
    //查询所有库存 的回到
    @Override
    public void onCheckInventory(CheckInventoryBean checkInventoryBean,List<GetShortcartProductsBean.ResultBean> resultBeans) {
        if (checkInventoryBean.getCode().equals("200")){
            Intent intent = new Intent(getContext(), GetOrderActivity.class);
            ArrayList<GetShortcartProductsBean.ResultBean> objects = new ArrayList<>();
            for (int i = 0; i <resultBeans.size() ; i++) {
                objects.add(resultBeans.get(i));
            }
            intent.putExtra("price",price);
            intent.putExtra("list",objects);
            startActivity(intent);
        }else {
            String name = "";
            List<CheckInventoryBean.ResultBean> result = checkInventoryBean.getResult();
            if (result!=null){
                for (int i = 0; i <result.size() ; i++) {
                    if ( result.get(i).getProductName()!=null){
                        name=name+result.get(i).getProductName()+",";
                    }
                }
            }

            Toast.makeText(getActivity(), name+"这些商品缺货", Toast.LENGTH_SHORT).show();
        }
    }

    //修改更新的回调
    @Override
    public void onUpdateProductNum(UpdateProductNumBean updateProductNumBean, int position, boolean mBoolena) {
        if (updateProductNumBean.getCode().equals("200")) {
            //修改内存数据
            if (mBoolena) {
                //减少
                if ((Integer.parseInt(resultBeans.get(position).getProductNum()) > 1)) {
                    resultBeans.get(position).setProductNum((Integer.parseInt(resultBeans.get(position).getProductNum()) - 1) + "");
                } else {
                    httpPresenter.removeOneProductBean(resultBeans.get(position), position);
                }
            } else {
                //增加
                resultBeans.get(position).setProductNum((Integer.parseInt(resultBeans.get(position).getProductNum()) + 1) + "");
            }
            allPrice();
            SoppingCartMemoryDataManager.setResultBean(resultBeans);
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
            shopCheck.setChecked(true);
            httpPresenter.getSelectAllProduct(true, true);
        } else {
            shopCheck.setChecked(false);
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
                float v = Float.parseFloat(productPrice);
                String productNum = resultBeans.get(i).getProductNum();
                int parseInt = Integer.parseInt(productNum);
                fPrice = fPrice + (v * parseInt);
            }
        }
        shopMoney.setText("" + fPrice);
        price = fPrice;
        fPrice = 0;
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
    public void onDestroy() {
        super.onDestroy();
        SoppingCartMemoryDataManager.getInstance().unHoppingCartMemory(this);
    }

    @Override
    public void onSoppingDataChange(List<GetShortcartProductsBean.ResultBean> resultBeanList) {
        if (resultBeanList != null) {
            shoppingCarAdapter.notifyDataSetChanged();
        }
    }

}
