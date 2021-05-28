package com.example.shoppingmallsix.fragment.classify.frgment.classitfy.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.net.bean.classify.ClassBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;

import java.util.List;

public class ClassRightBottomAdapter<T> extends BaseRvAdapter<T> {

    public ClassRightBottomAdapter(List<T> strings) {
        setDataList(strings);
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_right_button_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {

        ImageView imageView = holder.getView(R.id.classify_right_button_item_iv);
        TextView textView = holder.getView(R.id.classify_right_button_item_tv);

        ClassBean.ResultBean.ChildBean childBean = (ClassBean.ResultBean.ChildBean) itemData;
        ShopmallGlide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + childBean.getPic()).into(imageView);
        textView.setText(childBean.getName());

    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }

}
