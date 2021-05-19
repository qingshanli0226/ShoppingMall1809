package com.example.myapplication.home.homeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.HomeBean;

public class EndRecAdapter extends BaseRecyclerViewAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.end_item_lay;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.SeckillInfoBean.ListBean itemData) {
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getFigure())
                .into((ImageView) holder.getView(R.id.homeEndItemNowImage));
        TextView nameTv = holder.getView(R.id.homeEndItemNowMoney);
        nameTv.setText(itemData.getCover_price());

        TextView nameTv1 = holder.getView(R.id.homeEndItemoOldMoney);
        nameTv1.setText(itemData.getOrigin_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
