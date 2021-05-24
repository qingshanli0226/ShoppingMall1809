package com.example.shoppingmall1809.main.particulars;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.shoppingmall1809.R;

import java.io.Serializable;

public class ParticularsActivity extends BaseActivity {
    private ToolBar toolbar;
    private ImageView particularsImg;
    private TextView particularsText;
    private TextView particularsPrice;
    private LinearLayout toShopCar;
    private TextView particularsAdd;
    private View tou;

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

            Glide.with(this).load(Constants.BASE_URl_IMAGE + recommend.getFigure()).into(particularsImg);
            particularsText.setText(recommend.getName());
            particularsPrice.setText("￥" + recommend.getCover_price());
        } else if (code == 2) {
            HoemBean.ResultBean.HotInfoBean hotInfo = (HoemBean.ResultBean.HotInfoBean) intent.getSerializableExtra("hotInfo");

            Glide.with(this).load(Constants.BASE_URl_IMAGE + hotInfo.getFigure()).into(particularsImg);
            particularsText.setText(hotInfo.getName());
            particularsPrice.setText("￥" + hotInfo.getCover_price());
        } else if (code == 3) {
            HoemBean.ResultBean.SeckillInfoBean.ListBean seckill_info = (HoemBean.ResultBean.SeckillInfoBean.ListBean) intent.getSerializableExtra("seckill_info");

            Glide.with(this).load(Constants.BASE_URl_IMAGE + seckill_info.getFigure()).into(particularsImg);
            particularsText.setText(seckill_info.getName());
            particularsPrice.setText("￥" + seckill_info.getCover_price());
        }

        particularsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginBean loginBean = FiannceUserManager.getInstance().getLoginBean();
                if (loginBean == null) {
                    CacheManager.getInstance().decideARoutPage = ShopConstants.AROUT_PARTICULARS;
                    ARouter.getInstance().build(ShopConstants.LOGIN_PATH).navigation();
                    return;
                }

                PopupWindow popupWindow = new PopupWindow();

                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);

                View inflate = LayoutInflater.from(ParticularsActivity.this).inflate(R.layout.item_particulars_pop, null);
                popupWindow.setContentView(inflate);

                popupWindow.showAsDropDown(particularsAdd);
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
}
