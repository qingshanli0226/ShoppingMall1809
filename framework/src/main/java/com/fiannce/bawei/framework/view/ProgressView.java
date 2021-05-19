package com.fiannce.bawei.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.fiannce.bawei.framework.R;

import androidx.annotation.NonNull;


public class ProgressView extends View {
    private Paint paint;
    private final int START_ANGLE = 0;//以3点钟方向开始绘制扇形
    private final int STEP_ANGLE = 1;//每次按照1度的大小进行增加
    private int offsetAngle;//绘制扇形的角度.该大小根据理财产品销售的百分比进度计算出来的
    private int progressAngle = 0;//已经绘制的角度
    private int progressViewWidth;
    private int progressViewHeight;//控件的宽度和高度
    private int progess;
    private final int CIRCLE_MARGIN = 5;
    private int textColor;
    private int circleWith;
    private int backgroundId;

    public ProgressView(Context context) {//new 一个控件时，会调用该构造方法
        super(context);
        init(context,null,0);
    }

    public ProgressView(Context context, AttributeSet attrs) {//在布局里声明，findViewById会调用该构造方法
        super(context, attrs);
        init(context,attrs,0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {//当布局里有style属性时会调用该构造函数
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    //初始化方法
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ProgressView);
        textColor = typedArray.getColor(R.styleable.ProgressView_textColor,Color.BLACK);
        circleWith = typedArray.getInt(R.styleable.ProgressView_circleWith,5);
        backgroundId = typedArray.getResourceId(R.styleable.ProgressView_background,0);

        typedArray.recycle();
    }

    //添加一个方法，设置理财产品销售的百分比（如果卖了20%，progress参数就传20）
    public void saledProgress(int progress,boolean isAnimal) {
        offsetAngle = (progress * 360)/100;//计算出扇形绘制的角度
        if (isAnimal) {
            progressAngle = 0;
        } else {
            progressAngle = offsetAngle;
        }

        if (progressAngle<=offsetAngle) {
            invalidate();//触发onDraw函数执行
            handler.sendEmptyMessageDelayed(1,10);
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            progressAngle = progressAngle + STEP_ANGLE;
            if (progressAngle<=offsetAngle) {
                invalidate();
                handler.sendEmptyMessageDelayed(1,10);
            }
        }
    };
    

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getY();event.getRawY();
        event.getX();event.getRawX();
        return super.onTouchEvent(event);
    }

    //把控件摆放到那个位置的，实现自定义View时，该方法按照默认实现就可以。把控件摆放到何处，由它的父布局来决定就可以了
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setTextColor(int color) {
        this.textColor = color;
        invalidate();
    }

    public void setCircleWith(int width) {
        this.circleWith = width;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int realWidth,realHeitht;

        //获取自定义View的测量模式，通过测量模式来判断当前用户在布局里给这个自定义View设置的宽和高是确定值（例如100dp或者match_parent)还是使用的wrap_content
        //如果是确定值，不用改变，否则的话，给该自定义View设置一个默认的宽度和高度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {//在布局里给这个自定义设置的宽度是wrap_content,那么获取到的测量模式就是AT_MOST,如果是确定值是EXACTLY，
            // UNSPECIFILED,未指定测量模式，在ListView或者RecyclerView里传的是未指定测量模式
            realWidth = 50;//如果宽度是wrap_content时，把宽度设置成默认的宽度是50；
        } else {
            realWidth = MeasureSpec.getSize(widthMeasureSpec);
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            realHeitht = 50;
        } else {
            realHeitht = MeasureSpec.getSize(heightMeasureSpec);
        }

        //通过该方法可以指定这个控件的大小,它的优先级大于布局里的优先级
        setMeasuredDimension(realWidth,realHeitht);
    }

    //绘制View，重点实现
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //第一步绘制底部圆形
        //先求出圆心
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),backgroundId);
        Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmap,matrix,paint);
        progressViewWidth = getMeasuredWidth();//获取控件的宽度，必须在onMeasure之后再获取
        progressViewHeight = getMeasuredHeight();//获取控件的高度，必须在onMeasure之后再获取
        int centerX = progressViewWidth/2;
        int centerY = progressViewHeight/2;
        //获取半径
        int radius = (progressViewWidth<progressViewHeight?progressViewWidth/2:progressViewHeight/2)-CIRCLE_MARGIN;
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleWith);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX,centerY,radius,paint);


        //画扇形
        RectF rectF = new RectF(progressViewWidth/2-radius,progressViewHeight/2-radius,progressViewWidth/2+radius,progressViewHeight/2+radius);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleWith);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,START_ANGLE,progressAngle,false,paint);

        //绘制文字
        Rect rect = new Rect();
        paint.setColor(textColor);
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        String content = (progressAngle*100)/360+"%";
        Log.d("LQS", content+"");
        paint.getTextBounds(content,0,content.length(),rect);
        canvas.drawText(content,progressViewWidth/2-rect.width()/2,progressViewHeight/2+rect.height()/2,paint);
    }

    public void destry() {
        handler.removeCallbacksAndMessages(null);
    }
}
