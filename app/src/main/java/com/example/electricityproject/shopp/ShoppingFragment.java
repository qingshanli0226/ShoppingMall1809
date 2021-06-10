package com.example.electricityproject.shopp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.LogUtils;
import com.example.common.bean.CheckInventoryBean;
import com.example.common.bean.LogBean;
import com.example.common.bean.OrderBean;
import com.example.common.bean.OrderInfoBean;
import com.example.common.bean.RegBean;
import com.example.common.bean.RemoveManyProductBean;
import com.example.common.bean.RemoveOneProductBean;
import com.example.common.bean.SelectAllProductBean;
import com.example.common.bean.ShortcartProductBean;
import com.example.common.bean.UpdateProductNumBean;
import com.example.electricityproject.R;
import com.example.electricityproject.shopp.orderdetails.OrderDetailsActivity;
import com.example.electricityproject.shopp.userinfo.UserInfoActivity;
import com.example.framework.BaseFragment;
import com.example.manager.AllSelectManager;
import com.example.manager.BusinessUserManager;
import com.example.manager.ShopCacheManger;
import com.example.view.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView ,ShopCacheManger.iShopBeanChangeListener{

    private ImageView all;
    private ToolBar toolbar;
    private ShoppingAdapter shoppingAdapter;
    private RecyclerView buyCarRv;
    private TextView shoppingMoney;
    private Button goZfb;
    private Map<String, Boolean> map = new HashMap<>();
    private List<ShortcartProductBean.ResultBean> result;
    private double allPrice;
    private int num = 0;
    private boolean isShow = false;
    private int AddPosition = -1;
    private int SubPosition = -1;
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
    private String a="";
    private List<ShortcartProductBean.ResultBean> removeAllShopBean=new ArrayList<>();
    private List<ShortcartProductBean.ResultBean> notEnoughList=new ArrayList<>();
    private List<ShortcartProductBean.ResultBean> selectList = ShopCacheManger.getInstance().getSelectList();

    @Override
    protected void initData() {

        //检测购物车商品是否发生改变,如果改变了刷新购物车页面
        if (ShopCacheManger.getInstance().getShortBeanList()!=null){
            result = ShopCacheManger.getInstance().getShortBeanList();
            buyCarRv.setVisibility(View.VISIBLE);
            shoppingAdapter.updateData(result);
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();
        }

        //全选
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllSelect();
            }
        });

        //编辑全选
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllSelect();
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
                        httpPresenter.selectOntProductData(isShow);
                        break;
                    //数量加
                    case R.id.image_add:
                        //判断当前库存
                        AddPosition = position;
                        a="add";
                        httpPresenter.getCheckShopBean(result.get(position).getProductId(), result.get(position).getProductNum());
                        break;
                    //数量减
                    case R.id.image_sub:
                        int lose = Integer.parseInt(result.get(position).getProductNum());
                        if (lose <= 1) {
                            Toast.makeText(getContext(), getResources().getString(R.string.shop_num), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        SubPosition=position;
                        a="sub";
                        httpPresenter.getUpdateProduct(result.get(position).getProductId(), Integer.parseInt(result.get(position).getProductNum())-1+"", result.get(position).getProductName(), result.get(position).getUrl(), result.get(position).getProductPrice());
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
                    toolbar.setRightTvs(getResources().getString(R.string.shop_success));
                    shoppingSta.setVisibility(View.GONE);
                    shoppingEnd.setVisibility(View.VISIBLE);
                    isChange = true;
                } else {
                    toolbar.setRightTvs(getResources().getString(R.string.shop_bianji));
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


                LogUtils.i(BusinessUserManager.getInstance().isBindAddress()+"");
                LogUtils.i(BusinessUserManager.getInstance().isBindTel()+"");
                LogUtils.i(ShopCacheManger.getInstance().getSelectList().toString());
                LogUtils.i(ShopCacheManger.getInstance().getShortBeanList().toString()+"");
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
                        Toast.makeText(getContext(), getResources().getString(R.string.shop_unselected_goods), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(getContext(), UserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //删除
    private void deleteShopmall() {
        delShopNum=0;
        delOne=-1;
        List<ShortcartProductBean.ResultBean> select = ShopCacheManger.getInstance().getSelectList();
        for (int i = select.size() - 1; i >= 0; i--) {
            if (select.get(i).isAll()){
                delShopNum++;
                delOne=i;
                removeAllShopBean.add(select.get(i));
            }
        }
        //删除一个
        if (delShopNum==1){
            httpPresenter.getRemoveOneShopBean(select.get(delOne).getProductId(),select.get(delOne).getProductName(),select.get(delOne).getProductNum(),select.get(delOne).getUrl(),select.get(delOne).getProductPrice());
        }
        //删除多个
        if (removeAllShopBean.size()>0){
            httpPresenter.getRemoveManyShopBean(removeAllShopBean);
        }
        //刷新适配器
        shoppingAdapter.notifyDataSetChanged();
    }
    //删除一个 返回值
    @Override
    public void removeOneShop(RemoveOneProductBean removeOneProductBean) {
        if (removeOneProductBean.getCode().equals("200")){
            //从缓存数据源中删除然后调用接口刷新
            ShopCacheManger.getInstance().ShopDelOne(result.get(delOne));
        }
    }
    //删除选中大于1个 返回值
    @Override
    public void removeManyShop(RemoveManyProductBean removeManyProductBean) {
        if (removeManyProductBean.getCode().equals("200")){
            for (ShortcartProductBean.ResultBean bean : removeAllShopBean) {
                ShopCacheManger.getInstance().ShopDelOne(bean);
            }
        }
    }

    //向服务器下订单
    @Override
    public void getOrderInfoData(OrderInfoBean orderInfoBean) {
        if(orderInfoBean.getCode().equals("200")){
            loadingPage.showSuccessView();
            String outTradeNo = orderInfoBean.getResult().getOutTradeNo();
            String orderInfo = orderInfoBean.getResult().getOrderInfo();
            LogBean.ResultBean result1 = BusinessUserManager.getInstance().getIsLog().getResult();
            Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
            intent.putExtra("username",result1.getName());
            intent.putExtra("address", (String) result1.getAddress());
            intent.putExtra("phone", (String) result1.getPhone());
            intent.putExtra("outTradeNo", outTradeNo);
            intent.putExtra("orderInfo", orderInfo);
            startActivity(intent);
        }else {
            Toast.makeText(getContext(), ""+orderInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //检查多个库存
    @Override
    public void checkInventory(CheckInventoryBean checkInventoryBean) {
        if(checkInventoryBean.getCode().equals("200")){
            loadingPage.showSuccessView();
            List<CheckInventoryBean.ResultBean> checkInventoryBeanResul = checkInventoryBean.getResult();
            boolean isEnough=true;
            for (int j = 0; j < checkInventoryBeanResul.size(); j++) {
                if(Integer.parseInt(checkInventoryBeanResul.get(j).getProductNum())<Integer.parseInt(selectList.get(j).getProductNum())){
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
                    notEnough+=resultBean.getProductName()+"  ";
                }
                Toast.makeText(getContext(), notEnough+getResources().getString(R.string.shop_product_not), Toast.LENGTH_SHORT).show();
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
        shoppingMoney = mView.findViewById(R.id.shopping_money);
        goZfb = mView.findViewById(R.id.go_zfb);
        all = mView.findViewById(R.id.all);
        shoppingAdapter = new ShoppingAdapter();
        shoppingSta = (LinearLayout) findViewById(R.id.shopping_sta);
        shoppingEnd = (LinearLayout) findViewById(R.id.shopping_end);
        delAll = (ImageView) findViewById(R.id.del_all);
        delShop = (TextView) findViewById(R.id.del_shop);
        collectShop = (TextView) findViewById(R.id.collect_shop);
        //加载的时候对页面进行操作
        shoppingAdapter = new ShoppingAdapter();
        result = ShopCacheManger.getInstance().getShortBeanList();
        if (result!=null){
            shoppingAdapter.updateData(result);
            buyCarRv.setAdapter(shoppingAdapter);
            shoppingAdapter.notifyDataSetChanged();
        }
        if (AllSelectManager.getInstance().isSelect()){
            all.setImageResource(R.drawable.checkbox_selected);
            delAll.setImageResource(R.drawable.checkbox_selected);
        }else {
            all.setImageResource(R.drawable.checkbox_unselected);
            delAll.setImageResource(R.drawable.checkbox_unselected);
        }
        EventBus.getDefault().register(this);
        ShopCacheManger.getInstance().registerShopBeanChange(this);

    }

    //支付成功或者支付失败后发送eventBus，来把选中的数据删除
    @Subscribe
    public void eventDel(String del){
        if (del.equals("del")) {
            deleteShopmall();
            if (AllSelectManager.getInstance().isSelect()){
                AllSelect();
            }
        }else if (del.equals("outLog")){
            Toast.makeText(getContext(), getResources().getString(R.string.shop_exit), Toast.LENGTH_SHORT).show();
            ShopCacheManger.getInstance().setShortBeanList(null);
            ShopCacheManger.getInstance().requestShortProductData();
            result=ShopCacheManger.getInstance().getShortBeanList();
            shoppingAdapter.updateData(result);
            shoppingAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void onLoginChange(LogBean isLog) {
        if (isLog!=null){
            ShopCacheManger.getInstance().requestShortProductData();
        }
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
            buyCarRv.setVisibility(View.VISIBLE);
            shoppingAdapter.updateData(shortcartProductBean.getResult());
            buyCarRv.setAdapter(shoppingAdapter);
            ShopCacheManger.getInstance().setShortBeanList(shortcartProductBean.getResult());
            shoppingAdapter.notifyDataSetChanged();
        }
    }

    //修改产品返回Bean
    @Override
    public void amendProductData(UpdateProductNumBean updateProductNumBean) {

        if (updateProductNumBean.getCode().equals("200")) {
            if (!a.equals("")){
                if (a.equals("add")){
                    ShopCacheManger.getInstance().addShopNum(result.get(AddPosition));
                }else if (a.equals("sub")){
                    ShopCacheManger.getInstance().subShopNum(result.get(SubPosition));
                }
            }
        }
    }
    //检查库存
    @Override
    public void CheckProductData(RegBean regBean) {
        if (regBean.getMessage().equals(getResources().getString(R.string.shop_request_success))) {
            httpPresenter.getUpdateProduct(result.get(AddPosition).getProductId(), result.get(AddPosition).getProductNum()+1+"", result.get(AddPosition).getProductName(), result.get(AddPosition).getUrl(), result.get(AddPosition).getProductPrice());
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.shop_product_not), Toast.LENGTH_SHORT).show();
            return;
        }
    }

    // 全选点击请求数据返回的数据
    @Override
    public void postSelectAllProductData(SelectAllProductBean selectAllProductBean) {
            //全选
                if (selectAllProductBean.getCode().equals("200")) {
                        if (!AllSelectManager.getInstance().isSelect()) {
                            AllSelectManager.getInstance().setSelect(true);
                            all.setImageResource(R.drawable.checkbox_selected);
                            delAll.setImageResource(R.drawable.checkbox_selected);
                            for (ShortcartProductBean.ResultBean bean : result) {
                                bean.setAll(true);
                            }
                            count();
                            shoppingAdapter.notifyDataSetChanged();
                        } else {
                            AllSelectManager.getInstance().setSelect(false);
                            all.setImageResource(R.drawable.checkbox_unselected);
                            delAll.setImageResource(R.drawable.checkbox_unselected);
                            for (ShortcartProductBean.ResultBean bean : result) {
                                bean.setAll(false);
                            }
                            count();
                            shoppingAdapter.notifyDataSetChanged();
                        }

                        for (ShortcartProductBean.ResultBean bean : result) {
                            ShopCacheManger.getInstance().setSelect(bean);
                        }
                    }
                }

    //单选
    @Override
    public void postSelectOneProductData(SelectAllProductBean selectAllProductBean) {
        if (selectAllProductBean.getCode().equals("200")) {
            if (result.get(selectPosition).isAll()) {
                isSelectImg.setImageResource(R.drawable.checkbox_unselected);
                result.get(selectPosition).setAll(false);
                count();
                ShopCacheManger.getInstance().setSelect(result.get(selectPosition));
            } else {
                isSelectImg.setImageResource(R.drawable.checkbox_selected);
                result.get(selectPosition).setAll(true);
                count();
                ShopCacheManger.getInstance().setSelect(result.get(selectPosition));
            }
            shoppingAdapter.notifyItemChanged(selectPosition);

            //反选
            for (ShortcartProductBean.ResultBean resultBean : result) {
                if (resultBean.isAll()) {
                    num++;
                }
            }
            if (num == result.size()) {
                all.setImageResource(R.drawable.checkbox_selected);
                delAll.setImageResource(R.drawable.checkbox_selected);
                AllSelectManager.getInstance().setSelect(true);
            } else {
                all.setImageResource(R.drawable.checkbox_unselected);
                delAll.setImageResource(R.drawable.checkbox_unselected);
                AllSelectManager.getInstance().setSelect(false);
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

    //全选
    public void AllSelect() {
        if (AllSelectManager.getInstance().isSelect()) {
            httpPresenter.postSelectAllProductData(AllSelectManager.getInstance().isSelect());
        } else {
            httpPresenter.postSelectAllProductData(AllSelectManager.getInstance().isSelect());
        }
    }

    //添加数据如果全选中状态把全选状态改为非选中状态
    public void addShopChangeStatus(){
        if (AllSelectManager.getInstance().isSelect()){
            httpPresenter.postSelectAllProductData(AllSelectManager.getInstance().isSelect());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusinessUserManager.getInstance().UnRegister(this);
        EventBus.getDefault().unregister(this);
        ShopCacheManger.getInstance().unregisterShopBeanChange(this);
    }

    @Override
    public void OnShopBeanChange() {
        result = ShopCacheManger.getInstance().getShortBeanList();
        shoppingAdapter.updateData(result);
        shoppingAdapter.notifyDataSetChanged();
        count();
    }
}