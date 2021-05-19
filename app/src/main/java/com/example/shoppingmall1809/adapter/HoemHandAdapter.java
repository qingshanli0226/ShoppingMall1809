package com.example.shoppingmall1809.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.net.model.HoemBean;
import com.example.shoppingmall1809.R;

import java.util.List;

public class HoemHandAdapter extends BaseAdapter {

    private Context context;
    private List<HoemBean.ResultBean.ChannelInfoBean> list;

    public HoemHandAdapter(Context context, List<HoemBean.ResultBean.ChannelInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.home_item_hand_item, null);
            viewHolder = new ViewHolder();
            viewHolder.img = view.findViewById(R.id.home_hand_image);
            viewHolder.name = view.findViewById(R.id.home_hand_name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(context)
                .load(Constants.BASE_URl_IMAGE +list.get(i).getImage())
                .placeholder(R.drawable.new_img_loading_1).into(viewHolder.img);
        viewHolder.name.setText(list.get(i).getChannel_name());
        return view;
    }

    private class ViewHolder{
        ImageView img;
        TextView name;
    }
}
