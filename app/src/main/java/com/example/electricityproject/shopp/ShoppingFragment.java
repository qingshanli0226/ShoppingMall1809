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
import com.example.common.bean.CheckInventoryBean;
import com.example.common.bean.LogBean;
import com.example.common.bean.OrderBean;
import com.example.common.bean.OrderInfoBean;
import com.example.common.bean.RegBean;
import com.example.common.bean.RemoveManyProductBean;
import com.example.common.bean.RemoveOneProductBean;
import com.example.common.bean.RequestOrderInfo;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.SelectOrderBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.bean.UpdateProductNumBean;
import com.example.electricityproject.R;
import com.example.electricityproject.shopp.orderdetails.OrderDetailsActivity;
import com.example.electricityproject.shopp.userinfo.BindUserInfoActivity;
import com.example.framework.BaseFragment;
import com.example.manager.BusinessBuyCarManger;
import com.example.manager.BusinessUserManager;
import com.example.manager.ShopCacheManger;
import com.example.view.ToolBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView ,ShopCacheManger.iSelectShop{

    private ImageView all;
    private ToolBar toolbar;
    private ShoppingAdapter shoppingAdapter;
    private RecyclerView buyCarRv;
    private TextView shoppingSelectAll;
    private TextView shoppingMoney;
    private Button goZfb;
    private Map<String, Boolean> map = new HashMap<>();
    private boolean isSelect =false;
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
    private int delShopNum;
    private int delOne;
    private int selectPosition=-1;
    private ImageView isSelectImg;
    private String isAllStr="";
    private String isOneCheckStr="";

    private List<ShortcartProductBean.ResultBean> removeAllShopBean=new ArrayList<>();
    private List<ShortcartProductBean.ResultBean> notEnoughList=new ArrayList<>();
    private List<ShortcartProductBean.ResultBean> selectList = ShopCacheManger.getInstance().getSelectList();

    private List<RequestOrderInfo.BodyBean> bodyBeanList = new ArrayList<>();
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

        BusinessUserManager.getInstance().Register(new BusinessUserManager.IUserLoginChanged() {
            @Override
            public void onLoginChange(LogBean isLog) {
                if (isLog!=null){
                    ShopCacheManger.getInstance().requestShortProductData();
                }else {
                    Toast.makeText(getContext(), "当前未登录", Toast.LENGTH_SHORT).show();
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
        collectShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zx", "onClick: "+ShopCacheManger.getInstance().getSelectList());
            }
        });
        //全选
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllStr="all";
                del();
            }
        });
        //删除全选
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllStr="all";
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
                        //单选
                        selectPosition=position;
                        isSelectImg = (ImageView) view;
                        isShow = result.get(position).isAll();

                        httpPresenter.postSelectAllProductData(isShow);
                        isOneCheckStr="check";
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
                        if (lose <= 1) {
                            Toast.makeText(getContext(), "商品数量不能小于1", Toast.LENGTH_SHORT).show();
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
                deleteShopmall();
            }
        });

        //去结算
        goZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BusinessUserManager.getInstance().isBindAddress() && BusinessUserManager.getInstance().isBindTel()){
                    if (selectList.size() > 0){
                        List<OrderBean> orderBeanList = new ArrayList<>();
                        for (ShortcartProductBean.ResultBean resultBean : selectList) {
                            OrderBean orderBean = new OrderBean();
                            orderBean.setProductId(resultBean.getProductId());
                            orderBean.setProductName(resultBean.getProductName());
                            orderBean.setProductNum(resultBean.getProductNum());
                            orderBean.setUrl(resultBean.getUrl());
                            orderBeanList.add(orderBean);
                        }
                        if (orderBeanList!=null){
                            httpPresenter.checkInventory(orderBeanList);
                        }
                    }else {
                        Toast.makeText(getContext(), "未选中商品", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(getContext(), BindUserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //删除
    private void deleteShopmall() {
        delShopNum=0;
        delOne=-1;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).isAll()){
                delShopNum++;
                delOne=i;
                removeAllShopBean.add(result.get(i));
            }
        }
        if (delShopNum==1){
            httpPresenter.getRemoveOneShopBean(result.get(delOne).getProductId(),result.get(delOne).getProductName(),result.get(delOne).getProductNum(),result.get(delOne).getUrl(),result.get(delOne).getProductPrice());
        }
        if (removeAllShopBean.size()>1){
            Toast.makeText(getContext(), "大于1", Toast.LENGTH_SHORT).show();
            httpPresenter.getRemoveManyShopBean(removeAllShopBean);
        }
    }
    //删除一个
    @Override
    public void removeOneShop(RemoveOneProductBean removeOneProductBean) {
        if (removeOneProductBean.getCode().equals("200")){

            Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
            result.remove(delOne);
            ShopCacheManger.getInstance().requestShortProductData();
        }
        shoppingAdapter.notifyItemChanged(delOne);
    }
    //删除选中大于1个
    @Override
    public void removeManyShop(RemoveManyProductBean removeManyProductBean) {
        if (removeManyProductBean.getCode().equals("200")){
            Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
            result.remove(removeAllShopBean);
            ShopCacheManger.getInstance().requestShortProductData();
        }
        shoppingAdapter.notifyDataSetChanged();
    }

    //向服务器下订单
    @Override
    public void getOrderInfoData(OrderInfoBean orderInfoBean) {
        if(orderInfoBean.getCode().equals("200")){
            loadingPage.showSuccessView();

            String outTradeNo = orderInfoBean.getResult().getOutTradeNo();
            String orderInfo = orderInfoBean.getResult().getOrderInfo();

            List<SelectOrderBean> list = new ArrayList<>();
            for (ShortcartProductBean.ResultBean resultBean : selectList) {
                SelectOrderBean selectOrderBean = new SelectOrderBean(resultBean.getUrl(), resultBean.getProductName(), resultBean.getProductPrice(), resultBean.getProductNum());
                list.add(selectOrderBean);
            }
            ShopCacheManger.getInstance().setList(list);

            LogBean.ResultBean result1 = BusinessUserManager.getInstance().getIsLog().getResult();
            Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
            intent.putExtra("username",result1.getName());
            intent.putExtra("address", (String) result1.getAddress());
            intent.putExtra("phone", (String) result1.getPhone());
            intent.putExtra("outTradeNo", outTradeNo);
            intent.putExtra("orderInfo", orderInfo);
            if (ShopCacheManger.getInstance().getList()!=null){
                startActivity(intent);
//                deleteShopmall();
            }

        }else {
            Toast.makeText(getContext(), ""+orderInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //检查多个库存
    @Override
    public void checkInventory(CheckInventoryBean checkInventoryBean) {
        if(checkInventoryBean.getCode().equals("200")){
            loadingPage.showSuccessView();
            List<CheckInventoryBean.ResultBean> result = checkInventoryBean.getResult();

            boolean isEnough=true;

            for (int j = 0; j < result.size(); j++) {
                if(Integer.parseInt(result.get(j).getProductNum())<Integer.parseInt(selectList.get(j).getProductNum())){
                    isEnough=false;
                    notEnoughList.add(selectList.get(j));
                }
            }
            if(isEnough){
                //获取订单信息
                httpPresenter.getOrderInfo(selectList);
            }else {
                String notEnough="";
                for (ShortcartProductBean.ResultBean resultBean : notEnoughList) {
                    notEnough+=resultBean.getProductName()+"    ";
                }
                Toast.makeText(getContext(), notEnough+"库存不足", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getContext(), ""+checkInventoryBean.getMessage(), Toast.LENGTH_SHORT).show();
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
    //检查库存
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

    // 单选和全选点击请求数据返回的数据
    @Override
    public void postSelectAllProductData(SelectAllProductBean selectAllProductBean) {
        list = selectAllProductBean;

                if (list.getCode().equals("200")) {
                    //单选
                    if (!isOneCheckStr.equals("")) {
                        if (result.get(selectPosition).isAll()) {
                            isSelectImg.setImageResource(R.drawable.checkbox_unselected);
                            result.get(selectPosition).setAll(false);
                            count();
                            shoppingAdapter.notifyItemChanged(selectPosition);
                            ShopCacheManger.getInstance().setSelect(result.get(selectPosition));
                        } else {
                            isSelectImg.setImageResource(R.drawable.checkbox_selected);
                            result.get(selectPosition).setAll(true);
                            count();
                            shoppingAdapter.notifyItemChanged(selectPosition);
                            ShopCacheManger.getInstance().setSelect(result.get(selectPosition));
                        }

                        //反选

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
                    }
                    //全选
                    if (!isAllStr.equals("")) {
                        if (!isSelect) {
                            isSelect = true;
                            all.setImageResource(R.drawable.checkbox_selected);
                            delAll.setImageResource(R.drawable.checkbox_selected);
                            for (ShortcartProductBean.ResultBean bean : result) {
                                bean.setAll(true);
                            }
                            count();

                        } else {
                            isSelect = false;
                            all.setImageResource(R.drawable.checkbox_unselected);
                            delAll.setImageResource(R.drawable.checkbox_unselected);
                            for (ShortcartProductBean.ResultBean bean : result) {
                                bean.setAll(false);
                            }
                            count();

                        }
                        isAllStr = "";
                        shoppingAdapter.notifyDataSetChanged();
                        for (ShortcartProductBean.ResultBean bean : result) {
                            ShopCacheManger.getInstance().setSelect(bean);
                        }

                    }

                }

            }

    //计算价钱
    public void count () {
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


    public void del () {
        if (isSelect) {
            httpPresenter.postSelectAllProductData(isSelect);
        } else {
            httpPresenter.postSelectAllProductData(isSelect);
        }
    }


    @Override
    public void onSelectBean(List<ShortcartProductBean.ResultBean> selectShopList) {
        Log.i("zx", "onSelectBean: "+selectShopList.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusinessUserManager.getInstance().UnRegister(this::onLoginChange);
        BusinessBuyCarManger.getInstance().UnRegister(this::getShortProductData);
    }
}