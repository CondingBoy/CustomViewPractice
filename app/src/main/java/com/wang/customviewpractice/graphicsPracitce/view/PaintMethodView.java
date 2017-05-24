package com.wang.customviewpractice.graphicsPracitce.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by wangdachui on 2017/5/17.
 */

public class PaintMethodView extends View {
    int mPathOffset=0;
    public PaintMethodView(Context context) {
        this(context,null);
    }

    public PaintMethodView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaintMethodView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        startAnimator();
    }

    private void startAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,58);
        valueAnimator.setDuration(800)
                .setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mPathOffset= (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw5(canvas);
//        draw4(canvas);
//        draw3(canvas);
//        draw2(canvas);
//        draw1(canvas);
    }
    //印章路径效果
    private void draw5(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Path path =new Path();
        path.moveTo(100,600);
        path.lineTo(400,100);
        path.lineTo(700,900);

        Path shapePath=new Path();
        shapePath.moveTo(0,20);
        shapePath.lineTo(10,0);
        shapePath.lineTo(20,20);
        shapePath.close();
        shapePath.addCircle(0,0,3, Path.Direction.CCW);

        paint.setPathEffect(new PathDashPathEffect(shapePath,35,mPathOffset, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(path,paint);
    }

    //虚线路径效果
    private void draw4(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Path path =new Path();
        path.moveTo(100,600);
        path.lineTo(400,100);
        path.lineTo(700,900);

        paint.setPathEffect(new DashPathEffect(new float[]{20,10,100,100},0));
        canvas.drawPath(path,paint);
        //画布平移
        canvas.translate(0,100);
        paint.setColor(Color.RED);
        //偏移值为15
        paint.setPathEffect(new DashPathEffect(new float[]{20,10,100,100},15));
        canvas.drawPath(path,paint);

        canvas.translate(0,100);
        paint.setPathEffect(new DashPathEffect(new float[]{20,10,100,100},mPathOffset));
        paint.setColor(Color.YELLOW);
        canvas.drawPath(path,paint);
    }

    //路径效果,拐角效果
    private void draw3(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Path path =new Path();
        path.moveTo(100,600);
        path.lineTo(400,100);
        path.lineTo(700,900);

        canvas.drawPath(path,paint);

        paint.setColor(Color.RED);
        paint.setPathEffect(new CornerPathEffect(20));
        canvas.drawPath(path,paint);

        paint.setColor(Color.YELLOW);
        paint.setPathEffect(new CornerPathEffect(100));
        canvas.drawPath(path,paint);
    }

    /**
     * 路径连接处样式
     * @param canvas
     */
    private void draw2(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Path path =new Path();
        path.moveTo(100,100);
        path.lineTo(500,100);
        path.lineTo(200,200);
        //锐角连接
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path,paint);

        path.reset();
        path.moveTo(100,300);
        path.lineTo(500,300);
        path.lineTo(200,400);
        //结合处为直线
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path,paint);

        path.reset();
        path.moveTo(100,500);
        path.lineTo(500,500);
        path.lineTo(200,600);
        //圆弧连接
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path,paint);
    }

    /**
     * 线帽
     * @param canvas
     */
    private void draw1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(80);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        //圆形线帽
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100,100,500,100,paint);

        //方形线帽
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100,200,500,200,paint);

        //无线帽
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100,300,500,300,paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawLine(100,100,100,700,paint);

    }
}
