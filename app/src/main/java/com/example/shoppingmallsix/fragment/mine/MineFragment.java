package com.example.shoppingmallsix.fragment.mine;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.message.MessageActivity;
import com.example.shoppingmallsix.obligation.ObligationActivity;
import com.example.shoppingmallsix.sendgoods.SendGoodsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {
    private ToolBar toolbar;
    private LinearLayout obligation;
    private LinearLayout sendgoods;
    private ImageView messageImg;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {


        toolbar = (ToolBar) mBaseView.findViewById(R.id.toolbar);
        obligation = (LinearLayout) mBaseView.findViewById(R.id.obligation);
        sendgoods = (LinearLayout) mBaseView.findViewById(R.id.sendgoods);
        messageImg = mBaseView.findViewById(R.id.messageImg);

        messageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });

        obligation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ObligationActivity.class);
                startActivity(intent);
            }
        });

        sendgoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SendGoodsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
