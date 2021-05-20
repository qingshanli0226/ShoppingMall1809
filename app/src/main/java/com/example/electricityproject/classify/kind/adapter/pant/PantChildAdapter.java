package com.example.electricityproject.classify.kind.adapter.pant;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.base.BaseAdapter;
import com.example.common.bean.KindJacketBean;
import com.example.common.bean.KindPantsBean;
import com.example.electricityproject.R;

public
class PantChildAdapter extends BaseAdapter<KindPantsBean.ResultBean.ChildBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_kind_child;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, KindPantsBean.ResultBean.ChildBean itemData) {
        TextView view = baseViewHolder.getView(R.id.kind_name);
        view.setText(itemData.getName()+"");
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getPic()).into((ImageView) baseViewHolder.getView(R.id.kind_child_image));
    }

    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
