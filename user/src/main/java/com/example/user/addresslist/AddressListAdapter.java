package com.example.user.addresslist;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.framework.db.AddressTable;
import com.example.framework.view.BaseRVAdapter;
import com.example.user.R;

public class AddressListAdapter extends BaseRVAdapter<AddressTable> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_addr_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, AddressTable itemData) {
        TextView name = holder.getView(R.id.addr_name);
        TextView phone = holder.getView(R.id.addr_phone);
        TextView address = holder.getView(R.id.addr_address);
        RadioButton addrDefault = holder.getView(R.id.addr_default);
        TextView remove = holder.getView(R.id.addr_remove);

        name.setText(holder.itemView.getContext().getResources().getString(R.string.consignee)+itemData.getName());
        phone.setText(holder.itemView.getContext().getResources().getString(R.string.phone)+itemData.getPhone());
        address.setText(holder.itemView.getContext().getResources().getString(R.string.detailedAddress)+itemData.getAddress());

        if (itemData.getIsDefault()){
            addrDefault.setChecked(true);
        }else {
            addrDefault.setChecked(false);
        }

        addrDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAddressList.onItemChildClick(position,view);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAddressList.onItemChildClick(position,view);
            }
        });
    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }

    public interface IAddressList{
        void onItemChildClick(int position,View view);
    }

    private IAddressList iAddressList;

    public void setShopItemListener(IAddressList iAddressList) {
        this.iAddressList = iAddressList;
    }
}
