package com.shoppingmall.bawei.shoppingmall1809.mvvm;

import android.widget.ImageView;

import com.fiannce.bawei.framework.manager.ShopmallGlide;
import com.fiannce.bawei.net.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

public class City extends BaseObservable {

    private String name;
    private String province;
    @Bindable
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(com.shoppingmall.bawei.shoppingmall1809.BR.imageUrl);
    }

    //注解的名字是布局imageView的自定义属性性
    //方法是静态返回为空的方法，且里面两个参数，第一个参数是使用这个注解名字的控件类型，第二参数是属性使用的实例变量的类型
    @BindingAdapter("imageCity")
    public static void setImg(ImageView imageView,String imageCityUrl) {
        ShopmallGlide.with(imageView.getContext()).load(imageCityUrl).into(imageView);
    }
}
