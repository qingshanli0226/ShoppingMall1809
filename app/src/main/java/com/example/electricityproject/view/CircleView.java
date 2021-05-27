package com.example.electricityproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {
    private Paint paint=new Paint();
    private String currentNum="0";
    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setCurrentNum(String str){
        currentNum=str;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //红色圆圈
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        int ScX = getMeasuredWidth()/2;
        int ScY = getMeasuredHeight()/2;
        int radius = getMeasuredWidth()/2>getMeasuredHeight()/2?getMeasuredHeight()/2:getMeasuredWidth()/2;
        canvas.drawCircle(ScX,ScY,radius,paint);

        //圈内文字
        Rect rect = new Rect();
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
        paint.setStrokeWidth(5);

        paint.getTextBounds(currentNum,0,currentNum.length(),rect);
        canvas.drawText(currentNum,getMeasuredWidth()/2-rect.width()/2,getMeasuredHeight()/2+rect.height()/2,paint);


    }
}
