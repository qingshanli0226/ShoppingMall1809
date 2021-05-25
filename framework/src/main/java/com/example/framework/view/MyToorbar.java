package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framework.R;

public class MyToorbar extends RelativeLayout {
    private IToorbarListener iToorbarListener;
    public MyToorbar(Context context) {
        this(context,null);
    }

    public MyToorbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyToorbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyToorbar);
        String titletext = typedArray.getString(R.styleable.MyToorbar_titletext);
        int leftimg = typedArray.getResourceId(R.styleable.MyToorbar_leftimg, 0);
        int rightimg = typedArray.getResourceId(R.styleable.MyToorbar_rightimg, 0);
        boolean isleft = typedArray.getBoolean(R.styleable.MyToorbar_isleft, false);
        boolean isright = typedArray.getBoolean(R.styleable.MyToorbar_isright, false);
        typedArray.recycle();//回收

        //给控件赋值
        View mytoorbar = LayoutInflater.from(context).inflate(R.layout.toorbarview, this);
        TextView title = mytoorbar.findViewById(R.id.title);
        ImageView left= mytoorbar.findViewById(R.id.leftimg);
        ImageView right  = mytoorbar.findViewById(R.id.rightimg);
        title.setText(titletext);

        if (isleft&&leftimg!=0){
            left.setImageResource(leftimg);
        }

        if (isright&&rightimg!=0){
            right.setImageResource(rightimg);
        }

        left.setOnClickListener(v -> {
            if (iToorbarListener!=null){
                iToorbarListener.onleftClick();
            }
        });

        right.setOnClickListener(v -> {
            if (iToorbarListener!=null){
                iToorbarListener.onrightClick();
            }
        });
    }

    public void setToorbarListener(IToorbarListener iToorbarListener){
        this.iToorbarListener=iToorbarListener;
    }
    //点击回调借口
    public static interface IToorbarListener{
        void onleftClick();
        void onrightClick();
        void ontextClick();
    }
}
