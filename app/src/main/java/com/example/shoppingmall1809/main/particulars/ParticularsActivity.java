package com.example.shoppingmall1809.main.particulars;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.ShopeUserManager;
import com.example.framework.manager.ShoppingCarManager;
import com.example.framework.view.ShopmallGlide;
import com.example.framework.view.ToolBar;
import com.example.net.model.CategoryBean;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingcar.addOneProduct.AddOnrProductPresenter;
import com.example.shoppingcar.addOneProduct.IAddOneProduct;
import com.example.shoppingmall1809.R;

public class ParticularsActivity extends BaseActivity implements IAddOneProduct {
    private ToolBar toolbar;
    private ImageView particularsImg;
    private TextView particularsText;
    private TextView particularsPrice;
    private LinearLayout toShopCar;
    private TextView particularsAdd;
    private View tou;

    private String productId;
    private String productName;
    private String productPrice;
    private String url;
    private int num = 1;
    private AddOnrProductPresenter addOnrProductPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();

        int code = intent.getIntExtra("code", 0);
        if (code == 1) {
            HoemBean.ResultBean.RecommendInfoBean recommend = (HoemBean.ResultBean.RecommendInfoBean) intent.getSerializableExtra("recommend");
            setString(recommend.getProduct_id(), recommend.getName(), recommend.getCover_price(), recommend.getFigure());
        } else if (code == 2) {
            HoemBean.ResultBean.HotInfoBean hotInfo = (HoemBean.ResultBean.HotInfoBean) intent.getSerializableExtra("hotInfo");
            setString(hotInfo.getProduct_id(), hotInfo.getName(), hotInfo.getCover_price(), hotInfo.getFigure());
        } else if (code == 3) {
            HoemBean.ResultBean.SeckillInfoBean.ListBean seckill_info = (HoemBean.ResultBean.SeckillInfoBean.ListBean) intent.getSerializableExtra("seckill_info");
            setString(seckill_info.getProduct_id(), seckill_info.getName(), seckill_info.getCover_price(), seckill_info.getFigure());
        } else if (code == 4) {
            CategoryBean.ResultBean.HotProductListBean hotProductListBeans = (CategoryBean.ResultBean.HotProductListBean) intent.getSerializableExtra("hotProductListBeans");
            setString(hotProductListBeans.getProduct_id(), hotProductListBeans.getName(), hotProductListBeans.getCover_price(), hotProductListBeans.getFigure());
        }

        Glide.with(this).load(url).into(particularsImg);
        particularsText.setText(productName);
        particularsPrice.setText("￥" + productPrice);

        particularsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginBean loginBean = ShopeUserManager.getInstance().getLoginBean();
                if (loginBean == null) {
                    CacheManager.getInstance().decideARoutPage = ShopConstants.AROUT_PARTICULARS;
                    ARouter.getInstance().build(ShopConstants.LOGIN_PATH).navigation();
                    return;
                }

                num=1;

                PopupWindow popupWindow = new PopupWindow();

                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(true);

                View inflate = LayoutInflater.from(ParticularsActivity.this).inflate(R.layout.item_particulars_pop, null);
                popupWindow.setContentView(inflate);

                ImageView popImg = inflate.findViewById(R.id.pop_img);
                TextView popText = inflate.findViewById(R.id.pop_text);
                TextView popPrice = inflate.findViewById(R.id.pop_price);
                ImageView popSub = inflate.findViewById(R.id.pop_sub);
                ImageView popAdd = inflate.findViewById(R.id.pop_add);
                TextView popNum = inflate.findViewById(R.id.pop_num);
                TextView popCancel = inflate.findViewById(R.id.pop_cancel);
                TextView popConfirm = inflate.findViewById(R.id.pop_confirm);

                popNum.setText(num + "");
                ShopmallGlide.with(ParticularsActivity.this)
                        .load(url)
                        .into(popImg);
                popText.setText(productName);
                popPrice.setText(productPrice);

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

//                        ShoppingTrolleyBean.ResultBean resultBean = ShoppingCarManager.getInstance().selectResultBean(productId);
//                        if (resultBean!=null){
//                            popupWindow.dismiss();
//                            return;
//                        }

                        addOnrProductPresenter = new AddOnrProductPresenter(ParticularsActivity.this);

                        addOnrProductPresenter.CheckOneProductInventory(productId,num+"");

                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(particularsAdd, Gravity.BOTTOM,0,0);
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        particularsImg = (ImageView) findViewById(R.id.particulars_img);
        particularsText = (TextView) findViewById(R.id.particulars_text);
        particularsPrice = (TextView) findViewById(R.id.particulars_price);
        toShopCar = (LinearLayout) findViewById(R.id.toShopCar);

        particularsAdd = (TextView) findViewById(R.id.particulars_add);
        tou = (View) findViewById(R.id.tou);
    }

    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        PopupWindow popupWindow = new PopupWindow();

        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        tou.setVisibility(View.VISIBLE);

        View inflate = LayoutInflater.from(this).inflate(R.layout.item_pop, null);
        popupWindow.setContentView(inflate);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tou.setVisibility(View.GONE);
            }
        });

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

    public void setString(String productId, String productName, String productPrice,
                          String url) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.url = Constants.BASE_URl_IMAGE + url;
    }

    @Override
    public void onAddOneProduct(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            Toast.makeText(this, "添加购物车成功", Toast.LENGTH_SHORT).show();

            ShoppingTrolleyBean.ResultBean resultBean=new ShoppingTrolleyBean.ResultBean();

            resultBean.setProductId(productId);
            resultBean.setProductName(productName);
            resultBean.setProductNum(num+"");
            resultBean.setProductPrice(productPrice);
            resultBean.setProductSelected(false);
            resultBean.setUrl(url);

            ShoppingCarManager.getInstance().insertResultBean(resultBean);
            ShoppingCarManager.getInstance().refreshData();
        } else {
            Toast.makeText(this, "添加购物车失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckOneProductInventory(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")){
            if (Integer.parseInt(registerBean.getResult()) >= num) {
                addOnrProductPresenter
                        .AddOneProduct(productId, num + "", productName, url, productPrice);
            } else {
                Toast.makeText(this, "该商品库存不足", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }
}
