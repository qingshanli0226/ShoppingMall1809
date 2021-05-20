package com.example.threeshopping.type.typechild.classify.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.TypeBean;
import com.example.threeshopping.R;

public class ItemClassTuiAdapter extends BaseRvAdapter<TypeBean.ResultBean.ChildBean> {
    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.itme_type_tui_child_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, TypeBean.ResultBean.ChildBean itemView) {
        ImageView typeHotImg = holder.getView(R.id.typeTuiImg);
        TextView typeHotTitle = holder.getView(R.id.typeTuiTitle);
        typeHotTitle.setText(itemView.getName());
        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemView.getPic()).into(typeHotImg);
    }


}
