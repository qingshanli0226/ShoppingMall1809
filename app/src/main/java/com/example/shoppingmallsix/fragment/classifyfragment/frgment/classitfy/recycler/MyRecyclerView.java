package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;

public class MyRecyclerView extends RecyclerView {
    private int   lofitY;
    private int   lofitX;
    public MyRecyclerView(@NonNull Context context) {
        super(context, null, 0);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs,defStyle);
    }

    public void init(Context context, AttributeSet attrs, int defStyle) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                lofitX = (int) e.getX();
                lofitY = (int) e.getY();
            case MotionEvent.ACTION_MOVE:
                int y1 = (int) getY();
                if (lofitY!=y1){
                    return false;
                }else {
                    return true;
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

}


