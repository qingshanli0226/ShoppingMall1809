package com.example.myapplication.classify;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseFragment;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.typeadapter.ClassifyAdapter;
import com.example.net.bean.AccessoryBean;
import com.example.net.bean.BagBean;
import com.example.net.bean.DigitBean;
import com.example.net.bean.DressBean;
import com.example.net.bean.GameBean;
import com.example.net.bean.HomeProductBean;
import com.example.net.bean.JacketBean;
import com.example.net.bean.Overconat;
import com.example.net.bean.PantsBean;
import com.example.net.bean.SkirtBean;
import com.example.net.bean.StationeryBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class TypeFragment extends BaseFragment<SkirtPresenter> implements ISkirtView {

    private RecyclerView lv;
    private RecyclerView rv;
    private ClassifyAdapter classifyAdapter;
    private List<String> list=new ArrayList<>();
    private ClassAdapter classAdapter;



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
         rootPresenter=new SkirtPresenter(this);
         rootPresenter.onSkirt();
         rootPresenter.onJack();
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
         list.add("小裙子");
        classAdapter.updataData(list);


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
                if (position==0){
                    rootPresenter.onSkirt();
                }
                if (position==1){
                    rootPresenter.onJack();
                }
                if (position==2){
                    rootPresenter.onpatns();
                }
                if (position==3){
                    rootPresenter.onOver();
                }
                if (position==4){
                    rootPresenter.onAccess();
                }if (position==5){
                    rootPresenter.onBag();
                }if (position==6){
                    rootPresenter.onDress();
                }if (position==7){
                     rootPresenter.onHomeP();
                }if (position==8){
                     rootPresenter.onStari();
                }if (position==9){
                     rootPresenter.onDigit();
                }if (position==10){
                     rootPresenter.onGame();
                }
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }

    @Override
    public void onSkirt(SkirtBean skirtBean) {

        LogUtils.json(skirtBean);
        classifyAdapter.setupdata(skirtBean.getResult(),getContext(),ClassifyAdapter.SKIRT_URL);


    }

    @Override
    public void onJack(JacketBean jacketBean) {
        classifyAdapter.setupdata(jacketBean.getResult(),getContext(),ClassifyAdapter.JACKET_URL);



    }

    @Override
    public void onPants(PantsBean pantsBean) {
        classifyAdapter.setupdata(pantsBean.getResult(),getContext(),ClassifyAdapter.PANTS_URL);
    }

    @Override
    public void onOver(Overconat overconat) {
        classifyAdapter.setupdata(overconat.getResult(),getContext(),ClassifyAdapter.OVERCOAT_URL);

    }

    @Override
    public void onAccess(AccessoryBean accessoryBean) {
        classifyAdapter.setupdata(accessoryBean.getResult(),getContext(),ClassifyAdapter.ACCESSORY_URL);
    }

    @Override
    public void onBagurl(BagBean bagBean) {
        classifyAdapter.setupdata(bagBean.getResult(),getContext(),ClassifyAdapter.BAG_URL);
    }

    @Override
    public void onDress(DressBean dressBean) {
        classifyAdapter.setupdata(dressBean.getResult(),getContext(),ClassifyAdapter.DRESS_UP_URL);
    }

    @Override
    public void onHomee(HomeProductBean homeProductBean) {
        classifyAdapter.setupdata(homeProductBean.getResult(),getContext(),ClassifyAdapter.HOME_PRODUCTS_URL);
    }

    @Override
    public void onStart(StationeryBean stationeryBean) {
        classifyAdapter.setupdata(stationeryBean.getResult(),getContext(),ClassifyAdapter.STATIONERY_URL);
    }

    @Override
    public void onDigit(DigitBean digitBean) {
        classifyAdapter.setupdata(digitBean.getResult(),getContext(),ClassifyAdapter.DIGIT_URL);
    }

    @Override
    public void onGame(GameBean gameBean) {
        classifyAdapter.setupdata(gameBean.getResult(),getContext(),ClassifyAdapter.GAME_URL);
    }


}