package com.example.electricityproject.classify.kind.adapter.dress;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.base.BaseAdapter;
import com.example.common.bean.KindDressBean;
import com.example.common.bean.KindJacketBean;
import com.example.electricityproject.R;

public class DressHotAdapter extends BaseAdapter<KindDressBean.ResultBean.HotProductListBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_kind_hot;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, KindDressBean.ResultBean.HotProductListBean itemData) {
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
