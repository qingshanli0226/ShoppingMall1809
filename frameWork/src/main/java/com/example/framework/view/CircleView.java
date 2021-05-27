package com.example.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {
    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private Paint paint;
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();
    }

    private String text = "8";

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int radius = (measuredHeight>measuredWidth?measuredWidth/2:measuredHeight/2)-10;

        canvas.drawCircle(measuredWidth/2,measuredHeight/2,radius,paint);


        paint.setColor(Color.WHITE);
        paint.setTextSize(radius);
        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text,measuredWidth/2-rect.width()/2,measuredHeight/2+rect.height()/2,paint);
    }
}
