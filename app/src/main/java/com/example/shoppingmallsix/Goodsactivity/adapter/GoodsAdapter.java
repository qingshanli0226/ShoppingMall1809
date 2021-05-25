package com.example.shoppingmallsix.Goodsactivity.adapter;

import android.graphics.Color;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.store.GoodAdapterBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;

import java.util.List;

public class GoodsAdapter extends BaseRvAdapter<GoodAdapterBean> {
    private WebView goodsAdapterItemWebView;
    private TextView goodsAdapterItemTextName;
    private TextView goodsAdapterItemTextPrice;
    private int mType;
    public GoodsAdapter(List<GoodAdapterBean> list) {
        setDataList(list);
    }

    @Override
    public int getLayoutId(int viewType) {
        switch (viewType){
            case 0:
               mType =   R.layout.goods_adapter_item_top;
               break;
            case 1:
                mType = R.layout.goods_adapter_item_button;
        }


        return mType;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, GoodAdapterBean itemData) {
        switch (position){
            case 0:
                goodsAdapterItemWebView = (WebView) holder.getView(R.id.goods_adapter_item_web_view);
                goodsAdapterItemTextName = (TextView) holder.getView(R.id.goods_adapter_item_text_name);
                goodsAdapterItemTextPrice = (TextView) holder.getView(R.id.goods_adapter_item_text_price);
                goodsAdapterItemWebView.loadUrl(Constants.BASE_URl_IMAGE+itemData.getFigure());

                goodsAdapterItemTextName.setText(itemData.getName());
                goodsAdapterItemTextPrice.setText(holder.itemView.getResources().getString(R.string.bi)+itemData.getPrice());
                goodsAdapterItemTextPrice.setTextColor(Color.parseColor("#ff0000"));
                break;
            case 1:
                break;

        }


    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }

}
