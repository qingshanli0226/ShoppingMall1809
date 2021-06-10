package com.example.threeshopping.personalinformation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.common.LogUtil;
import com.example.common.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.EventBean;
import com.example.net.bean.LoginBean;
import com.example.threeshopping.MainActivity;
import com.example.threeshopping.R;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalinformationActivity extends BaseActivity {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.ImageView portrait;
    private android.widget.TextView username;
    private android.widget.TextView nickname;
    View view;
    private Button camera;
    private Button album;
    private String path = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_personalinformation;
    }

    @Override
    public void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        portrait = (ImageView) findViewById(R.id.portrait);
        username = (TextView) findViewById(R.id.username);
        nickname = (TextView) findViewById(R.id.nickname);

        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        LoginBean.ResultBean result = loginBean.getResult();
        String name = result.getName();
        if (loginBean != null) {
            nickname.setText("" + name);
            username.setText("" + name);
        }
        view = LayoutInflater.from(this).inflate(R.layout.portrait_layout, null);
        camera = (Button) view.findViewById(R.id.camera);
        album = (Button) view.findViewById(R.id.album);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

        String getpath = SpUtil.getpath(this);
        Glide.with(this).load(getpath).transform(new CircleCrop()).into(portrait);

        portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow(PersonalinformationActivity.this);
                popupWindow.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
                popupWindow.setContentView(view);
                popupWindow.setOutsideTouchable(true);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        path = "/sdcard/DCIM/Camera" + capturename();
                        Uri uriForFile = FileProvider.getUriForFile(PersonalinformationActivity.this, "com.example.threeshopping", new File(path));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                        startActivityForResult(intent, 101);
                    }
                });
                album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 111);

                    }
                });
                popupWindow.showAsDropDown(toolbar, 0, 1000);
            }
        });
    }

    public String capturename() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd hhmmss");
        String format = simpleDateFormat.format(date);
        return "IMG_" + format + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i("相机" + requestCode);
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            Uri data1 = data.getData();
            Glide.with(this).load(data1).transform(new CircleCrop()).into(portrait);
            SpUtil.putpath(this, data1 + "");
        } else if (requestCode == 101 && resultCode == Activity.RESULT_OK) {

            Glide.with(this).load(path).transform(new CircleCrop()).into(portrait);

            SpUtil.putpath(this, path);

            EventBus.getDefault().post(""+path);

        }
    }
}
