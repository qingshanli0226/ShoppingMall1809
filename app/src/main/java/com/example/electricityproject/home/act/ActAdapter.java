package com.example.electricityproject.home.act;

import android.widget.ImageView;

import com.example.adapter.BaseAdapter;
import com.example.common.Constants;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;
import com.example.glide.ShopGlide;

public class ActAdapter extends BaseAdapter<HomeBean.ResultBean.ActInfoBean> {


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_act_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, HomeBean.ResultBean.ActInfoBean itemData) {

        ImageView imageView = baseViewHolder.getView(R.id.act_img);
        ShopGlide.getInstance().with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemData.getIcon_url()).init(imageView);

    }

    @Override
    public int getRootViewType(int position) {
        return 1;
    }


}
