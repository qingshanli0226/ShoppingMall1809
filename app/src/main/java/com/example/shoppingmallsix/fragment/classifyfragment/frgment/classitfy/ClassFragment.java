package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.ClassBean;
import com.example.net.bean.ClassLeftBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter.ClassLeftAdapter;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter.ClassRightAdapter;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.recycler.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends BaseFragment<ClassPresenter> implements IClassView, BaseRvAdapter.IRecyclerItemClickListener {

    private RecyclerView classifyLeftRecyclerView;
    private MyRecyclerView classifyRightRecyclerView;
    private ClassLeftAdapter classifyAdapter;
    private List<ClassLeftBean> list = new ArrayList<>();
    private String[] strings = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL, Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL, Constants.DIGIT_URL, Constants.GAME_URL};

    private List<Object> objectList = new ArrayList<>();
    private ClassRightAdapter<Object> resultBeanClassRightAdapter;
    @Override
    protected void initPresenter() {
        httpPresenter = new ClassPresenter(this);
    }

    private void inits() {
        initRecyclerView();
    }

    @Override
    protected void initData() {
        for (int i = 0; i < strings.length; i++) {
            list.add(new ClassLeftBean());
            httpPresenter.getClassData(strings[i],true);
        }
        httpPresenter.getClassData(strings[0],false);
        inits();
    }

    private void initRecyclerView() {
        //左布局初始化
        classifyAdapter = new ClassLeftAdapter(list);
        classifyAdapter.setiRecyclerItemClickListener(this);
        classifyLeftRecyclerView.setAdapter(classifyAdapter);
        classifyLeftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //右布局初始化
        //Top
        resultBeanClassRightAdapter = new ClassRightAdapter<>();
        classifyRightRecyclerView.setAdapter(resultBeanClassRightAdapter);
        classifyRightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initView() {
        classifyLeftRecyclerView = (RecyclerView) mBaseView.findViewById(R.id.classify_left_recycler_view);
        classifyRightRecyclerView = (MyRecyclerView) mBaseView.findViewById(R.id.classify_right_recycler_view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class;
    }

    @Override
    public void onItemClick(int position) {

        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setaBoolean(false);

        }
        list.get(position).setaBoolean(true);
        httpPresenter.getClassData(strings[position],false);
        classifyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItwmLongClick(int position) {

    }




    @Override
    public void showLoading() {
        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        loadingPage.showError(msg);
    }

    @Override
    public void onClassData(ClassBean classBean,boolean mBoolean,String url) {
        objectList.clear();
        loadingPage.showSuccessView();
        if (mBoolean){
            for (int i = 0; i <strings.length ; i++) {

                if (url.equals(strings[i])){
                    if (url == strings[0]){
                        list.get(i).setaBoolean(true);
                    }else {
                        list.get(i).setaBoolean(false);
                    }
                    list.get(i).setString(classBean.getResult().get(0).getName());
                }
            }

            classifyAdapter.notifyDataSetChanged();
        }else {
            List<ClassBean.ResultBean> result = classBean.getResult();
            objectList.addAll(result);
            resultBeanClassRightAdapter.setUpdateData(objectList,getActivity());
        }

    }
}