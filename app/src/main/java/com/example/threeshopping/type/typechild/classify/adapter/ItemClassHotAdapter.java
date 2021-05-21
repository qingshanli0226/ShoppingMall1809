package com.example.threeshopping.type.typechild.classify.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.net.bean.HomeBean;
import com.example.net.bean.TypeBean;
import com.example.threeshopping.R;

public class ItemClassHotAdapter extends BaseRvAdapter<TypeBean.ResultBean.HotProductListBean> {
    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_type_hot_child_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, TypeBean.ResultBean.HotProductListBean itemView) {
        ImageView typeHotImg = holder.getView(R.id.typeHotImg);
        TextView typeHotTitle = holder.getView(R.id.typeHotTitle);
        typeHotTitle.setText(itemView.getCover_price());
        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemView.getFigure()).into(typeHotImg);
//        ShopmallGlide.getInstance().with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemView.getFigure()).into(typeHotImg);

    }
}
