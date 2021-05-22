package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.ClassBean;
import com.example.net.bean.ClassLeftBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;
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
        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + hotProductListBean.getFigure()).into(imageView);
        textView.setText("￥" + hotProductListBean.getCover_price());

    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }

}
