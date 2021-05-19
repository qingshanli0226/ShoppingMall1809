package com.example.electricityproject.classify.kind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.electricityproject.R;

import java.util.List;

public
class KindAdapter extends ArrayAdapter<KindBean> {

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

        return view;
    }
}
