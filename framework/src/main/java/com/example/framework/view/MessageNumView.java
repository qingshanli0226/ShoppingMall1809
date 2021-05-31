package com.example.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;

public class MessageNumView extends View {
    private Paint mPaint;
    private Paint mPaint1;
    private int mSize=20;
    private int mWidth;
    private int mHeight;
    private int num;
    public MessageNumView(Context context) {
        super(context);
        init();
    }

    public MessageNumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MessageNumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint1=new Paint();
        mPaint1.setColor(Color.WHITE);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setTextSize(50);
        mPaint1.setStrokeWidth(mSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2,mPaint);
        canvas.drawText(num+"",mWidth/2-10,mHeight/2+20,mPaint1);
    }

    public void getNum(int numm){
       num=numm;
       invalidate();
    }
}
