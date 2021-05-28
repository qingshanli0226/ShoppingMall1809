package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

public class Student {
    public ObservableField<String> score = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> classRoom = new ObservableField<>();
    private String imageUrl;

    public void setScore(String scroeValue) {
        score.set(scroeValue);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("imagepath")
    public static void getImg(ImageView view, String imageUrl) {
        Log.d("LQS", imageUrl);
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}
