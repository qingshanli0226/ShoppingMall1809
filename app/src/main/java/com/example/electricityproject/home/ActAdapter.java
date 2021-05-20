package com.example.electricityproject.home;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.base.BaseAdapter;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;

public class ActAdapter extends BaseAdapter<HomeBean.ResultBean.ActInfoBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_act_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, HomeBean.ResultBean.ActInfoBean itemData) {
        ImageView img = baseViewHolder.getView(R.id.act_img);

        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemData.getIcon_url()).into(img);
    }

    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
