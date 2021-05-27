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
import com.shoppingmall.framework.manager.CacheManager;
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
    private static int ADD_OR_REMOVE = 0;

    private int nowPosition = 0;
    private ShopCarBean shopCarBean2;
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
        //加载列表
        shopCarAdapter = new ShopCarAdapter();
        //判断是否点击编辑
        checkcompileOnClick();
        httpPresenter.getShopCarData();


    }

    private void checkcompileOnClick() {
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
    }

    @Override
    public void getShopCarData(ShopCarBean shopCarBean) {
        CacheManager.getInstance().setShopCarBean(shopCarBean);

        shopCarBean2 = CacheManager.getInstance().getShopCarBean();
        //添加数据
        shopMallCarRv.setLayoutManager(new LinearLayoutManager(getContext()));
        shopMallCarRv.setAdapter(shopCarAdapter);
//        shopCarAdapter.notifyItemChanged(nowPosition);

        //上传数据
        shopCarAdapter.updateData(shopCarBean2.getResult());

        //子点击
        shopCarAdapter.setRecyclerChildItemClickListener(new ShopCarAdapter.IRecyclerChildItemClickListener() {
            @Override
            public void onShopCarAddNumItemClick(int position, View v) {
                httpPresenter.checkProductNum(shopCarBean2.getResult().get(position).getProductId()
                        ,shopCarBean2.getResult().get(position).getProductNum());
                nowPosition = position;
                ADD_OR_REMOVE = 1;
            }

            @Override
            public void onShopCarRemoveNumItemClick(int position, View v) {
                httpPresenter.checkProductNum(shopCarBean2.getResult().get(position).getProductId()
                        ,shopCarBean2.getResult().get(position).getProductNum());
                nowPosition = position;
                ADD_OR_REMOVE = 2;
            }

            @Override
            public void onIsSelectItemClick(int position, View view) {

            }
        });
    }

    //String id,int num,String name,String url,String price
    @Override
    public void checkProductNum(CheckProductBean checkProductBean) {
//        LogUtils.json(checkProductBean);
//        if (checkProductBean.getCode().equals("200")){
        if (ADD_OR_REMOVE==1){
            httpPresenter.updateProduct(shopCarBean2.getResult().get(nowPosition).getProductId(),
                    Integer.parseInt(shopCarBean2.getResult().get(nowPosition).getProductNum())+1,
                    shopCarBean2.getResult().get(nowPosition).getUrl(),
                    (String) shopCarBean2.getResult().get(nowPosition).getProductPrice());
        }else if(ADD_OR_REMOVE==2){
            httpPresenter.updateProduct(shopCarBean2.getResult().get(nowPosition).getProductId(),
                    Integer.parseInt(shopCarBean2.getResult().get(nowPosition).getProductNum())-1,
                    shopCarBean2.getResult().get(nowPosition).getUrl(),
                    (String) shopCarBean2.getResult().get(nowPosition).getProductPrice());
        }
//        }
    }

    @Override
    public void updateProduct(AddProductBean addProductBean) {
        LogUtils.json(addProductBean);
        if (addProductBean.getCode().equals("200")){
            Toast.makeText(getContext(), "数量改变成功", Toast.LENGTH_SHORT).show();
            httpPresenter.getShopCarData();
        }
    }
}