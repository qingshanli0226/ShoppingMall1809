package com.shoppingmall.main.home.adapter;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoppingmall.R;
import com.shoppingmall.framework.Variable;
import com.shoppingmall.net.bean.HomeBean;

import java.util.List;

public class SecKillAdapter extends BaseQuickAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean, BaseViewHolder> {
    private ImageView secKillRvImg;
    private TextView secKillText;
    private TextView secKill;

    public SecKillAdapter(@Nullable List<HomeBean.ResultBean.SeckillInfoBean.ListBean> data) {
        super(R.layout.item_sec_kill_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.SeckillInfoBean.ListBean item) {
        secKillRvImg = (ImageView) helper.getView(R.id.secKillRvImg);
        secKillText = (TextView) helper.getView(R.id.secKillText);
        secKill = (TextView) helper.getView(R.id.secKill);

        Glide.with(mContext).load(Variable.IMG_HTTPS+item.getFigure()).into(secKillRvImg);
        secKill.setText("￥"+item.getCover_price());
        secKillText.setText("￥"+item.getOrigin_price());
        secKillText.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
    }

}
