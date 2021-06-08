package com.example.threeshopping.addrmanager.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.LogUtil;
import com.example.framework.BasePresenter;
import com.example.framework.BaseRvAdapter;
import com.example.threeshopping.R;
import com.fiannce.sql.bean.AddrBean;

public class AddrAdapter extends BaseRvAdapter<AddrBean> {

    private IAddrListener addrListener;

    public void setAddrListener(IAddrListener addrListener) {
        this.addrListener = addrListener;
    }

    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_addr_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, AddrBean itemView) {
        TextView itemAddrDefault = holder.getView(R.id.itemAddrDefault);
        TextView itemAddrTitle = holder.getView(R.id.itemAddrTitle);
        TextView itemAddrName = holder.getView(R.id.itemAddrName);
        TextView itemAddrPhone = holder.getView(R.id.itemAddrPhone);
        ImageView itemAddrEdit = holder.getView(R.id.itemAddrEdit);

        if (itemView.getIsDefault()) {
            itemAddrDefault.setVisibility(View.VISIBLE);
        } else{
            itemAddrDefault.setVisibility(View.GONE);
        }

        itemAddrTitle.setText(itemView.getAddr());
        itemAddrName.setText(itemView.getName());
        itemAddrPhone.setText(itemView.getPhone());

        itemAddrEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addrListener != null) {
                    addrListener.onEditClick(position);
                }
            }
        });

    }

    public interface IAddrListener{
        void onEditClick(int position);
    }
}
