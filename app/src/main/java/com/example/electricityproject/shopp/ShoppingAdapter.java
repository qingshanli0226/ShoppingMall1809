package com.example.electricityproject.shopp;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.example.common.Constants.BASE_URl_IMAGE;

public
class ShoppingAdapter extends BaseQuickAdapter<ShortcartProductBean.ResultBean, BaseViewHolder> {


    public ShoppingAdapter(@Nullable List<ShortcartProductBean.ResultBean> data) {
        super(R.layout.item_buy_car, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ShortcartProductBean.ResultBean resultBean) {
        TextView shop_name = baseViewHolder.getView(R.id.shop_name);
        shop_name.setText(resultBean.getProductName()+"");
        TextView shop_money = baseViewHolder.getView(R.id.text_money);
        shop_money.setText("￥"+resultBean.getProductPrice());
        TextView shop_product_num = baseViewHolder.getView(R.id.product_num);
        shop_product_num.setText(resultBean.getProductNum()+"");
        ImageView shop_img = baseViewHolder.getView(R.id.is_select);
        shop_img.setImageDrawable(baseViewHolder.itemView.getContext().getDrawable(R.drawable.checkbox_unselected));

        Glide.with(baseViewHolder.itemView.getContext()).load(BASE_URl_IMAGE+resultBean.getUrl()).placeholder(R.drawable.new_img_loading_1).into((ImageView) baseViewHolder.getView(R.id.shop_image));

        if (resultBean.isProductSelected()){
            shop_img.setImageDrawable(baseViewHolder.itemView.getContext().getDrawable(R.drawable.checkbox_selected));
        }else {
            shop_img.setImageDrawable(baseViewHolder.itemView.getContext().getDrawable(R.drawable.checkbox_unselected));
        }
    }
}
