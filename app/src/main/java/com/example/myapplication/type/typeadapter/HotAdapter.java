package com.example.myapplication.type.typeadapter;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.SkirtBean;

public class HotAdapter extends BaseRecyclerViewAdapter<SkirtBean.ResultBean.HotProductListBean> {


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_hot_view;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, SkirtBean.ResultBean.HotProductListBean itemData) {
        Log.i("zyhaaa", "displayViewHolder: "+itemData);
        TextView view = holder.getView(R.id.money);
        view.setText(itemData.getCover_price()+"");
        Log.i("zyh", "displayViewHolder: "+itemData);
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getFigure()).into((ImageView) holder.getView(R.id.img));

    }




    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
