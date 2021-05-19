package com.shoppingmall.main.home.adapter;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.Variable;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.HomeBean;


public class SecKillAdapter extends BaseRvAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean> {
    private ImageView secKillRvImg;
    private TextView secKillText;
    private TextView secKill;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_sec_kill_rv_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.SeckillInfoBean.ListBean itemData) {
        secKillRvImg = (ImageView) holder.getView(R.id.secKillRvImg);
        secKillText = (TextView) holder.getView(R.id.secKillText);
        secKill = (TextView) holder.getView(R.id.secKill);

        Glide.with(secKillRvImg.getContext()).load(Variable.IMG_HTTPS+itemData.getFigure()).into(secKillRvImg);
        secKill.setText("￥"+itemData.getCover_price());
        secKillText.setText("￥"+itemData.getOrigin_price());
        secKillText.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
    }


    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
