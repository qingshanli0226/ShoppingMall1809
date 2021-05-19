package com.example.user.frag;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.register.IRegisterView;
import com.example.user.register.RegisPresenter;

import org.greenrobot.eventbus.EventBus;

import mvp.view.BaseFragment;


public class RegisterFragment extends BaseFragment<RegisPresenter> implements IRegisterView {

    private EditText editname;
    private EditText editpwd;
    private EditText editpwdd;
    private Button btn;

    @Override
    public int bandLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView() {

        editname = findViewById(R.id.editname);
        editpwd = findViewById(R.id.editpwd);
        editpwdd = findViewById(R.id.editpwdd);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ename = editname.getText().toString().trim();
                String epwd = editpwd.getText().toString().trim();
                String epwdd = editpwdd.getText().toString();
               if (ename!=null&&epwd!=null&&epwd.equals(epwdd)){
                   rootPresenter.onRegister(ename,epwd);
               }
            }
        });
    }

    @Override
    public void initPresenter() {
        rootPresenter = new RegisPresenter(this);

    }

    @Override
    public void initData() {

    }


    @Override
    public void OnRegister(RegisterBean registerBean) {
        Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
        String code = registerBean.getCode();
        if (code.equals("200")){

            EventBus.getDefault().post("2");

            Toast.makeText(getActivity(), "已经发送", Toast.LENGTH_SHORT).show();
        }
    }
}