package com.example.threeshopping.type.typechild;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.threeshopping.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment {


    private ListView classList;
    private RecyclerView classRv;
    private ArrayAdapter<String> stringArrayAdapter;
    private List<String> title;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        classList = (ListView) findViewById(R.id.classList);
        classRv = (RecyclerView) findViewById(R.id.classRv);
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        title = new ArrayList<>();
        title.add("小裙子");
        title.add("上衣");
        title.add("下装");
        title.add("小外套");
        title.add("配件");
        title.add("包包");
        title.add("装扮");
        title.add("居家宅品");
        title.add("办公文具");
        title.add("数码周边");
        title.add("游戏专区");

        stringArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,title);
        classList.setAdapter(stringArrayAdapter);
        classList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "000", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }
}
