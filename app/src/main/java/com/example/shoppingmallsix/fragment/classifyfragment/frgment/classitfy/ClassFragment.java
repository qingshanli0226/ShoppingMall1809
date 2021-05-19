package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.AccrssoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.ClassLeftBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.OvercoatBean;
import com.example.net.bean.PantsBean;
import com.example.net.bean.ProductsBean;
import com.example.net.bean.StationeryBean;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.adapter.ClassLeftAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends BaseFragment implements IClassView, BaseRvAdapter.IRecyclerItemClickListener {

    private RecyclerView classifyLeftRecyclerView;
    private RecyclerView classifyRightRecyclerView;
    private String[] strings = new String[]{"小裙子","上衣","下装","外套","配件","包包","装扮","居家宅品","办公文具","数据周边","游戏专区"};
    private List<ClassLeftBean> list = new ArrayList<>();
    private ClassLeftAdapter classifyAdapter;
    @Override
    protected void initPresenter() {

    }
    private void inits() {
        initRecyclerView();
    }

    @Override
    protected void initData() {
        //存储list集合
        for (int i = 0; i <strings.length ; i++) {
            ClassLeftBean classLeftBean = new ClassLeftBean();
            if (i == 0){
                classLeftBean.setaBoolean(true);
            }else {
                classLeftBean.setaBoolean(false);
            }
            classLeftBean.setString(strings[i]);
            list.add(classLeftBean);
        }
        inits();
    }

    private void initRecyclerView() {
        //左布局初始化
        classifyAdapter = new ClassLeftAdapter(list);
        classifyAdapter.setiRecyclerItemClickListener(this);
        classifyLeftRecyclerView.setAdapter(classifyAdapter);
        classifyLeftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //右布局初始化

    }

    @Override
    protected void initView() {
        classifyLeftRecyclerView = (RecyclerView) mBaseView.findViewById(R.id.classify_left_recycler_view);
        classifyRightRecyclerView = (RecyclerView) mBaseView.findViewById(R.id.classify_right_recycler_view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class;
    }
    @Override
    public void onItemClick(int position) {
        for (int i = 0; i <list.size() ; i++) {
            if (position == i){
                list.get(i).setaBoolean(true);
            }else {
                list.get(i).setaBoolean(false);
            }
        }
        classifyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItwmLongClick(int position) {

    }




    @Override
    public void onJacketData(JacketBean jacketBean) {

    }

    @Override
    public void onPantsBean(PantsBean pantsBean) {

    }

    @Override
    public void onOvercoatBean(OvercoatBean overcoatBean) {

    }

    @Override
    public void onAccrssoryBean(AccrssoryBean accrssoryBean) {

    }

    @Override
    public void onBagBean(BagBean bagBean) {

    }

    @Override
    public void onDressBean(DressBean dressBean) {

    }

    @Override
    public void onProductsBean(ProductsBean productsBean) {

    }

    @Override
    public void onStationeryBean(StationeryBean stationeryBean) {

    }

    @Override
    public void onDigitBean(DigitBean digitBean) {

    }

    @Override
    public void onGameBean(GameBean gameBean) {

    }



}