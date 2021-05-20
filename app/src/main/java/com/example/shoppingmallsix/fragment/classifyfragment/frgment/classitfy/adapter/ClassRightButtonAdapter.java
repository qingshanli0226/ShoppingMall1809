package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
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

public class ClassRightButtonAdapter<T> extends BaseRvAdapter<T> {
    private int mTypes;

    public ClassRightButtonAdapter(List<T> strings, int styp) {
        setDataList(strings);
        mTypes = styp;
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_right_button_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {

        ImageView imageView = holder.getView(R.id.classify_right_button_item_iv);
        TextView textView = holder.getView(R.id.classify_right_button_item_tv);
        switch (mTypes) {
            case ClassRightAdapter.SKIRT_TYPE:
                SkirtBean.ResultBean.ChildBean skirtchildBean = (SkirtBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + skirtchildBean.getPic()).into(imageView);
                textView.setText(skirtchildBean.getName());
                break;
            case ClassRightAdapter.JACKET_TYPE:
                JacketBean.ResultBean.ChildBean jacketchildBean = (JacketBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + jacketchildBean.getPic()).into(imageView);
                textView.setText(jacketchildBean.getName());
                break;

            case ClassRightAdapter.PANTS_TYPE:
                PantsBean.ResultBean.ChildBean pantschildBean = (PantsBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + pantschildBean.getPic()).into(imageView);
                textView.setText(pantschildBean.getName());
                break;
            case ClassRightAdapter.OVERCOAT_TYPE:
                OvercoatBean.ResultBean.ChildBean overcoatchildBean = (OvercoatBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + overcoatchildBean.getPic()).into(imageView);
                textView.setText(overcoatchildBean.getName());
                break;
            case ClassRightAdapter.ACCRSSORY_TYPE:
                AccrssoryBean.ResultBean.ChildBean accrssorychildBean = (AccrssoryBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + accrssorychildBean.getPic()).into(imageView);
                textView.setText(accrssorychildBean.getName());
                break;
            case ClassRightAdapter.BAG_TYPE:
                BagBean.ResultBean.ChildBean bagchildBean = (BagBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + bagchildBean.getPic()).into(imageView);
                textView.setText(bagchildBean.getName());
                break;
            case ClassRightAdapter.DRESS_TYPE:
                DressBean.ResultBean.ChildBean dresschildBean = (DressBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + dresschildBean.getPic()).into(imageView);
                textView.setText(dresschildBean.getName());
                break;
            case ClassRightAdapter.PRODUCTS_TYPE:
                ProductsBean.ResultBean.ChildBean produstschildBean = (ProductsBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + produstschildBean.getPic()).into(imageView);
                textView.setText(produstschildBean.getName());
                break;
            case ClassRightAdapter.STATIONERY_TYPE:
                StationeryBean.ResultBean.ChildBean stationerychildBean = (StationeryBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + stationerychildBean.getPic()).into(imageView);
                textView.setText(stationerychildBean.getName());
                break;
            case ClassRightAdapter.DIGIT_TYPE:
                DigitBean.ResultBean.ChildBean digitchildBean = (DigitBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + digitchildBean.getPic()).into(imageView);
                textView.setText(digitchildBean.getName());
                break;
            case ClassRightAdapter.GAME_TYPE:
                GameBean.ResultBean.ChildBean gamechildBean = (GameBean.ResultBean.ChildBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + gamechildBean.getPic()).into(imageView);
                textView.setText(gamechildBean.getName());
                break;
        }
    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }

}
