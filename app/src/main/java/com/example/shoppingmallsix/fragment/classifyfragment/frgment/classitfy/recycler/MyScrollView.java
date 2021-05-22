package com.example.shoppingmallsix.fragment.classifyfragment.frgment.classitfy.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private int   lofitY;
    private int   lofitX;
    public MyScrollView(Context context) {
        super(context, null, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                lofitX = (int) e.getX();
                lofitY = (int) e.getY();
            case MotionEvent.ACTION_MOVE:
                int y1 = (int) getY();
                int x1 = (int) getX();
                if (lofitY!=y1&&x1>200){
                    return true;
                }else {
                    return false;
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }


}
