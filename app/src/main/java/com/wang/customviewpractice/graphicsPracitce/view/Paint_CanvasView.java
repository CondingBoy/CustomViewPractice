package com.wang.customviewpractice.graphicsPracitce.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangdachui on 2017/5/8.
 */

public class Paint_CanvasView extends View {
    public Paint_CanvasView(Context context) {
        super(context);
    }

    public Paint_CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Paint_CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        draw1(canvas);
//        draw2(canvas);
//        draw3(canvas);
//        draw4(canvas);
//        draw5(canvas);
//        draw6(canvas);
//        draw7(canvas);
//        draw8(canvas);
//        draw9(canvas);
//        draw10(canvas);
        draw11(canvas);
    }

    private void draw11(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        RectF rect = new RectF(100,10,300,100);
        canvas.drawArc(rect,0,90,true,paint);
        RectF rectF = new RectF(320,10,520,100);
        canvas.drawArc(rectF,0,90,false,paint);
    }

    private void draw10(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        RectF rect = new RectF(100,10,300,100);
        canvas.drawArc(rect,0,90,true,paint);
        RectF rectF = new RectF(320,10,520,100);
        canvas.drawArc(rectF,0,90,false,paint);
    }

    /**
     * 画椭圆
     * @param canvas
     */
    private void draw9(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(20);
        RectF rect = new RectF(100,10,300,100);
        canvas.drawRect(rect,paint);
        paint.setColor(Color.GREEN);
        canvas.drawOval(rect,paint);
    }

    /**
     * 画圆形
     * @param canvas
     */
    private void draw8(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(150,150,100,paint);
    }

    private void draw7(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(20);
        RectF rect = new RectF(100,10,300,100);
        canvas.drawRoundRect(rect,10,20,paint);
    }

    /**
     * 绘制矩形
     * @param canvas
     */
    private void draw6(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(20);
        canvas.drawRect(10,10,100,100,paint);//直接绘制矩形
        Rect rect = new Rect(120,10,210,100);
        canvas.drawRect(rect,paint);
        RectF rectF = new RectF(230,10,320,100);
        canvas.drawRect(rectF,paint);

    }

    private void draw5(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(20);
        float pos[] =new float[]{10,10,100,100,200,200,400,400};
        /**
         * offset:跳过数值的个数，数组里一个float表示一个数值
         * count:绘制的数值个数
         * 这里表示，跳过前两个数值，从第三个开始绘四个数值
         */
        canvas.drawPoints(pos,2,4,paint);
    }

    /**
     * 画单个点
     * @param canvas
     */
    private void draw4(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(10);
        canvas.drawPoint(100,100,paint);
    }

    /**
     * 画多条直线
     * @param canvas
     */
    private void draw3(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(10);
        float[] pos =new float[]{10,10,100,100,200,200,400,400};
        canvas.drawLines(pos,paint);
    }

    private void draw2(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStrokeWidth(10);
        canvas.drawLine(100,100,200,200,paint);
    }

    /**
     * 给文字添加阴影
     * @param canvas
     */
    private void draw1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(10,15,15,Color.GREEN);
        canvas.drawText("画图示例",100,100,paint);
    }
}
