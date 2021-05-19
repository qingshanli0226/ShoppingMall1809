package com.fiannce.bawei.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fiannce.bawei.framework.R;

public abstract class LoadingPage extends FrameLayout {

    private View loadingView;
    private View successView;
    private View errorView;
    private TextView errorTv;

    public LoadingPage(Context context) {
        super(context);
        init(context, null,0);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,0);
    }

    private void init(Context context, AttributeSet attrs, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        errorView = inflater.inflate(R.layout.view_error,null);
        errorTv = errorView.findViewById(R.id.errorTv);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(errorView,layoutParams);
        successView = inflater.inflate(getSuccessLayoutId(), null);
        addView(successView,layoutParams);
        loadingView = inflater.inflate(R.layout.view_loading,null);
        addView(loadingView,layoutParams);
        showSuccessView();
    }

    protected abstract int getSuccessLayoutId();

    public void showLoadingView() {
        errorView.setVisibility(GONE);
        successView.setVisibility(GONE);
        loadingView.setBackgroundColor(Color.WHITE);
        loadingView.setVisibility(VISIBLE);
    }

    public void showErorView() {
        errorView.setVisibility(VISIBLE);
        successView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
    }

    public void showSuccessView() {
        errorView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
    }

    public void showTransparentLoadingView() {
        errorView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        loadingView.setBackgroundColor(Color.TRANSPARENT);
        loadingView.setVisibility(VISIBLE);
    }

    public void showError(String errorInfo) {
        showErorView();
        errorTv.setText(errorInfo);
    }

}
