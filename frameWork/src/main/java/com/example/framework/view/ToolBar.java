package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framework.R;

public class ToolBar extends RelativeLayout {
    private String text;
    private int textColor = Color.RED, backColor = Color.GRAY;
    private int leftSrc, rightSrc;
    private boolean leftIsshow, rightIsShow;

    private IToolbarOnClickLisenter toolbarOnClickLisenter;

    public void setToolbarOnClickLisenter(IToolbarOnClickLisenter iToolbarOnClickLisenter) {
        this.toolbarOnClickLisenter = iToolbarOnClickLisenter;
    }

    public ToolBar(Context context) {
        this(context, null);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private ImageView leftRe;
    private ImageView rightSet;
    private TextView centerTitle;
    private RelativeLayout toolbarBack;

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this);
        leftRe = inflate.findViewById(R.id.left_re);
        rightSet = inflate.findViewById(R.id.right_set);
        centerTitle = inflate.findViewById(R.id.center_title);
        toolbarBack = inflate.findViewById(R.id.toolbar_back);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
            text = (String) typedArray.getText(R.styleable.ToolBar_app_text);
            textColor = typedArray.getColor(R.styleable.ToolBar_app_text_color, textColor);
            backColor = typedArray.getColor(R.styleable.ToolBar_app_back_color_toob, backColor);
            leftSrc = typedArray.getResourceId(R.styleable.ToolBar_app_left_src, 0);
            rightSrc = typedArray.getResourceId(R.styleable.ToolBar_app_right_src, 0);
            leftIsshow = typedArray.getBoolean(R.styleable.ToolBar_app_left_isshow, leftIsshow);
            rightIsShow = typedArray.getBoolean(R.styleable.ToolBar_app_right_isshow, rightIsShow);
            typedArray.recycle();
        }

        centerTitle.setText(text);
        centerTitle.setTextColor(textColor);
        toolbarBack.setBackgroundColor(backColor);

        if (leftSrc != 0 && leftIsshow) {
            leftRe.setImageResource(leftSrc);
        }
        if (rightSrc != 0 && rightIsShow) {
            rightSet.setImageResource(rightSrc);
        }

        leftRe.setOnClickListener(v -> {
            if (toolbarOnClickLisenter != null) {
                toolbarOnClickLisenter.onClickLeft();
            }
        });
        rightSet.setOnClickListener(v -> {
            if (toolbarOnClickLisenter != null) {
                toolbarOnClickLisenter.onClickRight();
            }
        });
        centerTitle.setOnClickListener(v -> {
            if (toolbarOnClickLisenter != null) {
                toolbarOnClickLisenter.onClickCenter();
            }
        });
    }

    public void setText(String text) {
        if (centerTitle != null) {
            centerTitle.setText(text);
        }
    }

    public void setTextColor(int color) {
        if (centerTitle != null) {
            centerTitle.setTextColor(textColor);
        }
    }

    public void setBackgroundColor(int color){
        if (toolbarBack != null) {
            toolbarBack.setBackgroundColor(backColor);
        }
    }

    public void setLeftSrc(int src){
        if (leftRe != null) {
            leftRe.setImageResource(src);
        }

    }
    public void setRightSrc(int src){
        if (rightSet != null) {
            rightSet.setImageResource(src);

        }
    }
    public void setleftIsshow(boolean isShow){
        if (leftRe != null) {
            if (isShow) {
                leftRe.setVisibility(View.VISIBLE);
            } else {
                leftRe.setVisibility(View.GONE);

            }
        }
    }
    public void setRightIsshow(boolean isShow){
        if (rightSet != null) {
            if (isShow) {
                rightSet.setVisibility(View.VISIBLE);
            } else {
                rightSet.setVisibility(View.GONE);

            }
        }
    }
        public static interface IToolbarOnClickLisenter {
            void onClickCenter();

            void onClickLeft();

            void onClickRight();
        }

    }
