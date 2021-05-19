package com.fiannce.bawei.framework.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;


@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MyImageView LQS", "收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("MyImageView LQS", "收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyImageView LQS", "收到Up事件");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("LQS onTouchEvent", "MyImageView 收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("LQS onTouchEvent", "MyImageView 收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("LQS onTouchEvent", "MyImageView 收到Up事件");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
