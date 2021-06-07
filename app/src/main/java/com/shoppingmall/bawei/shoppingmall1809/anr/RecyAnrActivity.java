package com.shoppingmall.bawei.shoppingmall1809.anr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.shoppingmall.bawei.shoppingmall1809.R;

import java.util.ArrayList;
import java.util.List;

public class RecyAnrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_anr);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AnrAdapter anrAdapter = new AnrAdapter();
        List<String> dataList = new ArrayList<>();
        for(int i = 0;i < 500;i++) {
            String str = (1000+i)+"";
            dataList.add(str);
        }
        recyclerView.setAdapter(anrAdapter);

        anrAdapter.updateData(dataList);
    }
}
