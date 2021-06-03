package com.example.electricityproject.shopp.userinfo;

import android.widget.CheckBox;
import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.electricityproject.R;
import com.example.electricityproject.shopp.userinfo.infodb.UserInfoTable;

public
class UserInfoAdapter extends BaseAdapter<UserInfoTable> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_userinfo;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, UserInfoTable itemData) {
        TextView name = baseViewHolder.getView(R.id.info_name);
        TextView address = baseViewHolder.getView(R.id.info_address);
        TextView phone = baseViewHolder.getView(R.id.info_phone);
        CheckBox checkBox = baseViewHolder.getView(R.id.is_check);
        name.setText(""+itemData.getName());
        address.setText(""+itemData.getAddress());
        phone.setText(""+itemData.getPhone());

        if (itemData.getIsShow()){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }


    }



    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
