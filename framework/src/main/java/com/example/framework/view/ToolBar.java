package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framework.R;

public class ToolBar extends RelativeLayout {
    private ImageView toolbarLeftImg;
    private TextView toolbarCenterTitle;
    private ImageView toolbarRightImg;
    private TextView toolbarRightTitle;

    private boolean istoolbarLeftImg = true;
    private boolean istoolbarRightImg = true;
    private String CenterTitle = "";
    private String RightTitle = "";
    private int leftImgId;
    private int rightImgId;



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
        istoolbarLeftImg = typedArray.getBoolean(R.styleable.ToolBar_wp_istoolbarLeftImg, false);
        istoolbarRightImg = typedArray.getBoolean(R.styleable.ToolBar_wp_istoolbarRightImg, false);
        CenterTitle = typedArray.getString(R.styleable.ToolBar_wp_CenterTitle);
        RightTitle=typedArray.getString(R.styleable.ToolBar_wp_RightTitle);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_wp_leftImId, 0);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_wp_rightImgId, 0);
        typedArray.recycle();


        LayoutInflater.from(context).inflate(R.layout.view_toolbar, this);

        toolbarLeftImg = (ImageView) findViewById(R.id.toolbar_left_img);
        toolbarCenterTitle = (TextView) findViewById(R.id.toolbar_center_title);
        toolbarRightTitle=(TextView) findViewById(R.id.toolbar_right_text);
        toolbarRightImg = (ImageView) findViewById(R.id.toolbar_right_img);

        toolbarCenterTitle.setText(CenterTitle);
        toolbarRightTitle.setText(RightTitle);

        if (istoolbarLeftImg && leftImgId != 0)
            toolbarLeftImg.setImageResource(leftImgId);

        if (istoolbarRightImg && rightImgId != 0)
            toolbarRightImg.setImageResource(rightImgId);

        toolbarLeftImg.setOnClickListener(view -> {
            if (toolbarListener!=null) {
                toolbarListener.onLeftImgClick();
            }
        });
        toolbarCenterTitle.setOnClickListener(view -> {
            if (toolbarListener!=null) {
                toolbarListener.onCenterTitleClick();
            }
        });
        toolbarRightTitle.setOnClickListener(view ->{
            if (toolbarListener!=null){
                toolbarListener.onRightTitle();
            }
        });
        toolbarRightImg.setOnClickListener(view -> {
            if (toolbarListener!=null) {
                toolbarListener.onRightImgClick();
            }
        });

    }

    private IToolbarListener toolbarListener;

    public void setToolbarListener(IToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }

    public static interface IToolbarListener{
        void onLeftImgClick();
        void onCenterTitleClick();
        void onRightImgClick();
        void onRightTitle();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    public void setIstoolbarLeftImg(boolean istoolbarLeftImg) {
        this.istoolbarLeftImg = istoolbarLeftImg;
    }

    public void setIstoolbarRightImg(boolean istoolbarRightImg) {
        this.istoolbarRightImg = istoolbarRightImg;
    }

    public void setCenterTitle(String centerTitle) {
        CenterTitle = centerTitle;
        toolbarCenterTitle.setText(CenterTitle);
    }

    public void setLeftImgId(int leftImgId) {
        this.leftImgId = leftImgId;
    }

    public void setRightImgId(int rightImgId) {
        this.rightImgId = rightImgId;
    }

    public void setRightTitle(String rightTitle) {
        this.RightTitle = rightTitle;
        toolbarRightTitle.setText(RightTitle);
       invalidate();
    }
}
