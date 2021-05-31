package com.example.framework;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class LoadingPage extends FrameLayout {
    private View error,success,loadingview;
    private TextView textView;
    public LoadingPage(@NonNull Context context) {
        this(context,null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater inflate = LayoutInflater.from(context);
         error = inflate.inflate(R.layout.view_error, null);
         textView = error.findViewById(R.id.error);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(error,layoutParams);
         success = inflate.inflate(getSuccessLayoutId(), null);
            addView(success,layoutParams);
         loadingview = inflate.inflate(R.layout.view_loading, null);
        addView(loadingview,layoutParams);
        showSuccessView();
    }


    protected abstract int getSuccessLayoutId();

    public void showLoadingView(){
        error.setVisibility(GONE);
        success.setVisibility(GONE);
        loadingview.setBackgroundColor(Color.WHITE);
        loadingview.setVisibility(VISIBLE);
    }

    public void showErrorView(){
        error.setVisibility(VISIBLE);
        success.setVisibility(GONE);
        loadingview.setVisibility(GONE);
    }

    public void showSuccessView(){
        error.setVisibility(GONE);
        success.setVisibility(VISIBLE);
        loadingview.setVisibility(GONE);
    }

    public void showError(String errorinfo){
        showErrorView();
        textView.setText(errorinfo);
    }
}
