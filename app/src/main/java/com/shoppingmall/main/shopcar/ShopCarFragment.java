package com.shoppingmall.main.shopcar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.LogUtils;
import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.shopcar.adapter.ShopCarAdapter;
import com.shoppingmall.net.bean.AddProductBean;
import com.shoppingmall.net.bean.CheckProductBean;
import com.shoppingmall.net.bean.ProductBean;
import com.shoppingmall.net.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;


public class ShopCarFragment extends BaseFragment<ShopCarPresenter> implements IShopCarView {

    private ShopCarAdapter shopCarAdapter;

    private ViewPager viewPager;
    private RecyclerView shopMallCarRv;
    private CheckBox checkcompile;
    private LinearLayout cartLLfinish;
    private CheckBox checkdelete;
    private Button cartdelete;
    private Button cartcollect;
    private LinearLayout cartLLpayment;
    private CheckBox checkpayment;
    private TextView paymentprice;
    private Button payment;
    private boolean isOpenDel = false;
    private List<ShopCarBean> shopCarBeanList;

    private int nowPosition = 0;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_car;
    }

    @Override
    public void initView() {
        viewPager = getActivity().findViewById(R.id.mainVp);
        shopMallCarRv = (RecyclerView) mView.findViewById(R.id.shopMallCarRv);

        checkcompile = (CheckBox)  mView.findViewById(R.id.checkcompile);
        cartLLfinish = (LinearLayout)  mView.findViewById(R.id.cartLLfinish);
        checkdelete = (CheckBox)  mView.findViewById(R.id.checkdelete);
        cartdelete = (Button)  mView.findViewById(R.id.cartdelete);
        cartcollect = (Button)  mView.findViewById(R.id.cartcollect);
        cartLLpayment = (LinearLayout)  mView.findViewById(R.id.cartLLpayment);
        checkpayment = (CheckBox)  mView.findViewById(R.id.checkpayment);
        paymentprice = (TextView)  mView.findViewById(R.id.paymentprice);
        payment = (Button)  mView.findViewById(R.id.payment);
    }

    @Override
    public void initPresenter() {
        httpPresenter = new ShopCarPresenter(this);
    }

    @Override
    public void initData() {
        shopCarBeanList = new ArrayList<>();
        httpPresenter.getShopCarData();
    }

    @Override
    public void getShopCarData(ShopCarBean shopCarBean) {
        shopCarBeanList.add(shopCarBean);

        LogUtils.json(shopCarBean);
        shopCarAdapter = new ShopCarAdapter();
        shopMallCarRv.setLayoutManager(new LinearLayoutManager(getContext()));
        shopMallCarRv.setAdapter(shopCarAdapter);
        shopCarAdapter.updateData(shopCarBean.getResult());

        checkcompile.setOnClickListener(v->{
            if (isOpenDel){
                cartLLpayment.setVisibility(View.VISIBLE);
                cartLLfinish.setVisibility(View.GONE);
                isOpenDel = false;
            }else {
                cartLLpayment.setVisibility(View.GONE);
                cartLLfinish.setVisibility(View.VISIBLE);
                isOpenDel = true;
            }
        });

        shopCarAdapter.setRecyclerChildItemClickListener(new ShopCarAdapter.IRecyclerChildItemClickListener() {
            @Override
            public void onShopCarAddNumItemClick(int position, View v) {
                httpPresenter.checkProductNum(shopCarBean.getResult().get(position).getProductId()
                ,shopCarBean.getResult().get(position).getProductNum());
                nowPosition = position;
            }

            @Override
            public void onShopCarRemoveNumItemClick(int position, View v) {
                httpPresenter.checkProductNum(shopCarBean.getResult().get(position).getProductId()
                        ,shopCarBean.getResult().get(position).getProductNum());
                nowPosition = position;
            }

            @Override
            public void onIsSelectItemClick(int position, View view) {

            }
        });
    }
    //String id,int num,String name,String url,String price
    @Override
    public void checkProductNum(CheckProductBean checkProductBean) {
        LogUtils.json(checkProductBean);
        if (checkProductBean.getCode().equals("200")){
            String productId = shopCarBeanList.get(0).getResult().get(nowPosition).getProductId();
            Log.i("hqy", "checkProductNum: "+productId);
            String productNum = shopCarBeanList.get(0).getResult().get(nowPosition).getProductNum();
            String url = shopCarBeanList.get(0).getResult().get(nowPosition).getUrl();
            String productPrice = (String) shopCarBeanList.get(0).getResult().get(nowPosition).getProductPrice();
            LogUtils.json(checkProductBean);
            Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show();
            httpPresenter.updateProduct(productId,productNum , url, productPrice);
        }
    }

    @Override
    public void updateProduct(AddProductBean addProductBean) {

    }
}