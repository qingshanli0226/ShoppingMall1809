package com.example.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.framework.R;

public abstract class LoadPage extends FrameLayout {
    public LoadPage(@NonNull Context context) {
        this(context, null);
    }

    public LoadPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private View load_layout;
    private View error_layout;
    private View success_layout;
    private TextView textError;

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater from = LayoutInflater.from(context);
        LayoutParams layoutParams = new LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);


        load_layout = from.inflate(R.layout.load_layout, null);
        addView(load_layout, layoutParams);

        error_layout = from.inflate(R.layout.error_layout, null);
        textError = error_layout.findViewById(R.id.error);
        addView(error_layout, layoutParams);

        success_layout = from.inflate(getSuccessLayout(), null);
        addView(success_layout, layoutParams);

        //没有时执行
        showSuccessLayout();
    }

    protected abstract int getSuccessLayout();

    public void showLoadLayout() {
        load_layout.setBackgroundColor(Color.WHITE);
        load_layout.setVisibility(VISIBLE);
        error_layout.setVisibility(GONE);
        success_layout.setVisibility(GONE);
    }

    public void showTransparentLoadLayout() {
        load_layout.setBackgroundColor(Color.TRANSPARENT);
        load_layout.setVisibility(VISIBLE);
        error_layout.setVisibility(GONE);
        success_layout.setVisibility(VISIBLE);
    }
    public void showErrorLayout() {
        load_layout.setVisibility(GONE);
        error_layout.setVisibility(VISIBLE);
        success_layout.setVisibility(GONE);
    }

    public void showSuccessLayout() {
        load_layout.setVisibility(GONE);
        error_layout.setVisibility(GONE);
        success_layout.setVisibility(VISIBLE);
    }
    public void showErrorText(String error){
        showErrorLayout();
        textError.setText(error);
    }
}
