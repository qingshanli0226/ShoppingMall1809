package com.example.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.framework.R;

public abstract class LoadingPage extends FrameLayout {

    private View loadingView;
    private View successView;
    private View errorView;
    private TextView errorTv;

    public LoadingPage(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater inflater = LayoutInflater.from(context);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        errorView = inflater.inflate(R.layout.view_error, null);
        errorTv = errorView.findViewById(R.id.errorTv);
        addView(errorView, layoutParams);

        loadingView = inflater.inflate(R.layout.view_loading, null);
        addView(loadingView, layoutParams);

        successView = inflater.inflate(getSucessLayout(), null);
        addView(successView, layoutParams);

        showSucessView();
    }

    protected abstract int getSucessLayout();


    public void showSucessView() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
    }

    public void showLoadingView() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        successView.setVisibility(GONE);
    }

    public void showTransparencyLoadingView() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        loadingView.setBackgroundColor(Color.TRANSPARENT);
        successView.setVisibility(GONE);
    }

    public void showErrorView(String str) {
        errorView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(GONE);
        errorTv.setText(str + "");
    }


}



