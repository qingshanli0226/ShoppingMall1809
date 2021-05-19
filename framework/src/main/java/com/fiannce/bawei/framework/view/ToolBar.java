package com.fiannce.bawei.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fiannce.bawei.framework.R;


//自定义View组件
public class ToolBar extends RelativeLayout {
    private TextView titleTv;
    private LinearLayout rightArea;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView rightTv;
    private boolean rightAreaIsShow,leftIsShow;
    private String titleText;
    private int rightImgId,leftImgId;

    private IToolbarListener iToolbarListener;

    public ToolBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {

        //获取属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ToolBar);
        titleText = typedArray.getString(R.styleable.ToolBar_titleText);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_leftImg,0);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_rightImage,0);
        rightAreaIsShow = typedArray.getBoolean(R.styleable.ToolBar_rightIsShow,false);
        leftIsShow = typedArray.getBoolean(R.styleable.ToolBar_leftIsShow,false);
        typedArray.recycle();

        //设置布局获取控件
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_toolbar,this);

        titleTv = findViewById(R.id.titleTv);
        rightArea = findViewById(R.id.rightArea);
        leftImg = findViewById(R.id.leftImg);
        rightImg = findViewById(R.id.rightImg);
        rightTv = findViewById(R.id.rightTv);

        //通过属性值来控制控件的的渲染
        titleTv.setText(titleText);
        if (rightAreaIsShow && rightImgId!=0) {
            rightImg.setImageResource(rightImgId);
        }
        if (leftIsShow && leftImgId!=0) {
            leftImg.setImageResource(leftImgId);
        }

        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (iToolbarListener!=null) {
                     iToolbarListener.onLeftClick();
                 }
            }
        });
        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iToolbarListener!=null) {
                    iToolbarListener.onRightImgClick();
                }
            }
        });

        //触摸监听函数，通过它可以获取用户的触摸事件，在这里可以消费事件，如果消费了，onToucheEvent就不会被调用了,无法获取事件了
        rightImg.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("LQS", "OnTouchListener onTouch");
                return false;
            }
        });


    }
    //注册一个回调接口
    public void setToolbarListener(IToolbarListener iToolbarListener) {
        this.iToolbarListener = iToolbarListener;
    }

    public static interface IToolbarListener {
        void onLeftClick();
        void onRightImgClick();
        void onRightTvClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("LQS onTouchEvent", "ToolBar 收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("LQS onTouchEvent", "ToolBar 收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("LQS onTouchEvent", "ToolBar 收到Up事件");
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("ToolBar LQS", "收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ToolBar LQS", "收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ToolBar LQS", "收到Up事件");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
