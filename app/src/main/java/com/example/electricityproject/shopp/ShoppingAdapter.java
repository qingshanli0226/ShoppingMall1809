package com.example.electricityproject.shopp;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.common.Constants;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;
import com.example.glide.ShopGlide;

public class ShoppingAdapter extends BaseAdapter<ShortcartProductBean.ResultBean> {

    private iChildItemClickListener childItemClickListener;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_buy_car;
    }

    public void setChildItemClickListener(iChildItemClickListener childItemClickListener) {
        this.childItemClickListener = childItemClickListener;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, ShortcartProductBean.ResultBean itemData) {
        TextView shop_name = baseViewHolder.getView(R.id.shop_name);
        TextView shop_money = baseViewHolder.getView(R.id.text_money);
        TextView shop_product_num = baseViewHolder.getView(R.id.product_num);
        ImageView selectImg = baseViewHolder.itemView.findViewById(R.id.is_select);
        ImageView add = baseViewHolder.getView(R.id.image_add);
        ImageView sub = baseViewHolder.getView(R.id.image_sub);

        shop_name.setText(itemData.getProductName() + "");
        shop_money.setText("￥" + itemData.getProductPrice());
        shop_product_num.setText(itemData.getProductNum() + "");
        selectImg.setImageDrawable(baseViewHolder.itemView.getContext().getDrawable(R.drawable.checkbox_unselected));
//        ShopGlide.getInstance().with(baseViewHolder.itemView.getContext()).load(BASE_URl_IMAGE + itemData.getUrl()).init((ImageView) baseViewHolder.getView(R.id.shop_image));
        Log.i("zx", "displayViewHolder: "+Constants.BASE_URl_IMAGE + itemData.getUrl());
        ShopGlide.getInstance().with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + itemData.getUrl()).init((ImageView) baseViewHolder.getView(R.id.shop_image));

        ImageView img = baseViewHolder.getView(R.id.is_select);

        if (itemData.isAll()) {
            img.setImageResource(R.drawable.checkbox_selected);
        } else {
            img.setImageResource(R.drawable.checkbox_unselected);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childItemClickListener != null) {
                    childItemClickListener.OnChildItemListener(v, position);
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childItemClickListener != null) {
                    childItemClickListener.OnChildItemListener(v, position);
                }
            }
        });
       selectImg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (childItemClickListener != null) {
                   childItemClickListener.OnChildItemListener(v, position);
               }
           }
       });
}


    @Override
    public int getRootViewType(int position) {
        return 1;
    }


    public interface iChildItemClickListener {
        void OnChildItemListener(View view, int position);
    }
}

