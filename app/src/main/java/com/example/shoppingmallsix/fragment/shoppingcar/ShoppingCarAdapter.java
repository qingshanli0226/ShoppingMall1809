package com.example.shoppingmallsix.fragment.shoppingcar;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;

import java.util.List;

public class ShoppingCarAdapter extends BaseRvAdapter<GetShortcartProductsBean.ResultBean> {

    private IItemChildClick iItemChildClick;

    public ShoppingCarAdapter(List<GetShortcartProductsBean.ResultBean> datalist) {
        setDataList(datalist);
    }

    public void setItemListener(IItemChildClick iItemChildClick) {
        this.iItemChildClick = iItemChildClick;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shoppingtrolley;
    }


    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, GetShortcartProductsBean.ResultBean itemData) {
        ImageView imageView = holder.getView(R.id.shoppingTrolley_img);
        TextView textView = holder.getView(R.id.shoppingTrolley_text);
        TextView price = holder.getView(R.id.shoppingTrolley_price);
        ImageView sub = holder.getView(R.id.shoppingTrolley_sub);
        ImageView add = holder.getView(R.id.shoppingTrolley_add);
        TextView num = holder.getView(R.id.shoppingTrolley_num);
        CheckBox shoppingTrolleyCheckBox = holder.getView(R.id.shoppingTrolley_CheckBox);
        shoppingTrolleyCheckBox.setChecked(itemData.isProductSelected());
        Glide.with(holder.itemView.getContext())
                .load(Constants.BASE_URl_IMAGE + itemData.getUrl())
                .into(imageView);
        textView.setText(itemData.getProductName());
        price.setText("￥" + itemData.getProductPrice().toString());
        num.setText(itemData.getProductNum());

        shoppingTrolleyCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iItemChildClick != null) {
                    iItemChildClick.onItemChildClick(position, view);
                }
            }
        });
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

    public interface IItemChildClick {
        void onItemChildClick(int position, View view);
    }
}
