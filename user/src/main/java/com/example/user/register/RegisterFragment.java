package com.example.user.register;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.register.IRegisterView;
import com.example.user.register.RegisPresenter;

import org.greenrobot.eventbus.EventBus;

import com.example.framework.BaseFragment;


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
    }

    @Override
    public void initPresenter() {
        rootPresenter = new RegisPresenter(this);
    }

    @Override
    public void initData() {
        //注册按钮点击
        btn.setOnClickListener(v -> {
            String ename = editname.getText().toString().trim();
            String epwd = editpwd.getText().toString().trim();
            String epwdd = editpwdd.getText().toString();
            if (TextUtils.isEmpty(ename) || TextUtils.isEmpty(epwd) || TextUtils.isEmpty(epwdd)) {
                Toast.makeText(getActivity(), getString(R.string.notNull), Toast.LENGTH_SHORT).show();
            }
            //注册
            rootPresenter.onRegister(ename, epwd);
        });
    }


    @Override
    public void OnRegister(RegisterBean registerBean) {
        String code = registerBean.getCode();
        if (code.equals("200")) {
            Toast.makeText(getActivity(),getString(R.string.registerYes) , Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post("1");
        }else {
            Toast.makeText(getActivity(), registerBean.getMessage(), Toast.LENGTH_SHORT).show();
            editname.setText("");
            editpwd.setText("");
            editpwdd.setText("");
        }
    }
}