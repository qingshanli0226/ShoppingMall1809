package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.framework.R;

public class ProgressView extends View {

    private Paint paint;
    private final int START_ANGLE = 0;//以3点钟方向开始绘制扇形
    private final int STEP_ANGLE = 1;//每次按照1度的大小进行增加
    private int offsetAngle;//需要绘制的百分比
    private int progressAngle;//已经绘制的百分比
    private int scanRadian = 0;//现在要展示的弧度

    private int progressViewWidth;//宽度
    private int progressViewHeight;//高度
    private int paintCircleWidth = 10;//圆形宽度
    private int paintTextSize = 100;//字体大小
    private int paintTextColor = Color.BLUE;
    private int progressBackRoundColor = Color.GREEN;//进度背景颜色
    private int progressColor = Color.RED;//进度颜色



    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        paintCircleWidth = typedArray.getInt(R.styleable.ProgressView_wp_paintCircleWidth,10);
        paintTextSize = typedArray.getInt(R.styleable.ProgressView_wp_paintTextSize,100);

        paintTextColor = typedArray.getColor(R.styleable.ProgressView_wp_paintTextColor,Color.BLUE);
        progressBackRoundColor = typedArray.getColor(R.styleable.ProgressView_wp_progressBackRoundColor,Color.GREEN);
        progressColor = typedArray.getColor(R.styleable.ProgressView_wp_progressColor,Color.RED);

        typedArray.recycle();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height =0 ;
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heighthmode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthmode==MeasureSpec.AT_MOST){
            width = 200;
        }else {
            width =widthMeasureSpec;
        }

        if (heighthmode==MeasureSpec.AT_MOST){
            height=200;
        }else {
            height =widthMeasureSpec;
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void beginProgressAnimation(int progress, boolean isAnimation) {
        offsetAngle = progress;
        if (isAnimation) {
            progressAngle = 0;
        } else {
            progressAngle = progress;
        }
        handler.sendEmptyMessage(1);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (offsetAngle > progressAngle) {
                progressAngle += STEP_ANGLE;
                scanRadian = (int) progressAngle * 360 / 100;
                invalidate();
                handler.sendEmptyMessageDelayed(1, 30);
            } else {
                scanRadian = (int) progressAngle * 360 / 100;
                invalidate();
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        progressViewWidth = getMeasuredWidth();
        progressViewHeight = getMeasuredHeight();

        float progressX = progressViewWidth / 2;
        float progressY = progressViewHeight / 2;
        float radius = (progressViewWidth < progressViewHeight ? progressViewWidth / 2 : progressViewHeight / 2) - paintCircleWidth;

        //设施画笔属性
        paint.setColor(progressBackRoundColor);
        paint.setStrokeWidth(paintCircleWidth);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(progressX, progressY, radius, paint);

        paint.setColor(progressColor);
        RectF rectF = new RectF(progressX - radius, progressY - radius, progressX + radius, progressY + radius);
        canvas.drawArc(rectF, START_ANGLE, scanRadian, false, paint);

        paint.setColor(paintTextColor);
        paint.setTextSize(paintTextSize);
        paint.setStrokeWidth(paintTextSize / 30);
        paint.setStyle(Paint.Style.FILL);


        Rect rect = new Rect();
        String text = progressAngle + "%";

        paint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text, progressX - rect.width() / 2, progressY+rect.height()/2, paint);

    }

    public void destroy(){
        handler.removeCallbacksAndMessages(null);
    }


    public void setProgressViewWidth(int progressViewWidth) {
        this.progressViewWidth = progressViewWidth;
    }

    public void setProgressViewHeight(int progressViewHeight) {
        this.progressViewHeight = progressViewHeight;
    }

    public void setPaintCircleWidth(int paintCircleWidth) {
        this.paintCircleWidth = paintCircleWidth;
    }

    public void setPaintTextSize(int paintTextSize) {
        this.paintTextSize = paintTextSize;
    }

    public void setPaintTextColor(int paintTextColor) {
        this.paintTextColor = paintTextColor;
    }

    public void setProgressBackRoundColor(int progressBackRoundColor) {
        this.progressBackRoundColor = progressBackRoundColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }


}
