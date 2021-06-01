package com.shoppingmall.main.shopcar;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.LogUtils;
import com.shoppingmall.R;
import com.shoppingmall.framework.manager.CacheShopManager;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.shopcar.adapter.ShopCarAdapter;
import com.shoppingmall.net.bean.ShopCarBean;
import com.shoppingmall.pay.order.OrderActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ShopCarFragment extends BaseFragment<ShopCarPresenter> implements CacheShopManager.ICartChange, IShopCarView {

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

    private ImageView itemImageView;
    private boolean isAll = false;
    private List<ShopCarBean.ResultBean> carts;
    private List<ShopCarBean.ResultBean> orderList = new ArrayList<>();
    private RelativeLayout NullCar;
    private TextView goHomeFragment;
    private LinearLayout notNullCar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_car;
    }

    @Override
    public void initView() {
        viewPager = getActivity().findViewById(R.id.mainVp);
        shopMallCarRv = (RecyclerView) mView.findViewById(R.id.shopMallCarRv);

        checkcompile = (CheckBox) mView.findViewById(R.id.checkcompile);
        cartLLfinish = (LinearLayout) mView.findViewById(R.id.cartLLfinish);
        checkdelete = (CheckBox) mView.findViewById(R.id.checkdelete);
        cartdelete = (Button) mView.findViewById(R.id.cartdelete);
        cartcollect = (Button) mView.findViewById(R.id.cartcollect);
        cartLLpayment = (LinearLayout) mView.findViewById(R.id.cartLLpayment);
        checkpayment = (CheckBox) mView.findViewById(R.id.checkpayment);
        paymentprice = (TextView) mView.findViewById(R.id.paymentprice);
        payment = (Button) mView.findViewById(R.id.payment);
        NullCar = (RelativeLayout) mView.findViewById(R.id.NullCar);
        goHomeFragment = (TextView) mView.findViewById(R.id.goHomeFragment);
        notNullCar = (LinearLayout) mView.findViewById(R.id.notNullCar);
    }

    @Override
    public void initPresenter() {
        httpPresenter = new ShopCarPresenter(this);
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        //判断是否点击编辑
        checkcompileOnClick();
        //注册
        CacheShopManager.getInstance().registerCart(this);
        carts = CacheShopManager.getInstance().getCarts();
        LogUtils.json(carts);
        //获取数据
        shopCarAdapter = new ShopCarAdapter();
        if (carts.size()!=0) {
            notNullCar.setVisibility(View.VISIBLE);
            NullCar.setVisibility(View.GONE);
            shopCarAdapter.updateData(carts);
        }else {
            notNullCar.setVisibility(View.GONE);
            NullCar.setVisibility(View.VISIBLE);
        }

        //数据
        shopMallCarRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopMallCarRv.setAdapter(shopCarAdapter);

        shopCarAdapter.setRecyclerChildItemClickListener(new ShopCarAdapter.IRecyclerChildItemClickListener() {
            @Override
            public void onShopCarAddNumItemClick(int position, View v) {
                ShopCarBean.ResultBean resultBean = shopCarAdapter.getData().get(position);
                ShopCarBean.ResultBean result = new ShopCarBean.ResultBean();
                //判断库存
                result.setProductId(resultBean.getProductId());
                result.setProductNum(Integer.parseInt(resultBean.getProductNum()) + 1 + "");
                result.setProductPrice(resultBean.getProductPrice());
                httpPresenter.upDateNum(position, result);
            }

            @Override
            public void onShopCarRemoveNumItemClick(int position, View v) {
                ShopCarBean.ResultBean resultBean = shopCarAdapter.getData().get(position);
                ShopCarBean.ResultBean result = new ShopCarBean.ResultBean();
                if (Integer.parseInt(resultBean.getProductNum()) > 0) {
                    result.setProductId(resultBean.getProductId());
                    result.setProductNum(Integer.parseInt(resultBean.getProductNum()) - 1 + "");
                    result.setProductPrice(resultBean.getProductPrice());
                    httpPresenter.upDateNum(position, result);
                }
            }

            @Override
            public void onIsSelectItemClick(int position, View view) {
                ShopCarBean.ResultBean resultBean = shopCarAdapter.getData().get(position);
                ShopCarBean.ResultBean result = new ShopCarBean.ResultBean();
                //修改
                result.setProductId(resultBean.getProductId());
                result.setProductNum(resultBean.getProductNum());
                result.setProductPrice(resultBean.getProductPrice());
                itemImageView = (ImageView) view;
                if (resultBean.isProductSelected()) {
                    result.setProductSelected(false);
                } else {
                    result.setProductSelected(true);
                }
                httpPresenter.updateProductSelect(position, result);
            }
        });
        //去主页
        goHomeFragment.setOnClickListener(v->{
            viewPager.setCurrentItem(0);
        });
        //全选
        checkpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAll) {
                    isAll = false;
                } else {
                    isAll = true;
                }
                httpPresenter.selectAll(isAll);
            }
        });

        //编辑全选
        checkdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //删除
        cartdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delCarProduct();
            }
        });
        isCheck();

        //支付
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < shopCarAdapter.getData().size(); i++) {
                    if (shopCarAdapter.getData().get(i).isProductSelected()) {
                        orderList.add(shopCarAdapter.getData().get(i));
                    }
                }
                Log.i("hqy", "onClick: "+orderList.toString());

                if (orderList.size()==0){
                    Toast.makeText(getContext(), "请至少选中一个", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getContext(), OrderActivity.class);
                    intent.putExtra("orderList", (Serializable) orderList);
                    intent.putExtra("orderPrice",paymentprice.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }

    private void checkcompileOnClick() {
        checkcompile.setOnClickListener(v -> {
            if (isOpenDel) {
                cartLLpayment.setVisibility(View.VISIBLE);
                cartLLfinish.setVisibility(View.GONE);
                isOpenDel = false;
            } else {
                cartLLpayment.setVisibility(View.GONE);
                cartLLfinish.setVisibility(View.VISIBLE);
                isOpenDel = true;
            }
        });
    }

    @Override
    public void onShowCart(List<ShopCarBean.ResultBean> carts) {
        this.carts = carts;
        shopCarAdapter.updateData(carts);
        EventBus.getDefault().post("ShopCarNum");
        Log.i("hqy", "onShowCart: ");
    }

    //添加
    @Override
    public void onAddCart(int position) {
        if (position > shopCarAdapter.getData().size()) {
            shopCarAdapter.getData().add(CacheShopManager.getInstance().getCarts().get(position - 1));
            shopCarAdapter.notifyItemChanged(position - 1);
        } else {
            shopCarAdapter.getData().get(position).setProductNum(CacheShopManager.getInstance().getCarts().get(position).getProductNum());
            shopCarAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onCheck(int position, boolean isCheck) {
        shopCarAdapter.notifyItemChanged(position);
        //反选
        isCheck();

        //更改价格
        priceCount();
    }

    private void isCheck() {
        int count = 0;
        for (ShopCarBean.ResultBean datum : shopCarAdapter.getData()) {
            if (datum.isProductSelected()) {
                count++;
            }
        }
        if (count == shopCarAdapter.getData().size()) {
            checkpayment.setChecked(true);
            isAll = true;
        } else {
            checkpayment.setChecked(false);
            isAll = false;
        }
    }

    //更改价格
    private void priceCount() {
        int priceCount = 0;
        for (ShopCarBean.ResultBean datum : shopCarAdapter.getData()) {
            if (datum.isProductSelected()) {
                priceCount += Integer.parseInt(datum.getProductNum()) * Float.parseFloat(datum.getProductPrice() + "");
            }
        }
        paymentprice.setText(priceCount + "");
    }

    @Override
    public void onCheckAll(boolean isChcekAll) {
        this.isAll = isChcekAll;
        shopCarAdapter.notifyDataSetChanged();
        priceCount();
    }

    //
    @Override
    public void onNum(int position) {
        shopCarAdapter.notifyItemChanged(position);
        priceCount();
    }
    //删除
    public void delCarProduct(){
        int position = -1;
        ArrayList<ShopCarBean.ResultBean> resultBeans = new ArrayList<>();
        for (int i = 0; i < shopCarAdapter.getData().size(); i++) {
            if (shopCarAdapter.getData().get(i).isProductSelected()) {
                resultBeans.add(shopCarAdapter.getData().get(i));
                position = i;
            }
        }
        if (resultBeans.size() == 1) {
            //选中一个
            httpPresenter.removeOneProduct(position, resultBeans.get(0));
        } else if (resultBeans.size() > 1) {
            //选中多个
            httpPresenter.removeMany(resultBeans);
        } else {
            Toast.makeText(getActivity(), "没有选中", Toast.LENGTH_SHORT).show();
        }
    }

    //删除一个
    @Override
    public void removeProduct(int position) {
        shopCarAdapter.getData().remove(position);
        shopCarAdapter.notifyItemRemoved(position);
        if (shopCarAdapter.getData().size()!=0) {
            notNullCar.setVisibility(View.VISIBLE);
            NullCar.setVisibility(View.GONE);
            shopCarAdapter.updateData(carts);
        }else {
            notNullCar.setVisibility(View.GONE);
            NullCar.setVisibility(View.VISIBLE);
        }
//        EventBus.getDefault().post("ShopCarNum");

    }

    //删除多个
    @Override
    public void removeMany(List<ShopCarBean.ResultBean> resultBeans) {
        for (int i = shopCarAdapter.getData().size() - 1; i >= 0; i--) {
            ShopCarBean.ResultBean bean = shopCarAdapter.getData().get(i);
            for (int i1 = resultBeans.size() - 1; i1 >= 0; i1--) {
                if (bean.getProductId().equals(resultBeans.get(i1).getProductId())) {
                    shopCarAdapter.getData().remove(i);
                    resultBeans.remove(i1);
                }
            }
        }
        shopCarAdapter.notifyDataSetChanged();
        if (shopCarAdapter.getData().size()!=0) {
            notNullCar.setVisibility(View.VISIBLE);
            NullCar.setVisibility(View.GONE);
            shopCarAdapter.updateData(carts);
        }else {
            notNullCar.setVisibility(View.GONE);
            NullCar.setVisibility(View.VISIBLE);
        }
//        EventBus.getDefault().post("ShopCarNum");

    }

    @Subscribe
    public void delCarProductEvenBus(String msg){
        if (msg.equals("delCar")){
            delCarProduct();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    public void destroy() {
        CacheShopManager.getInstance().unRegisterCart(this);
        CacheShopManager.getInstance().destroy();
    }

}