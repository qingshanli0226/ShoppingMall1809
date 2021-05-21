package com.example.myapplication.typeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.Overconat;
import com.example.net.bean.PantsBean;

import java.util.List;

public class HotThressAdapter<T> extends BaseRecyclerViewAdapter<T> {

    public HotThressAdapter(List<T> list){
        updataData(list);
    }
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_hot_view;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {
        Overconat.ResultBean.HotProductListBean listBeans = (Overconat.ResultBean.HotProductListBean) itemData;
        TextView view = holder.getView(R.id.money);
        view.setText(listBeans.getCover_price() + "");
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080" + "/atguigu/img" + listBeans.getFigure()).into((ImageView) holder.getView(R.id.img));
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
