package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framework.R;

public class ToolBar extends RelativeLayout {

    private TextView titleTv;
    private ImageView leftImg;
    private ImageView rightImg;
    private boolean rightAreaIsShow, leftIsShow;
    private String titleText;
    private int rightImgId, leftImgId;
    private IToolbarListener iToolbarListener;
    private String rightText;
    private TextView rightTv;

    public ToolBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        titleText = typedArray.getString(R.styleable.ToolBar_titleText);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_leftImg, 0);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_rightImage, 0);
        leftIsShow = typedArray.getBoolean(R.styleable.ToolBar_leftIsShow, false);
        rightAreaIsShow = typedArray.getBoolean(R.styleable.ToolBar_rightIsShow, false);
        rightText = typedArray.getString(R.styleable.ToolBar_rightText);
        typedArray.recycle();

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_toolbar, this);

        titleTv = findViewById(R.id.titleTv);
        leftImg = findViewById(R.id.leftImg);
        rightImg = findViewById(R.id.rightImg);
        rightTv = findViewById(R.id.rightTv);

        titleTv.setText(titleText);
        rightTv.setText(rightText);

        if (rightAreaIsShow && rightImgId != 0) {
            rightImg.setImageResource(rightImgId);
        }
        if (leftIsShow && leftImgId != 0) {
            leftImg.setImageResource(leftImgId);
        }

        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iToolbarListener != null) {
                    iToolbarListener.onLeftClick();
                }
            }
        });
        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iToolbarListener != null) {
                    iToolbarListener.onRightImgClick();
                }
            }
        });

        rightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iToolbarListener != null) {
                    iToolbarListener.onRightTvClick();
                }
            }
        });
    }

    public void setToolbarListener(IToolbarListener iToolbarListener) {
        this.iToolbarListener = iToolbarListener;
    }

    public static interface IToolbarListener {
        void onLeftClick();

        void onRightImgClick();

        void onRightTvClick();
    }
}
