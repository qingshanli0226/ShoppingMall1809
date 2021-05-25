package com.example.myapplication.type;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.type.typeadapter.ClassifyAdapter;
import com.example.net.Constants;
import com.example.net.bean.SkirtBean;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends BaseFragment<SkirtPresenter> implements ISkirtView {

    private RecyclerView lv;
    private RecyclerView rv;
    private ClassifyAdapter classifyAdapter;
    private List<String> list=new ArrayList<>();
    private List<Object> data=new ArrayList<>();
    private ClassAdapter classAdapter;
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL, Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL, Constants.DIGIT_URL, Constants.GAME_URL};


    @Override
    public int bandLayout() {
        return R.layout.fragment_type;
    }

    @Override
    public void initView() {

        lv = (RecyclerView) findViewById(R.id.lv);
        rv = (RecyclerView) findViewById(R.id.rv);
       classifyAdapter=new ClassifyAdapter();
       classAdapter= new ClassAdapter();

    }

    @Override
    public void initPresenter() {
         mPresenter =new SkirtPresenter(this);

         list.add(getString(R.string.skirt));
         list.add(getString(R.string.jacket));
         list.add(getString(R.string.pants));
         list.add(getString(R.string.over));
         list.add(getString(R.string.access));
         list.add(getString(R.string.bag));
         list.add(getString(R.string.dress));
         list.add(getString(R.string.homepro));
         list.add(getString(R.string.stati));
         list.add(getString(R.string.digit));
         list.add(getString(R.string.game));
        classAdapter.updataData(list);
        mPresenter.onSkirt(urls[0]);

    }

    @Override
    public void initData() {
        rv.setAdapter(classifyAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        lv.setAdapter(classAdapter);
        lv.setLayoutManager(new LinearLayoutManager(getContext()));

        classAdapter.setRecyclerItemClickListener(new BaseRecyclerViewAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mPresenter.onSkirt(urls[position]);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }

    @Override
    public void onSkirt(SkirtBean skirtBean) {
        data.clear();
        List<SkirtBean.ResultBean> result = skirtBean.getResult();
        List<SkirtBean.ResultBean.ChildBean> child = result.get(0).getChild();
        List<SkirtBean.ResultBean.HotProductListBean> hot_product_list = result.get(0).getHot_product_list();
        data.add(hot_product_list);
        data.add(child);
        classifyAdapter.updataData(data);


    }


}