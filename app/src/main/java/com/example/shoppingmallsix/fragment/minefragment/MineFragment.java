package com.example.shoppingmallsix.fragment.minefragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.messageactivity.MessageActivity;
import com.example.shoppingmallsix.obligationactivity.ObligationActivity;
import com.example.shoppingmallsix.sendgoodsactivity.SendGoodsActivity;
import com.example.user.login.LoginActivity;

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
<<<<<<< HEAD

=======
        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        if (loginBean != null){

        }else {
            Toast.makeText(getActivity(), getString(R.string.Pleaselogintoyouraccountfirst), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
>>>>>>> t0521
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
