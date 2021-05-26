package com.example.myapplication.home.homeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.myapplication.R;
import com.example.net.bean.HomeBean;

public class GoodRecAdapter extends BaseRecyclerViewAdapter<HomeBean.ResultBean.HotInfoBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.good_item_lay;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.HotInfoBean itemData) {
        ShopmallGlide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getFigure())
                .into((ImageView) holder.getView(R.id.homeGoodItemImage));
        TextView nameTv = holder.getView(R.id.homeGoodItemName);
        nameTv.setText(itemData.getName());

        TextView nameTv1 = holder.getView(R.id.homeGoodItemMoney);
        nameTv1.setText(itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
