package com.example.electricityproject.classify.kind.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.electricityproject.R;

import java.util.List;

public
class KindAdapter extends ArrayAdapter<KindBean> {

    private int selectedPosition = 0;// 选中的位置
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public KindAdapter(@NonNull Context context, int resource, @NonNull List<KindBean> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        KindBean kindBean = getItem(position);
        //获取子视图控件实例
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_kind,parent,false);
        TextView kindText = (TextView) view.findViewById(R.id.kind_text);
        kindText.setText(kindBean.getText());

        if (selectedPosition == position) {
            view.setBackgroundColor(Color.parseColor("#7E8E99"));
            kindText.setTextColor(Color.RED);
            notifyDataSetChanged();
        } else {
            view.setBackgroundColor(Color.WHITE);
            notifyDataSetChanged();
        }

        return view;
    }
}
