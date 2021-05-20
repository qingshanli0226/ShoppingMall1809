package com.example.threeshopping.type.typechild.classify;


import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.net.bean.TypeBean;
import com.example.threeshopping.R;
import com.example.threeshopping.type.typechild.classify.adapter.ClassAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment<ClassPrensenter> implements IClassView {


    private ListView classList;
    private RecyclerView classRv;
    private ArrayAdapter<String> stringArrayAdapter;
    private List<String> title;
    private long listId = -1;
    private List<Object> typeList = new ArrayList<>();
    private ClassAdapter classAdapter;
    private SparseArray<View> sparseArray = new SparseArray<>();
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
        mPresenter = new ClassPrensenter(this);
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

                TextView textView = (TextView) view;
                textView.setTextColor(Color.RED);

                if (sparseArray.get(position) == null) {
                    sparseArray.put(position,view);
                }
                for (int i = 0; i < sparseArray.size(); i++) {
                    int viewPosition = sparseArray.keyAt(i);
                    Log.i("TAG", "onItemClick: "+viewPosition);
                    if(viewPosition != position){
                        View view1 = sparseArray.get(viewPosition);
                        TextView textView1 = (TextView) view1;
                        textView1.setTextColor(Color.BLACK);
                    }
                }
//                for (int i1 = 0; i1 < sparseArray.size(); i1++) {
//                    int i2 = sparseArray.keyAt(i1);
//                    if (i2 != position) {
//                        View item = viewSparseArray.get(i2);
//                        item.setBackgroundResource(R.color.darkAshen);
//                    }
//                }

                mPresenter.getSkirt(position);
            }
        });


        classAdapter = new ClassAdapter();
        classRv.setAdapter(classAdapter);
        classRv.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    @Override
    public void onType(TypeBean typeBean) {
        typeList.clear();
        typeList.add(typeBean.getResult().get(0).getHot_product_list());
        typeList.add(typeBean.getResult().get(0).getChild());
        classAdapter.updata(typeList);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}
