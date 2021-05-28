package com.example.shoppingmallsix.fragment.classify.frgment.classitfy.adapter;

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

public class ClassRightTopAdapter<T> extends BaseRvAdapter<T> {
    public ClassRightTopAdapter(List<T> strings) {
        setDataList(strings);
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_right_top_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {

        ImageView imageView = holder.getView(R.id.classify_right_top_item_iv);
        TextView textView = holder.getView(R.id.classify_right_top_item_tv);

        ClassBean.ResultBean.HotProductListBean hotProductListBean = (ClassBean.ResultBean.HotProductListBean) itemData;
        ShopmallGlide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + hotProductListBean.getFigure()).into(imageView);
        textView.setText("￥" + hotProductListBean.getCover_price());


    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }

}
