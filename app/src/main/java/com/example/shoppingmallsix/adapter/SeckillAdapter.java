package com.example.shoppingmallsix.adapter;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;

public class SeckillAdapter extends BaseRvAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_seckill_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.SeckillInfoBean.ListBean itemData) {
        Glide.with(holder.itemView.getContext())
                .load(Constants.BASE_URl_IMAGE + itemData.getFigure())
                .into((ImageView) holder.getView(R.id.seckillImage));

        TextView view = holder.getView(R.id.seckillCover);
        view.setText(itemData.getCover_price());
        TextView view1 = holder.getView(R.id.seckillOrigin);
        view1.setText(itemData.getOrigin_price());
        view1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
