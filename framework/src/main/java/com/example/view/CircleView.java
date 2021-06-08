package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    private Context mcontext;
    private int screenWidth;
    private int screenHeight;
    private int radial = 50;
    private Paint paint;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint();
        //设置画笔为抗锯齿
        paint.setAntiAlias(true);
        //设置颜色为红色
        paint.setColor(Color.RED);
        //填充
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        //获取屏幕的宽高
        screenHeight = 100;
        screenWidth = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(screenWidth,screenHeight,radial,paint);
    }

}
