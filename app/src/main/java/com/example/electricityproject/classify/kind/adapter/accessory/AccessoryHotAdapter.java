package com.example.electricityproject.classify.kind.adapter.accessory;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.base.BaseAdapter;
import com.example.common.bean.KindAccessoryBean;
import com.example.common.bean.KindJacketBean;
import com.example.electricityproject.R;

public class AccessoryHotAdapter extends BaseAdapter<KindAccessoryBean.ResultBean.HotProductListBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_kind_hot;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, KindAccessoryBean.ResultBean.HotProductListBean itemData) {
        Log.i("zzz", "displayViewHolder: "+itemData.toString());

        TextView view = baseViewHolder.getView(R.id.kind_money);
        view.setText("￥"+itemData.getCover_price());
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getFigure()).into((ImageView) baseViewHolder.getView(R.id.kind_image));
    }


    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
