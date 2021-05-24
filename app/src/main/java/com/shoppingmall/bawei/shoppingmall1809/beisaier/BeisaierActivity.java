package com.shoppingmall.bawei.shoppingmall1809.beisaier;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fiannce.bawei.framework.BaseActivity;
import com.shoppingmall.bawei.shoppingmall1809.R;

public class BeisaierActivity extends BaseActivity {
    private RelativeLayout rootView;
    private TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_beisaier;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        rootView = findViewById(R.id.rootView);
        findViewById(R.id.btnBeisai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBeisaierAnim();
            }
        });
        textView = findViewById(R.id.centerTv);

    }

    //显示贝塞尔曲线动画.找起始点，终点，控制点
    private void showBeisaierAnim() {
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100,100);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.mipmap.e);
        rootView.addView(imageView);

       int[] startLoacation = new int[2];//起始点坐标
        /**startLoacation[0] = 300;
        startLoacation[1] = 0;*/
       textView.getLocationInWindow(startLoacation);

        int[] endLocation = new int[2];//终点坐标
        endLocation[0] = 600;
        endLocation[1] = 1000;
        int[] controlLocation = new int[2];//控制点坐标
        controlLocation[0] = 0;
        controlLocation[1] = 300;
        int[] controlLocation2 = new int[2];//控制点坐标
        controlLocation2[0] = 500;
        controlLocation2[1] = 500;

        Path path = new Path();
        path.moveTo(startLoacation[0],startLoacation[1]);
        path.cubicTo(controlLocation[0],controlLocation[1],controlLocation2[0],controlLocation2[1],endLocation[0],endLocation[1]);
        PathMeasure pathMeasure = new PathMeasure(path,false);//是计算控件下一次要移动到的位置坐标

        //使用属性动画
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,pathMeasure.getLength());
        valueAnimator.setDuration(3*1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        Log.d("LQS", "showBeisaierAnim: length = " + pathMeasure.getLength());
        //动画更新监听，监听动画的绘制过程
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();
                Log.d("LQS", "onAnimationUpdate: " + value);
                float[] nextLocation = new float[2];
                pathMeasure.getPosTan(value,nextLocation,null);
                imageView.setTranslationX(nextLocation[0]);//让控件移动到下一个X，Y坐标处
                imageView.setTranslationY(nextLocation[1]);

                float percent = value/pathMeasure.getLength();
                imageView.setAlpha(1-percent);
                imageView.setScaleX(2*percent);
                imageView.setScaleY(2*percent);
            }
        });

        valueAnimator.start();

    }
}
