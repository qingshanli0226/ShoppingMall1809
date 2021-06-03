package com.example.myapplication.shoppingcart;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.manager.CaCheMannager;
import com.example.myapplication.R;
import com.example.myapplication.payorder.OrderActivity;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShoppingCartBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragment extends BaseFragment<ShoppingCartPresenter> implements CaCheMannager.IShoppingCartInterface, IShoppingCartView, ShoppingCartRecAdapter.IRecyclerItemChildClickListener {

    private List<ShoppingCartBean.ResultBean> list = new ArrayList<>();
    private List<ShoppingCartBean.ResultBean> delList = new ArrayList<>();
    private TextView shoppingCartCompile;
    private RecyclerView shoppingCartRec;
    private CheckBox shoppingCartCheck;
    private TextView shoppingCartPrice;
    private ShoppingCartRecAdapter adapter;
    private boolean nowIsChe;
    private boolean nowIsSelect;
    private CheckBox itemChe;
    private int itemPosition;
    private boolean AddorSub;//true为+  flase为-
    private RelativeLayout shoppingCartRelativeLayout;
    private RelativeLayout shoppingCartCompileRelativeLayout;
    private CheckBox shoppingCartCompileCheck;
    private TextView shoppingCartCompileDelete;
    private TextView shoppingCartCompileCollect;

    private boolean getData = false;
    private Button priceBtn;

    @Override
    protected int bandLayout() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    public void initView() {
        priceBtn = (Button) findViewById(R.id.priceBtn);
        shoppingCartCompile = (TextView) findViewById(R.id.shoppingCartCompile);
        shoppingCartRec = (RecyclerView) findViewById(R.id.shoppingCartRec);
        shoppingCartCheck = (CheckBox) findViewById(R.id.shoppingCartCheck);
        shoppingCartPrice = (TextView) findViewById(R.id.shoppingCartPrice);
        shoppingCartRelativeLayout = (RelativeLayout) findViewById(R.id.shoppingCartRelativeLayout);
        shoppingCartCompileRelativeLayout = (RelativeLayout) findViewById(R.id.shoppingCartCompileRelativeLayout);
        shoppingCartCompileCheck = (CheckBox) findViewById(R.id.shoppingCartCompileCheck);
        shoppingCartCompileDelete = (TextView) findViewById(R.id.shoppingCartCompileDelete);
        shoppingCartCompileCollect = (TextView) findViewById(R.id.shoppingCartCompileCollect);
        //万能适配器
        adapter = new ShoppingCartRecAdapter(list);
        shoppingCartRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        shoppingCartRec.setAdapter(adapter);
        adapter.setChildClickListener(this);//注册子控件点击
    }

    @Override
    public void initPresenter() {
        mPresenter = new ShoppingCartPresenter(this);
    }

    @Override
    public void initData() {
        //全选按钮
        shoppingCartCheck.setOnClickListener(v -> {
            nowIsChe = shoppingCartCheck.isChecked();//获取当前的按钮状态
            if (nowIsChe) {//当前按钮是true
                mPresenter.updatateAllSelect(true);
            } else {
                mPresenter.updatateAllSelect(false);
            }
        });
        //编辑里面的全选
        shoppingCartCompileCheck.setOnClickListener(v -> {
            nowIsChe = shoppingCartCompileCheck.isChecked(); //获取当前的按钮状态
            if (nowIsChe) {//当前按钮是true
                mPresenter.updatateAllSelect(true);
            } else {
                mPresenter.updatateAllSelect(false);
            }
        });
        //去结算
        priceBtn.setOnClickListener(v -> {
            if (delList.size() == 0) {
                Toast.makeText(getActivity(), getString(R.string.myShoppingCartSelectShopping), Toast.LENGTH_SHORT).show();
            } else {
                CaCheMannager.getInstance().setCheckList(delList);
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("shoppingPrice", shoppingCartPrice.getText().toString() + "");
                startActivity(intent);
            }
        });
        //点击编辑
        shoppingCartCompile.setOnClickListener(v -> {
            if (shoppingCartCompile.getText().toString().equals(getString(R.string.myShoppingCartCompile))) {
                shoppingCartRelativeLayout.setVisibility(View.GONE);//隐藏刚进去的布局
                shoppingCartCompileRelativeLayout.setVisibility(View.VISIBLE);//显示编辑布局
                shoppingCartCompile.setText(getString(R.string.myShoppingCartFinish));//更换样式
            } else {
                shoppingCartRelativeLayout.setVisibility(View.VISIBLE);//隐藏刚进去的布局
                shoppingCartCompileRelativeLayout.setVisibility(View.GONE);//显示编辑布局
                shoppingCartCompile.setText(getString(R.string.myShoppingCartCompile)); //更换样式
            }
        });
        //点击删除
        shoppingCartCompileDelete.setOnClickListener(v -> {
            if (delList.size() == 0) {
                Toast.makeText(getActivity(), getString(R.string.myShoppingCartSelectShopping), Toast.LENGTH_SHORT).show();
            } else if (delList.size() == 1) {//删除单个
                ShoppingCartBean.ResultBean resultBean = delList.get(0);
                mPresenter.deleteOneShopping(resultBean.getProductId(), resultBean.getProductNum(), resultBean.getProductName(), resultBean.getUrl(), resultBean.getProductPrice() + "");
            } else {//删除多个
                mPresenter.RemoveManvProduct(delList);
            }
        });
        getTotalPrice();//价格
    }

    /**
     * 子控件点击
     */
    @Override
    public void onItemCheClick(int position, View view) {
        CheckBox checkBox = (CheckBox) view;
        itemChe = checkBox;
        itemPosition = position;
        nowIsSelect = checkBox.isChecked();//获取现在有没有成功
        ShoppingCartBean.ResultBean resultBean = list.get(position);
        mPresenter.updateShoppingSlect(resultBean.getProductId(), nowIsSelect, resultBean.getProductName(), resultBean.getUrl(), resultBean.getProductPrice() + "");
    }

    @Override
    public void onItemAddClick(int position, View view) {
        AddorSub = true;
        itemPosition = position;
        ShoppingCartBean.ResultBean resultBean = list.get(position);
        mPresenter.getInventory(resultBean.getProductId(), resultBean.getProductNum());
    }

    @Override
    public void onItemSubClick(int position, View view) {
        AddorSub = false;
        itemPosition = position;
        ShoppingCartBean.ResultBean resultBean = list.get(position);
        mPresenter.updateShoppingNum(resultBean.getProductId(), resultBean.getProductNum(), resultBean.getProductName(), resultBean.getUrl(), resultBean.getProductPrice() + "");
    }

    //全选返回值
    @Override
    public void onupdateCheck(RegisterBean bean) {
        if (bean.getCode().equals("200")) {
            //修改子控件的值
            for (ShoppingCartBean.ResultBean be :
                    list) {
                be.setCheck(nowIsChe);
            }
            Toast.makeText(getActivity(), "每圈进", Toast.LENGTH_SHORT).show();
            if (nowIsChe) {//如果是全部变成true则全部加入到删除集合  反则清除
                delList.addAll(list);
            } else {
                delList.clear();
            }
            getTotalPrice();//总价
        } else {
            Toast.makeText(getActivity(), getString(R.string.myShoppingCartUpdataAllError), Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
        CaCheMannager.getInstance().setShoppingCartBeanList(list);//修改缓存类
    }

    //单选返回值
    @Override
    public void onupdateShoppingSelect(RegisterBean bean) {
        if (bean.getCode().equals("200")) {
            list.get(itemPosition).setCheck(nowIsSelect);
            if (nowIsSelect) {
                delList.add(list.get(itemPosition));
            } else {
                delList.remove(list.get(itemPosition));
            }
            CaCheMannager.getInstance().setShoppingCartBeanList(list);//修改缓存类
            //遍历  如果选择了全部商品则勾上全选
            boolean isAll = true;
            for (ShoppingCartBean.ResultBean re :
                    list) {
                if (!re.isCheck()) {
                    isAll = false;
                }
            }
            if (isAll) {
                shoppingCartCheck.setChecked(true);
            } else {
                shoppingCartCheck.setChecked(false);
            }
            getTotalPrice();//总价
        } else {
            Toast.makeText(getActivity(), getString(R.string.myShoppingCartUpdataError), Toast.LENGTH_SHORT).show();
            if (nowIsSelect) {
                itemChe.setChecked(false);
            } else {
                itemChe.setChecked(true);
            }
        }
    }

    //更改数量
    @Override
    public void onShoppingSetNum(RegisterBean bean) {
        if (bean.getCode().equals("200")) {
            if (AddorSub) {//加
                list.get(itemPosition).setProductNum((Integer.parseInt(list.get(itemPosition).getProductNum()) + 1) + "");
            } else {
                list.get(itemPosition).setProductNum((Integer.parseInt(list.get(itemPosition).getProductNum()) - 1) + "");
            }
            adapter.notifyItemChanged(itemPosition);
            CaCheMannager.getInstance().setShoppingCartBeanList(list);//更改缓存类
            getTotalPrice();//更新价格
        } else {
            Toast.makeText(getActivity(), getString(R.string.myShoppingCartUpdataNumError), Toast.LENGTH_SHORT).show();
        }
    }

    //库存
    @Override
    public void onIsInventory(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            ShoppingCartBean.ResultBean resultBean = list.get(itemPosition);
            mPresenter.updateShoppingNum(resultBean.getProductId(), resultBean.getProductNum(), resultBean.getProductName(), resultBean.getUrl(), resultBean.getProductPrice() + "");
        } else {
            //库存不足
            Toast.makeText(getActivity(), getString(R.string.myShoppingCartUnderstock), Toast.LENGTH_SHORT).show();
        }
    }

    //删除单个
    @Override
    public void onDeleteOneShopping(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            if (list.size()==1){//如果只剩下一个数据 点击全选的时候 删除所有数据
                shoppingCartCheck.setChecked(false);
                shoppingCartCompileCheck.setChecked(false);
                delList.clear();//清空编辑里面的集合
                list.clear();
                adapter.notifyItemRemoved(0);
                CaCheMannager.getInstance().setShoppingCartBeanList(list); //更新缓存类
                EventBus.getDefault().post("1");//发送Evenbus
            }else {
                delList.clear();//清空编辑里面的集合
                list.remove(itemPosition);
                adapter.notifyItemRemoved(itemPosition);
                CaCheMannager.getInstance().setShoppingCartBeanList(list); //更新缓存类
                EventBus.getDefault().post("1");//发送Evenbus
            }
        } else {
            Toast.makeText(getActivity(), getString(R.string.myShoppingCartRemoveError), Toast.LENGTH_SHORT).show();
        }
    }

    //删除多个
    @Override
    public void onRemoveManvProduct(RegisterBean registerBean) {
        if (registerBean.getCode().endsWith("200")) {
            shoppingCartCheck.setChecked(false); //修改多选按钮
            shoppingCartCompileCheck.setChecked(false);
            for (int i = 0; i < delList.size(); i++) {
                list.remove(delList.get(i));
            }
            delList.clear();//将删除集合里面的数据清空
            CaCheMannager.getInstance().setShoppingCartBeanList(list);
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post("1");//发送Evenbus
        } else {
            Toast.makeText(getActivity(), getString(R.string.myShoppingCartRemoveError), Toast.LENGTH_SHORT).show();
        }
    }

    public void getTotalPrice() {
        //遍历集合
        int money = 0;
        for (ShoppingCartBean.ResultBean bean :
                list) {
            if (bean.isCheck()) {
                int num = Integer.parseInt(bean.getProductNum());
                float price = Float.parseFloat(bean.getProductPrice() + "");
                money += num * price;
            }
        }
        shoppingCartPrice.setText(money + "");
    }

    @Override
    public void onShoppinCartgData(List<ShoppingCartBean.ResultBean> shoppingCartBean) {
        if (shoppingCartBean != null) {
            list.clear();
            list.addAll(shoppingCartBean);
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post("1");//发送Evenbus
        }
    }

    @Override
    public void onShoppingCartRemove(int position) {
        //删除一个商品
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onShoppingCartRemove(List<ShoppingCartBean.ResultBean> shoppingCartBean) {
        //删除多个
        for (int i = 0; i < shoppingCartBean.size(); i++) {
            list.remove(shoppingCartBean.get(i));
        }
        adapter.notifyDataSetChanged();
    }
}