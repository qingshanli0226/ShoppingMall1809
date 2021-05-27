package com.example.shoppingcar.shoppingtrolley;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseFragment;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.DeleteBean;
import com.example.net.model.OrderInfoParamBean;
import com.example.net.model.OrderinfoBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingcar.R;
import com.example.shoppingcar.shoppingtrolley.adapter.ShoppingCarAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingViewTrolleyFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView, ShoppingCarManager.IShoppingCar{
    private ToolBar toolbar;
    private RecyclerView shoppingTrolleyRv;
    private CheckBox checkAll;
    private TextView price;
    private TextView accout;
    private ShoppingCarAdapter shoppingCarAdapter;
    private boolean isCheck = false;
    private List<ShoppingTrolleyBean.ResultBean> result = new ArrayList<>();
    private CheckBox checkBox;
    private int position;
    private boolean isRequest = true;
    private boolean isDelete = false;
    private RelativeLayout shopDeletLayout;
    private TextView delete;
    private int count = 0;
    private RelativeLayout vain;
    private boolean isOneNotifyDataSetChanged = false;
    private float money = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppingtrolley;
    }

    @Override
    protected void initData() {

        String stringExtra = getActivity().getIntent().getStringExtra("");
        if (stringExtra != null && stringExtra.equals("")) {

        }

        List<ShoppingTrolleyBean.ResultBean> resultManager = ShoppingCarManager.getInstance().getResult();
        if (resultManager != null) {
            result = resultManager;
        }

        if (result.size() <= 0) {
            vain.setVisibility(View.VISIBLE);
        } else {
            vain.setVisibility(View.GONE);
        }

        //购物车添加的回调
        ShoppingCarManager.getInstance().register(this::onShoppingCar);


        shoppingCarAdapter = new ShoppingCarAdapter(this.result);
        shoppingTrolleyRv.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingTrolleyRv.setAdapter(shoppingCarAdapter);

        shoppingCarAdapter.setShopItemListener((position, view) -> {
            int id = view.getId();
            this.position = position;
            if (id == R.id.shoppingTrolley_add) {
                //添加是否去判断库存是否够
                ShoppingTrolleyBean.ResultBean resultBean = this.result.get(position);
                isRequest = false;
                httpPresenter.getCheckOneProductInventory(resultBean.getProductId(), resultBean.getProductNum());
            } else if (id == R.id.shoppingTrolley_sub) {
                ShoppingTrolleyBean.ResultBean resultBean = this.result.get(position);
                count = Integer.parseInt(resultBean.getProductNum());
                if (count <= 1) {
                    Toast.makeText(getActivity(), "已经是最小了", Toast.LENGTH_SHORT).show();
                } else {
                    --count;
                    isRequest = false;
                    httpPresenter.getUpDateProductNum(resultBean.getProductId(), count + "", resultBean.getProductName(), resultBean.getUrl(), (String) resultBean.getProductPrice());
                }

            } else if (id == R.id.shoppingTrolley_check) {
                checkBox = (CheckBox) view;
                ShoppingTrolleyBean.ResultBean resultBean = this.result.get(position);
                isRequest = false;
                httpPresenter.getUpDateSelected(resultBean.getProductId(), checkBox.isSelected(), resultBean.getProductName(), resultBean.getUrl(), resultBean.getProductPrice().toString());
            }
        });


        //全选
        checkAll.setOnClickListener(view -> {
            isRequest = false;
            httpPresenter.getSelectAllProduct(!isCheck);
        });

        //删除
        delete.setOnClickListener(view -> {
            isRequest = false;
            List<DeleteBean> delete = new ArrayList<>();
            for (int i = this.result.size() - 1; i >= 0; i--) {
                ShoppingTrolleyBean.ResultBean resultBean = this.result.get(i);
                if (resultBean.isProductSelected()) {
                    DeleteBean deleteBean = new DeleteBean(resultBean.getProductId(), resultBean.getProductNum(), resultBean.getProductName(), resultBean.getUrl());
                    delete.add(deleteBean);
                }
            }
            if (delete.size() <= 0) {
                Toast.makeText(getActivity(), "请先选择一个", Toast.LENGTH_SHORT).show();
            } else {
                isRequest = false;
                httpPresenter.getRemoveManyProduct(delete);
            }

        });

        //确定
        accout.setOnClickListener(view -> {
            List<DeleteBean> enough = new ArrayList<>();
            for (int i = this.result.size() - 1; i >= 0; i--) {
                ShoppingTrolleyBean.ResultBean resultBean = this.result.get(i);
                if (resultBean.isProductSelected()) {
                    DeleteBean deleteBean = new DeleteBean(resultBean.getProductId(), resultBean.getProductNum(), resultBean.getProductName(), resultBean.getUrl());
                    enough.add(deleteBean);
                }
            }
            if (enough.size() <= 0) {
                Toast.makeText(getActivity(), "请先选择一个", Toast.LENGTH_SHORT).show();
            } else {
                isRequest = false;
                httpPresenter.getCheckInventory(enough);
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ShoppingPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        shoppingTrolleyRv = (RecyclerView) findViewById(R.id.shoppingTrolley_rv);
        checkAll = (CheckBox) findViewById(R.id.checkAll);
        price = (TextView) findViewById(R.id.price);
        accout = (TextView) findViewById(R.id.accout);
        shopDeletLayout = (RelativeLayout) findViewById(R.id.shop_delet_layout);
        delete = (TextView) findViewById(R.id.delete);
        vain = (RelativeLayout) findViewById(R.id.vain);
    }


    //修改选择
    @Override
    public void onUpDateSelected(RegisterBean registerBean) {
        isRequest = true;
        if (registerBean.getCode().equals("200")) {
            isOneNotifyDataSetChanged = true;
            ShoppingTrolleyBean.ResultBean resultBean = result.get(position);
            resultBean.setProductSelected(checkBox.isChecked());
            selectAll();
            ShoppingCarManager.getInstance().upDataResultBean(resultBean);

        }
    }

    //全部选择
    @Override
    public void onSelectAllProduct(RegisterBean registerBean) {
        isRequest = true;
        if (registerBean.getCode().equals("200")) {
            isOneNotifyDataSetChanged = false;
            if (!isCheck) {
                checkAll.setBackgroundResource(R.drawable.checkbox_selected);
                ShoppingCarManager.getInstance().checkAll();
                isCheck = true;
            } else {
                checkAll.setBackgroundResource(R.drawable.checkbox_unselected);
                ShoppingCarManager.getInstance().unCheckAll();
                isCheck = false;
            }
        }
    }

    //删除
    @Override
    public void onRemoveManyProduct(RegisterBean registerBean) {
        isRequest = true;
        if (registerBean.getCode().equals("200")) {
            isOneNotifyDataSetChanged = false;
            List<ShoppingTrolleyBean.ResultBean> delete = new ArrayList<>();
            //获取要删除的
            for (int i = result.size() - 1; i >= 0; i--) {
                if (result.get(i).isProductSelected()) {
                    delete.add(result.get(i));
                }
            }
            //删除管理类
            ShoppingCarManager.getInstance().deletePartResult(false, delete);
            //删除成功后隐藏
            toolbar.setRightTitle(getResources().getString(R.string.compile));
            shopDeletLayout.setVisibility(View.GONE);
            isDelete = false;
        }
    }

    //检查服务端多个产品是否库存充足
    @Override
    public void onCheckInventory(ShoppingTrolleyBean shoppingTrolleyBean) {
        isRequest = true;
        if (shoppingTrolleyBean.getCode().equals("200")) {
            List<ShoppingTrolleyBean.ResultBean> result = shoppingTrolleyBean.getResult();
            LogUtils.json(result);
            boolean ishttp = false;
            for (ShoppingTrolleyBean.ResultBean resultBean : result) {
                if (Integer.parseInt(resultBean.getProductNum()) <= 0) {
                    ishttp = true;
                }
            }
            if (ishttp) {
                Toast.makeText(getActivity(), "库存不足", Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<OrderInfoParamBean.BodyBean> bodyBeans = new ArrayList<>();
                for (int i = this.result.size() - 1; i >= 0; i--) {
                    ShoppingTrolleyBean.ResultBean resultBean = this.result.get(i);
                    if (resultBean.isProductSelected()) {
                        bodyBeans.add(new OrderInfoParamBean.BodyBean(resultBean.getProductName(), resultBean.getProductId()));
                    }
                }
                OrderInfoParamBean orderInfoParamBean = new OrderInfoParamBean("buy", money + "", bodyBeans);
//                isRequest = false;
//                httpPresenter.getOrderInfo(orderInfoParamBean);
            }
        }
    }

    //下订单
    @Override
    public void onOrderInfo(OrderinfoBean orderinfoBean) {
        isRequest = true;
        if (orderinfoBean.getCode().equals("200")) {
            ArrayList<ShoppingTrolleyBean.ResultBean> resultBeans = new ArrayList<>();
            for (int i = this.result.size() - 1; i >= 0; i--) {
                ShoppingTrolleyBean.ResultBean resultBean = this.result.get(i);
                if (resultBean.isProductSelected()) {
                    resultBeans.add(resultBean);
                }
            }
            ShoppingCarManager.getInstance().deletePartResult(true, resultBeans);
            Toast.makeText(getActivity(), "下单成功", Toast.LENGTH_SHORT).show();
        }
    }

    //加减库存
    @Override
    public void onUpDateProductNum(RegisterBean registerBean) {
        isRequest = true;
        if (registerBean.getCode().equals("200")) {
            isOneNotifyDataSetChanged = true;
            ShoppingTrolleyBean.ResultBean resultBean = result.get(position);
            resultBean.setProductNum(count + "");

            ShoppingCarManager.getInstance().upDataResultBean(resultBean);
        }
    }

    //判断单个是否有库存
    @Override
    public void onCheckOneProductInventory(RegisterBean registerBean) {
        isRequest = true;
        if (registerBean.getCode().equals("200")) {
            ShoppingTrolleyBean.ResultBean resultBean = result.get(position);
            if (Integer.parseInt(registerBean.getResult()) > 0) {
                isOneNotifyDataSetChanged = true;
                count = Integer.parseInt(resultBean.getProductNum());
                count++;
                httpPresenter.getUpDateProductNum(resultBean.getProductId(), count + "", resultBean.getProductName(), resultBean.getUrl(), (String) resultBean.getProductPrice());
            } else {
                Toast.makeText(getActivity(), "库存不足", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //计算价格
    public void totalPrice() {
        float price = 0;
        for (ShoppingTrolleyBean.ResultBean resultBean : result) {
            if (resultBean.isProductSelected()) {
                price += Float.valueOf((String) resultBean.getProductPrice()) * Float.valueOf(Integer.parseInt(resultBean.getProductNum()));
            }
        }
        this.price.setText("￥" + price);
        money = price;
    }

    public void selectAll() {
        int countSelect = 0;
        for (ShoppingTrolleyBean.ResultBean resultBean : result) {
            if (resultBean.isProductSelected()) {
                countSelect++;
            }
        }
        if (countSelect == result.size()) {
            checkAll.setBackgroundResource(R.drawable.checkbox_selected);
            isCheck = true;
        } else {
            checkAll.setBackgroundResource(R.drawable.checkbox_unselected);
            isCheck = false;
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    //toolbar的点击事件 判断是否显示删除
    @Override
    public void onRightTitle() {
        if (!isDelete) {
            toolbar.setRightTitle(getResources().getString(R.string.iscompleted));
            shopDeletLayout.setVisibility(View.VISIBLE);
            isDelete = true;
        } else {
            toolbar.setRightTitle(getResources().getString(R.string.compile));
            shopDeletLayout.setVisibility(View.GONE);
            isDelete = false;
        }
    }

    @Override
    public void Error(String error) {
        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void destroy() {
        super.destroy();
        //购物车添加的回调
        ShoppingCarManager.getInstance().unregister(this::onShoppingCar);
    }

    //改变数据源后的通知
    @Override
    public void onShoppingCar(List<ShoppingTrolleyBean.ResultBean> result) {
        if (result.size() <= 0) {
            vain.setVisibility(View.VISIBLE);
        } else {
            vain.setVisibility(View.GONE);
            this.result = result;
            if (isOneNotifyDataSetChanged) {
                shoppingCarAdapter.notifyItemChanged(position);
            } else {
                shoppingCarAdapter.notifyDataSetChanged();
            }

            totalPrice();
        }
    }
}
