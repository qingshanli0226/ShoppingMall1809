package com.shoppingmall.detail;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.finance.detail.R;
import com.shoppingmall.detail.bean.ProductGoodBean;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.glide.ShopMallGlide;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.HomeBean;

import java.io.Serializable;

@Route(path = Constants.TO_DETAIL_ACTIVITY)
public class DetailActivity extends BaseActivity {

    private ImageView detailBack;
    private ImageView detailMenu;
    private ImageView detailImg;
    private TextView detailTitle;
    private TextView detailPrice;
    private PopupWindow popupWindow;
    private ProductGoodBean productGoodBean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {

        detailBack = (ImageView) findViewById(R.id.detailBack);
        detailMenu = (ImageView) findViewById(R.id.detailMenu);
        detailImg = (ImageView) findViewById(R.id.detailImg);
        detailTitle = (TextView) findViewById(R.id.detailTitle);
        detailPrice = (TextView) findViewById(R.id.detailPrice);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //退出当前页面
        detailBack.setOnClickListener(v->{
            backActivity();
        });
        //打开popWindow
        detailMenu.setOnClickListener(v->{
            openPopWindow();
        });

        Intent intent = getIntent();
        Serializable goods = intent.getSerializableExtra("goods");
        String type = intent.getStringExtra("type");
        if (goods!=null&&type!=null){
            switch (type){
                case "seckill":
                    HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = (HomeBean.ResultBean.SeckillInfoBean.ListBean) goods;
                    productGoodBean = new ProductGoodBean(listBean.getName(), listBean.getCover_price(), listBean.getFigure(), listBean.getProduct_id());
                    break;
                case "recommend":
                    HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = (HomeBean.ResultBean.RecommendInfoBean) goods;
                    productGoodBean = new ProductGoodBean(recommendInfoBean.getName(), recommendInfoBean.getCover_price(), recommendInfoBean.getFigure(), recommendInfoBean.getProduct_id());
                    break;
                case "hot":
                    HomeBean.ResultBean.HotInfoBean hotInfoBean = (HomeBean.ResultBean.HotInfoBean) goods;
                    productGoodBean = new ProductGoodBean(hotInfoBean.getName(), hotInfoBean.getCover_price(), hotInfoBean.getFigure(), hotInfoBean.getProduct_id());
                    break;
            }
            setUI(productGoodBean);
        }
    }

    private void setUI(ProductGoodBean productGoodBean) {
        ShopMallGlide.with(this).load(Constants.IMG_HTTPS+productGoodBean.getFigure()).into(detailImg);
        detailTitle.setText(""+productGoodBean.getName());
        detailPrice.setText(""+productGoodBean.getCover_price());
    }

    //打开popWindow
    private void openPopWindow() {
        detailMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popupWindow = new PopupWindow();
                View inflate = LayoutInflater.from(DetailActivity.this).inflate(R.layout.detail_pop_wiondow_layout, null);
                popupWindow.setContentView(inflate);
                popupWindow.setWidth(RadioGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(inflate,Gravity.TOP,0,190);
            }
        });
    }
    //退出当前Activity
    private void backActivity(){
        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (popupWindow.isShowing()){
//            popupWindow.dismiss();
//        }
    }
}