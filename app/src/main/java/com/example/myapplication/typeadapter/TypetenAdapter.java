package com.example.myapplication.typeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.DigitBean;
import com.example.net.bean.GameBean;

import java.util.List;

public class TypetenAdapter<T> extends BaseRecyclerViewAdapter<T> {
    public TypetenAdapter(List<T> list1) {
        updataData(list1);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.type_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {
        GameBean.ResultBean.ChildBean listBeans = (GameBean.ResultBean.ChildBean) itemData;
        TextView view = holder.getView(R.id.text);
        view.setText(listBeans.getName());
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080" + "/atguigu/img" + listBeans.getPic()).into((ImageView) holder.getView(R.id.img));
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
