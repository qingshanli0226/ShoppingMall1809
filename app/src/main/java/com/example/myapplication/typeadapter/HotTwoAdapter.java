package com.example.myapplication.typeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.JacketBean;
import com.example.net.bean.PantsBean;

import java.util.List;

public class HotTwoAdapter<T> extends BaseRecyclerViewAdapter<T> {

    public HotTwoAdapter(List<T> list){
        updataData(list);
    }
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_hot_view;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {
        PantsBean.ResultBean.HotProductListBean listBeans = (PantsBean.ResultBean.HotProductListBean) itemData;
        TextView view = holder.getView(R.id.money);
        view.setText(listBeans.getCover_price() + "");
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080" + "/atguigu/img" + listBeans.getFigure()).into((ImageView) holder.getView(R.id.img));
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
