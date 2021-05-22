package com.example.shoppingmall1809.main.type.category.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.framework.view.BaseRVAdapter;
import com.example.framework.view.ShopmallGlide;
import com.example.net.model.CategoryBean;
import com.example.shoppingmall1809.R;

import java.util.List;

public class CUseAdapter extends BaseRVAdapter<CategoryBean.ResultBean.ChildBean> {

    public CUseAdapter(List<CategoryBean.ResultBean.ChildBean> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.type_item_use_item;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, CategoryBean.ResultBean.ChildBean itemData) {
        ImageView figure = holder.getView(R.id.pic);
        TextView coverPrice = holder.getView(R.id.name);
//        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getPic()).placeholder(R.drawable.new_img_loading_1).into(figure);
        ShopmallGlide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getPic()).into(figure);
        coverPrice.setText(itemData.getName());
    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }
}
