package com.example.shoppingmallsix.goods;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.SoppingCartMemoryDataManager;
import com.example.framework.view.ToolBar;

import com.example.net.bean.business.AddOneProductBean;
import com.example.net.bean.business.CheckOneInventoryBean;
import com.example.net.bean.business.UpdateProductNumBean;
import com.example.net.bean.store.GoodAdapterBean;
import com.example.net.bean.user.LoginBean;
import com.example.shoppingmallsix.goods.adapter.GoodsAdapter;

import com.example.net.bean.business.GetShortcartProductsBean;

import com.example.net.constants.Constants;

import com.example.shoppingmallsix.R;
import com.example.user.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class GoodsActivity extends BaseActivity<GoodsPresenter> implements IGoodsView, SoppingCartMemoryDataManager.ISoppingDateChange {


    private com.example.framework.view.ToolBar toolbar;
    private RecyclerView moreRv;
    private List<GoodAdapterBean> list = new ArrayList<>();
    private GoodsAdapter goodsAdapter;
    private String name;
    private String figure;
    private String price;
    private LinearLayout shopcar;
    private android.widget.Button insertShopcar;
    private int num = 1;
    private String id;
    private GoodAdapterBean goodAdapterBean;
    private List<GetShortcartProductsBean.ResultBean> resultBeans = new ArrayList<>();
    private GetShortcartProductsBean.ResultBean resultBean = null;
    private int index;
    private TextView goodsSign;

    @Override
    protected void initPresenter() {
        httpPresenter = new GoodsPresenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        figure = intent.getStringExtra("figure");
        price = intent.getStringExtra("price");
        goodAdapterBean = new GoodAdapterBean(id, name, figure, price);
        list.add(goodAdapterBean);
        list.add(goodAdapterBean);
        goodsAdapter.notifyDataSetChanged();
        //注册接口
        SoppingCartMemoryDataManager.getInstance().registerHoppingCartMemory(this);
        //获取内存数据
        resultBeans = SoppingCartMemoryDataManager.getResultBean().getResult();
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        moreRv = (RecyclerView) findViewById(R.id.moreRv);
        shopcar = findViewById(R.id.shopcar);
        insertShopcar = findViewById(R.id.insertShopcar);
        goodsSign = (TextView) findViewById(R.id.goodsSign);


        goodsAdapter = new GoodsAdapter(list);
        moreRv.setAdapter(goodsAdapter);
        moreRv.setLayoutManager(new LinearLayoutManager(this));


        insertShopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginBean loginBean1 = CacheUserManager.getInstance().getLoginBean();
                List<GetShortcartProductsBean.ResultBean> result = SoppingCartMemoryDataManager.getResultBean().getResult();
                if (loginBean1 != null && result.size() != 0) {
                    goodsSign.setVisibility(View.VISIBLE);
                    goodsSign.setText(result.size() + "");
                    initPopupWindow();
                } else {
                    Toast.makeText(GoodsActivity.this, "请先登录账户", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GoodsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initPopupWindow() {
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        View inflate = LayoutInflater.from(GoodsActivity.this).inflate(R.layout.item_particulars_pop, null);

        ImageView popImg = inflate.findViewById(R.id.pop_img);
        TextView popText = inflate.findViewById(R.id.pop_text);
        TextView popPrice = inflate.findViewById(R.id.pop_price);
        ImageView popSub = inflate.findViewById(R.id.pop_sub);
        ImageView popAdd = inflate.findViewById(R.id.pop_add);
        TextView popNum = inflate.findViewById(R.id.pop_num);
        TextView popCancel = inflate.findViewById(R.id.pop_cancel);
        TextView popConfirm = inflate.findViewById(R.id.pop_confirm);
        popupWindow.setContentView(inflate);

        popNum.setText(num + "");
        Glide.with(GoodsActivity.this)
                .load(Constants.BASE_URl_IMAGE + figure)
                .into(popImg);
        popText.setText(name);
        popPrice.setText(price);

        popAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                popNum.setText(num + "");
            }
        });
        popSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num--;
                if (num <= 0) {
                    num = 1;
                }
                popNum.setText(num + "");
            }
        });
        popCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpPresenter.checkInventory(id, num + "");
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(toolbar, 0, 900, Gravity.BOTTOM);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods;
    }


    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        PopupWindow popupWindow = new PopupWindow();

        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        View inflate = LayoutInflater.from(this).inflate(R.layout.item_pop, null);
        popupWindow.setContentView(inflate);

        LinearLayout viewById = inflate.findViewById(R.id.toMain);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(toolbar);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddCart(AddOneProductBean productBean) {
        if (productBean.getCode().equals("200")) {
            Toast.makeText(this, getString(R.string.add), Toast.LENGTH_SHORT).show();
            GetShortcartProductsBean.ResultBean resultBean = new GetShortcartProductsBean.ResultBean();
            resultBean.setProductId(id);
            resultBean.setUrl(figure);
            resultBean.setProductName(name);
            resultBean.setProductNum(num + "");
            resultBean.setProductPrice(price);
            resultBeans.add(resultBean);
            SoppingCartMemoryDataManager.notifyDataSetChanged();
        } else {
            Toast.makeText(this, getString(R.string.dda), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckInventory(CheckOneInventoryBean bean) {
        if (bean.getCode().equals("200")) {
            Toast.makeText(this, "检查库存有", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < resultBeans.size(); i++) {
                if (name.equals(resultBeans.get(i).getProductName())) {
                    resultBean = resultBeans.get(i);
                    index = i;
                }
            }
            if (resultBean != null) {
                httpPresenter.updateProduceNum(id, (Integer.parseInt(resultBean.getProductNum()) + num) + "", name, figure, price);
            } else {
                httpPresenter.addOneProduct(id, num + "", name, figure, price);
            }

        } else {
            Toast.makeText(this, "检查库存没有", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateProductNum(UpdateProductNumBean updateProductNumBean) {
        if (updateProductNumBean.getCode().equals("200")) {
            Toast.makeText(this, "更新", Toast.LENGTH_SHORT).show();
            //更新
            resultBeans.get(index).setProductNum("" + (Integer.parseInt(resultBean.getProductNum()) + num));
            SoppingCartMemoryDataManager.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "没有更新", Toast.LENGTH_SHORT).show();
        }
    }

    //子线程获取数据 实时刷新
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            GetShortcartProductsBean resultBean = SoppingCartMemoryDataManager.getResultBean();
            if (resultBean != null) {
                List<GetShortcartProductsBean.ResultBean> result = resultBean.getResult();
                if (result != null && result.size() != 0) {
                    goodsSign.setVisibility(View.VISIBLE);
                    goodsSign.setText(result.size() + "");
                }
            } else {
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    public void onSoppingDataChange() {
        resultBeans = SoppingCartMemoryDataManager.getResultBean().getResult();
        if (resultBeans.size() != 0) {
            //红点刷新数量
            goodsSign.setVisibility(View.VISIBLE);
            goodsSign.setText(resultBeans.size() + "");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁接口
        SoppingCartMemoryDataManager.getInstance().unHoppingCartMemory(this);
    }

}
