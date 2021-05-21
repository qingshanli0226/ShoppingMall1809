package com.example.shoppingmallsix.fragment.classifyfragment.frgment.lab;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.bean.TabBean;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.frgment.lab.adapter.LabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LabelFragment extends BaseFragment<LabPresenter> implements ILabView {


    private ToolBar toolbar;
    private RecyclerView labReView;
    private List<TabBean.ResultBean> list = new ArrayList<>();
    private LabAdapter labAdapter;
    @Override
    protected void initPresenter() {
        httpPresenter = new LabPresenter(this);
    }

    @Override
    protected void initData() {
        httpPresenter.getLabData();
        labAdapter = new LabAdapter(list);
        labReView.setAdapter(labAdapter);
        labReView.setLayoutManager(new GridLayoutManager(getActivity(),3));
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) mBaseView.findViewById(R.id.toolbar);
        labReView = (RecyclerView) mBaseView.findViewById(R.id.lab_re_view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_label;
    }

    @Override
    public void onTabBean(TabBean tabBean) {
        list.clear();
        List<TabBean.ResultBean> result = tabBean.getResult();
        ListaddColor(result);
        list.addAll(result);
        labAdapter.notifyDataSetChanged();
    }

    private void ListaddColor(List<TabBean.ResultBean> result) {
        result.get(0).setTextcolor("#FE31FE");
        result.get(1).setTextcolor("#AFCA8F");
        result.get(2).setTextcolor("#14AF14");
        result.get(3).setTextcolor("#B4AC3F");
        result.get(4).setTextcolor("#0404FF");
        result.get(5).setTextcolor("#B6AF52");
        result.get(6).setTextcolor("#1A1AFE");
        result.get(7).setTextcolor("#FC74FC");
        result.get(8).setTextcolor("#17CE19");
        result.get(9).setTextcolor("#FD36FD");
        result.get(10).setTextcolor("#BCB558");
        result.get(11).setTextcolor("#02CC02");
        result.get(12).setTextcolor("#0F0FFF");
        result.get(13).setTextcolor("#3D3DFE");
        result.get(14).setTextcolor("#050ADD");
        result.get(15).setTextcolor("#B7B050");
        result.get(16).setTextcolor("#3636E3");
        result.get(17).setTextcolor("#FD27FD");
        result.get(18).setTextcolor("#B3CC95");
        result.get(19).setTextcolor("#B8DCD1");
        result.get(20).setTextcolor("#FD52FD");
        result.get(21).setTextcolor("#B1A93A");
        result.get(22).setTextcolor("#2525E0");
        result.get(23).setTextcolor("#03CD13");
        result.get(24).setTextcolor("#6868E7");
        result.get(25).setTextcolor("#FD44FD");
        result.get(26).setTextcolor("#27D12B");
        result.get(27).setTextcolor("#1616FE");
        result.get(28).setTextcolor("#2BB52B");
        result.get(29).setTextcolor("#FD5AFD");
    }
}
