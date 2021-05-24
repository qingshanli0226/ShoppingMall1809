package com.example.electricityproject.shopp;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adapter.BaseAdapter;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;

import static com.example.common.Constants.BASE_URl_IMAGE;

public class ShoppingAdapter extends BaseAdapter<ShortcartProductBean.ResultBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_buy_car;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, ShortcartProductBean.ResultBean itemData) {
        TextView shop_name = baseViewHolder.getView(R.id.shop_name);
        shop_name.setText(itemData.getProductName()+"");
        TextView shop_money = baseViewHolder.getView(R.id.text_money);
        shop_money.setText("￥"+itemData.getProductPrice());
        TextView shop_product_num = baseViewHolder.getView(R.id.product_num);
        shop_product_num.setText(itemData.getProductNum()+"");
        ImageView shop_img = baseViewHolder.getView(R.id.is_select);
        shop_img.setImageDrawable(baseViewHolder.itemView.getContext().getDrawable(R.drawable.checkbox_unselected));

        Glide.with(baseViewHolder.itemView.getContext()).load(BASE_URl_IMAGE+itemData.getUrl()).placeholder(R.drawable.new_img_loading_1).into((ImageView) baseViewHolder.getView(R.id.shop_image));

        ImageView img = baseViewHolder.getView(R.id.is_select);

        if (itemData.isAll()){
            img.setImageResource(R.drawable.checkbox_selected);
        }else {
            img.setImageResource(R.drawable.checkbox_unselected);
        }



    }

    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
