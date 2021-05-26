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
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingcar.R;
import com.example.shoppingcar.shoppingtrolley.adapter.ShoppingCarAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingViewTrolleyFragment extends BaseFragment<ShoppingPresenter> implements IShoppingView, ShopeUserManager.IUserLoginChanged , ShoppingCarManager.IShoppingCar {
    private ToolBar toolbar;
    private RecyclerView shoppingTrolleyRv;
    private CheckBox checkAll;
    private TextView price;
    private TextView accout;
    private ShoppingCarAdapter shoppingCarAdapter;
    private boolean isCheck = false;
    private List<ShoppingTrolleyBean.ResultBean> result;
    private CheckBox checkBox;
    private int position;
    private boolean isRequest = true;
    private boolean isDelete = false;
    private RelativeLayout shopDeletLayout;
    private TextView delete;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppingtrolley;
    }

    @Override
    protected void initData() {

        //注册登录的回调和购物车添加的回调
        ShopeUserManager.getInstance().register(this::onLoginChange);
        ShoppingCarManager.getInstance().register(this::onShoppingCar);

        LoginBean loginBean = ShopeUserManager.getInstance().getLoginBean();
        if (loginBean != null) {
            httpPresenter.getShoppingData();
            isRequest = false;
        }

        //全选
        checkAll.setOnClickListener(view -> {
            isRequest = false;
            httpPresenter.getSelectAllProduct(!isCheck);
        });

        //删除
        delete.setOnClickListener(view -> {
            isRequest = false;
            List<DeleteBean> delete  = new ArrayList<>();
            for (int i = result.size() - 1; i >= 0; i--) {
                ShoppingTrolleyBean.ResultBean resultBean = result.get(i);
                if (resultBean.isProductSelected()){
                    DeleteBean deleteBean = new DeleteBean(resultBean.getProductId(),resultBean.getProductNum(),resultBean.getProductName(),resultBean.getUrl());
                    delete.add(deleteBean);
                }
            }
            httpPresenter.getRemoveManyProduct(delete);

        });

        //确定
        accout.setOnClickListener(view -> {
            List<DeleteBean> enough  = new ArrayList<>();
            for (int i = result.size() - 1; i >= 0; i--) {
                ShoppingTrolleyBean.ResultBean resultBean = result.get(position);
                if (resultBean.isProductSelected()){
                    DeleteBean deleteBean = new DeleteBean(resultBean.getProductId(),resultBean.getProductNum(),resultBean.getProductName(),resultBean.getUrl());
                    enough.add(deleteBean);
                }
            }
            httpPresenter.getCheckInventory(enough);
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
    }

    @Override
    public void onShopping(ShoppingTrolleyBean shoppingTrolleyBean) {
        isRequest = true;
        if (shoppingTrolleyBean.getCode().equals("200")) {
            result = shoppingTrolleyBean.getResult();

            //初始化购物车管理类
            ShoppingCarManager.getInstance().initResult(result);

            shoppingCarAdapter = new ShoppingCarAdapter(result);
            shoppingTrolleyRv.setLayoutManager(new LinearLayoutManager(getContext()));
            shoppingTrolleyRv.setAdapter(shoppingCarAdapter);

            shoppingCarAdapter.setShopItemListener((position, view) -> {
                int id = view.getId();
                if (id == R.id.shoppingTrolley_add) {
                } else if (id == R.id.shoppingTrolley_sub) {
                } else if (id == R.id.shoppingTrolley_check) {
                    checkBox = (CheckBox) view;
                    this.position = position;
                    ShoppingTrolleyBean.ResultBean resultBean = result.get(position);
                    httpPresenter.getUpDateSelected(resultBean.getProductId(),checkBox.isSelected(), resultBean.getProductName(), resultBean.getUrl(), resultBean.getProductPrice().toString());
                    isRequest = false;
                }
            });

        }
    }

    //修改选择
    @Override
    public void onUpDateSelected(RegisterBean registerBean) {
        isRequest = true;
        if (registerBean.getCode().equals("200")) {
            ShoppingTrolleyBean.ResultBean resultBean = result.get(position);
            resultBean.setProductSelected(checkBox.isChecked());
            ShoppingCarManager.getInstance().upDataResultBean(resultBean);
            result.get(position).setProductSelected(checkBox.isChecked());
            shoppingCarAdapter.notifyItemChanged(position);
        }
//        LogUtils.json(result.get(position));
    }

    //全部选择
    @Override
    public void onSelectAllProduct(RegisterBean registerBean) {
        isRequest = true;
        if (registerBean.getCode().equals("200")){
            if (!isCheck) {
                checkAll.setBackgroundResource(R.drawable.checkbox_selected);
                ShoppingCarManager.getInstance().checkAll();
                isCheck = true;
            } else {
                checkAll.setBackgroundResource(R.drawable.checkbox_unselected);
                ShoppingCarManager.getInstance().unCheckAll();
                isCheck = false;
            }
            for (ShoppingTrolleyBean.ResultBean resultBean : result) {
               if (resultBean.isProductSelected()!=isCheck){
                   resultBean.setProductSelected(isCheck);
               }
            }
            shoppingCarAdapter.notifyDataSetChanged();
        }
    }

    //删除
    @Override
    public void onRemoveManyProduct(RegisterBean registerBean) {
        isRequest = true;
        if (registerBean.getCode().equals("200")){
            List<ShoppingTrolleyBean.ResultBean> delete  = new ArrayList<>();
            //删除本地
            for (int i = result.size() - 1; i >= 0; i--) {
                if (result.get(i).isProductSelected()){
                    delete.add(result.get(i));
                    result.remove(i);
                }
            }
            //删除管理类
            ShoppingCarManager.getInstance().deletePartResult(false,delete);
            //刷新数据库
            shoppingCarAdapter.notifyDataSetChanged();
            //删除成功后隐藏
            toolbar.setRightTitle(getResources().getString(R.string.compile));
            shopDeletLayout.setVisibility(View.GONE);
            isDelete = false;
        }
    }

    //检查服务端多个产品是否库存充足
    @Override
    public void onCheckInventory(ShoppingTrolleyBean shoppingTrolleyBean) {
        if (shoppingTrolleyBean.getCode().equals("200")) {
            List<ShoppingTrolleyBean.ResultBean> result = shoppingTrolleyBean.getResult();
            Toast.makeText(getActivity(), "判断库存", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    //toolber的点击事件 判断是否显示删除
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


    //登录额时候请求数据
    @Override
    public void onLoginChange(LoginBean loginBean) {
        if (loginBean != null) {
            httpPresenter.getShoppingData();
            isRequest = false;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        //取消注册登录的回调和购物车添加的回调
        ShopeUserManager.getInstance().unregister(this::onLoginChange);
        ShoppingCarManager.getInstance().unregister(this::onShoppingCar);
    }

    //改变数据源后的通知
    @Override
    public void onShoppingCar(List<ShoppingTrolleyBean.ResultBean> result) {
       this.result = result;
       shoppingCarAdapter.notifyDataSetChanged();
    }
}
