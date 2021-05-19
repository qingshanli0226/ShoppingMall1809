package com.example.myapplication.home.homeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.HomeBean;

public class NewRecAdapter extends BaseRecyclerViewAdapter<HomeBean.ResultBean.RecommendInfoBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.new_item_lay;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.RecommendInfoBean itemData) {
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getFigure())
                .into((ImageView) holder.getView(R.id.homeNewItemImage));
        TextView nameTv = holder.getView(R.id.homeNewItemName);
        nameTv.setText(itemData.getName());

        TextView nameTv1 = holder.getView(R.id.homeNewItemMoney);
        nameTv1.setText(itemData.getProduct_id());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
