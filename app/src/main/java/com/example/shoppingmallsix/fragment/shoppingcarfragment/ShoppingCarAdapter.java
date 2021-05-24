package com.example.shoppingmallsix.fragment.shoppingcarfragment;

import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;

import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;


import java.util.List;

public class ShoppingCarAdapter extends BaseRvAdapter<GetShortcartProductsBean.ResultBean> {
    public ShoppingCarAdapter(List<GetShortcartProductsBean.ResultBean> datalist) {
        setDataList(datalist);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shoppingtrolley;
    }


    @Override
    public void displayViewHolder(BaseRvAdapter.BaseViewHolder holder, int position, GetShortcartProductsBean.ResultBean itemData) {
        ImageView imageView = holder.getView(R.id.shoppingTrolley_img);
        TextView textView = holder.getView(R.id.shoppingTrolley_text);
        TextView price = holder.getView(R.id.shoppingTrolley_price);
        ImageView sub = holder.getView(R.id.shoppingTrolley_sub);
        ImageView add = holder.getView(R.id.shoppingTrolley_add);
        TextView num = holder.getView(R.id.shoppingTrolley_num);

        Glide.with(holder.itemView.getContext())
                .load(Constants.BASE_URl_IMAGE +itemData.getUrl())
                .into(imageView);
        textView.setText(itemData.getProductName());
        price.setText("￥"+itemData.getProductPrice().toString());
        num.setText(itemData.getProductNum());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
