package com.shoppingmall.main.sort.fragment;

import android.util.SparseArray;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.sort.fragment.adapter.ClassificationAdapter;
import com.shoppingmall.main.sort.fragment.adapter.ClassificationContentAdapter;
import com.shoppingmall.net.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;


public class ClassificationFragment extends BaseFragment {


    private RecyclerView classificationTitleRv;
    private RecyclerView classificationContentRv;
    private ClassificationContentAdapter classificationContentAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_classification;
    }

    @Override
    public void initView() {
        classificationTitleRv = (RecyclerView) mView.findViewById(R.id.classificationTitleRv);
        classificationContentRv = (RecyclerView) mView.findViewById(R.id.classificationContentRv);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        List<String> classificationTitleList = new ArrayList<>();
        classificationTitleList.add(getString(R.string.SKIRT));
        classificationTitleList.add(getString(R.string.JACKET_URL));
        classificationTitleList.add(getString(R.string.PANTS_URL));
        classificationTitleList.add(getString(R.string.OVERCOAT_URL));
        classificationTitleList.add(getString(R.string.ACCESSORY_URL));
        classificationTitleList.add(getString(R.string.BAG_URL));
        classificationTitleList.add(getString(R.string.DRESS_UP_URL));
        classificationTitleList.add(getString(R.string.HOME_PRODUCTS_URL));
        classificationTitleList.add(getString(R.string.STATIONERY_URL));
        classificationTitleList.add(getString(R.string.DIGIT_URL));
        classificationTitleList.add(getString(R.string.GAME_URL));
        ClassificationAdapter classificationAdapter = new ClassificationAdapter();
        classificationTitleRv.setLayoutManager(new LinearLayoutManager(getContext()));
        classificationTitleRv.setAdapter(classificationAdapter);
        classificationAdapter.updateData(classificationTitleList);

        //添加数据
        List<Object> objects = new ArrayList<>();
        objects.add(homeBean.getResult().getRecommend_info());
        objects.add(homeBean.getResult().getHot_info());

        classificationContentAdapter = new ClassificationContentAdapter();
        classificationContentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        classificationContentRv.setAdapter(classificationContentAdapter);
        classificationContentAdapter.updateData(objects);


        classificationAdapter.setRecyclerItemClickListener(new BaseRvAdapter.IRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                classificationAdapter.setPosition(position);
                classificationAdapter.notifyDataSetChanged();

                switch (position){
                    case 0:

                        break;
                    case 1:

                        break;
                }
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }

}