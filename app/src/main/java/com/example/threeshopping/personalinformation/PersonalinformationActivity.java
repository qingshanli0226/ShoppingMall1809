package com.example.threeshopping.personalinformation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.threeshopping.R;

public class PersonalinformationActivity extends BaseActivity {


    private com.example.framework.view.ToolBar toolbar;
    private android.widget.ImageView portrait;
    private android.widget.TextView name;
    private android.widget.TextView nickname;
    View view;
    private Button camera;
    private Button album;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personalinformation;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        portrait = (ImageView) findViewById(R.id.portrait);
        name = (TextView) findViewById(R.id.name);
        nickname = (TextView) findViewById(R.id.nickname);


         view = LayoutInflater.from(this).inflate(R.layout.portrait_layout, null);


        camera = (Button) view.findViewById(R.id.camera);
        album = (Button) view.findViewById(R.id.album);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow(PersonalinformationActivity.this);
                popupWindow.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
                popupWindow.setContentView(view);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,666);
                    }
                });
                album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent,111);

                    }
                });
                popupWindow.showAsDropDown(toolbar,0,1000);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i("相机"+requestCode);
        if(requestCode == 111 && resultCode == Activity.RESULT_OK){
            Uri data1 = data.getData();
            Glide.with(this).load(data1).transform(new CircleCrop()).into(portrait);
//            portrait.setImageURI(data1);
        } else if(requestCode == 666 ){
            Toast.makeText(this, "完成", Toast.LENGTH_SHORT).show();
            Bitmap data1 = (Bitmap) data.getExtras().get("data");
        }
    }
}
