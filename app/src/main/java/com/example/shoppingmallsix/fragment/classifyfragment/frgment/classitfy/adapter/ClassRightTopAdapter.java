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

public class ClassRightTopAdapter<T> extends BaseRvAdapter<T>{
    private int mTypes;
    public ClassRightTopAdapter(List<T> strings,int styp){
        setDataList(strings);
        mTypes=styp;
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_right_top_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, T itemData) {

        ImageView imageView = holder.getView(R.id.classify_right_top_item_iv);
        TextView textView = holder.getView(R.id.classify_right_top_item_tv);
        switch (mTypes){
            case ClassRightAdapter.SKIRT_TYPE:
                SkirtBean.ResultBean.HotProductListBean skirihotProductListBean = (SkirtBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+skirihotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+skirihotProductListBean.getCover_price());
                break;
            case ClassRightAdapter.JACKET_TYPE:
                JacketBean.ResultBean.HotProductListBean jackethotProductListBean = (JacketBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+jackethotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+jackethotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.PANTS_TYPE:
                PantsBean.ResultBean.HotProductListBean pantshotProductListBean = (PantsBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+pantshotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+pantshotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.OVERCOAT_TYPE:
                OvercoatBean.ResultBean.HotProductListBean overcoathotProductListBean = (OvercoatBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+overcoathotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+overcoathotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.ACCRSSORY_TYPE:
                AccrssoryBean.ResultBean.HotProductListBean accrssoryhotProductListBean = (AccrssoryBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+accrssoryhotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+accrssoryhotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.BAG_TYPE:
                BagBean.ResultBean.HotProductListBean baghotProductListBean = (BagBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+baghotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+baghotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.DRESS_TYPE:
                DressBean.ResultBean.HotProductListBean dresshotProductListBean = (DressBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+dresshotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+dresshotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.PRODUCTS_TYPE:
                ProductsBean.ResultBean.HotProductListBean productshotProductListBean = (ProductsBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+productshotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+productshotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.STATIONERY_TYPE:
                StationeryBean.ResultBean.HotProductListBean stationeryhotProductListBean = (StationeryBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+stationeryhotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+stationeryhotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.DIGIT_TYPE:
                DigitBean.ResultBean.HotProductListBean digithotProductListBean = (DigitBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+digithotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+digithotProductListBean.getCover_price());
                break;
                case ClassRightAdapter.GAME_TYPE:
                GameBean.ResultBean.HotProductListBean gamehotProductListBean = (GameBean.ResultBean.HotProductListBean) itemData;
                Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+gamehotProductListBean.getFigure()).into(imageView);
                textView.setText("￥"+gamehotProductListBean.getCover_price());
                break;


        }
    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }

}
