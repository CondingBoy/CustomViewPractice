package com.wang.customviewpractice.graphicsPracitce.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.wang.customviewpractice.animatePractice.AnimatorPracticeInXml;

/**
 * Created by wangdachui on 2017/5/9.
 */

public class Path_TextView extends View {
    AssetManager assetManager;
    public Path_TextView(Context context) {
        super(context);
        assetManager=context.getAssets();
    }

    public Path_TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        assetManager=context.getAssets();
    }

    public Path_TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        assetManager=context.getAssets();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw9(canvas);
//        draw8(canvas);
//        draw7(canvas);
//        draw6(canvas);
//        draw5(canvas);
//        draw4(canvas);
//        draw3(canvas);
//        draw2(canvas);
//        draw1(canvas);

    }

    /**
     * 自定义字体
     * @param canvas
     */
    private void draw9(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(80);
        Typeface fromAsset = Typeface.createFromAsset(assetManager, "fonts/jian_luobo.ttf");
        paint.setTypeface(fromAsset);
        canvas.drawText("这是自定义字体！",100,100,paint);
    }

    private void draw8(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        Path ccwPath = new Path();
        Path cwPath = new Path();
        //逆向生成的圆形
        ccwPath.addCircle(300,300,200, Path.Direction.CCW);
        //逆向生成的圆形
        cwPath.addCircle(300,750,200, Path.Direction.CCW);
        //画出路径
        canvas.drawPath(ccwPath,paint);
        canvas.drawPath(cwPath,paint);
        paint.setColor(Color.GRAY);
        paint.setTextSize(35);
        String text="风萧萧兮易水寒，壮士一去兮不复还！";
        //第一个表示与路径起始点的水平偏移距离，第二个表示与路径中心的垂直偏移距离
        canvas.drawTextOnPath(text,ccwPath,0,0,paint);
        canvas.drawTextOnPath(text,cwPath,80,30,paint);
    }

    /**
     * 设置每个字的位置
     * @param canvas
     */
    private void draw7(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(80);
        String text = "欢迎光临";
        float[] pos=new float[]{50,100,50,200,50,300,50,400};
        canvas.drawPosText(text,pos,paint);
    }

    /**
     * 绘制文字，中划线，下划线，倾斜度
     * @param canvas
     */
    private void draw6(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(80);
        String text = "欢迎光临";

        paint.setFakeBoldText(true);//加粗
        paint.setUnderlineText(true);//下划线
        paint.setStrikeThruText(true);//删除线

        //负值向右倾斜
        paint.setTextSkewX(-0.25f);
        canvas.drawText(text,50,100,paint);
        //正值向左倾斜
        paint.setTextSkewX(0.25f);
        canvas.drawText(text,50,200,paint);
        paint.setFakeBoldText(false);//加粗
        paint.setUnderlineText(false);//下划线
        paint.setStrikeThruText(false);//删除线
        //正常
        paint.setTextSkewX(0);
        canvas.drawText(text,50,300,paint);
        //横向拉伸两倍
        paint.setTextScaleX(2);
        canvas.drawText(text,50,400,paint);

        //对比拉伸效果
        paint.setTextScaleX(1);
        canvas.drawText(text,50,500,paint);
        paint.setColor(Color.GREEN);
        paint.setTextScaleX(2);
        canvas.drawText(text,50,500,paint);

    }

    private void draw5(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        //圆形路径
        Path circlePath = new Path();
        circlePath.addCircle(200,200,100, Path.Direction.CW);

        //椭圆路径
        Path ovalPath = new Path();
        RectF ovalRect = new RectF(100,350,500,550);
        ovalPath.addOval(ovalRect, Path.Direction.CW);

        //弧路径
        Path arcPath=new Path();
        RectF arcRect = new RectF(100,600,500,800);
        arcPath.addArc(arcRect,0,100);

        //画出路径
        canvas.drawPath(circlePath,paint);
        canvas.drawPath(ovalPath,paint);
        canvas.drawPath(arcPath,paint);


    }

    /**
     * 圆角矩形路径
     * @param canvas
     */
    private void draw4(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        Path ccwPath = new Path();
        Path cwPath = new Path();
        //逆向生成的矩形
        RectF ccwRectF = new RectF(50, 50, 240, 200);
        //所有圆角统一设置
        ccwPath.addRoundRect(ccwRectF,10,15, Path.Direction.CCW);
        RectF cwRectF = new RectF(290, 50, 480, 200);
        //设备每一个圆角
        float[] radius= new float[]{5,10,15,20,25,30,35,40};
        cwPath.addRoundRect(cwRectF, radius,Path.Direction.CW);

        //画出路径
        canvas.drawPath(ccwPath,paint);
        canvas.drawPath(cwPath,paint);
    }

    /**
     * 按路径画文字
     * @param canvas
     */
    private void draw3(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        Path ccwPath = new Path();
        Path cwPath = new Path();
        //逆向生成的矩形
        RectF ccwRectF = new RectF(50, 50, 240, 200);
        ccwPath.addRect(ccwRectF, Path.Direction.CCW);
        RectF cwRectF = new RectF(290, 50, 480, 200);
        cwPath.addRect(cwRectF, Path.Direction.CW);

        //画出路径
        canvas.drawPath(ccwPath,paint);
        canvas.drawPath(cwPath,paint);
        paint.setColor(Color.GRAY);
        paint.setTextSize(35);
        String text="风萧萧兮易水寒，壮士一去兮不复还！";
        canvas.drawTextOnPath(text,ccwPath,0,18,paint);
        canvas.drawTextOnPath(text,cwPath,0,18,paint);
    }

    private void draw2(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        //逆向生成的矩形
        RectF ccwRectF = new RectF(50, 50, 240, 200);
        path.addRect(ccwRectF, Path.Direction.CCW);
        RectF cwRectF = new RectF(290, 50, 480, 200);
        path.addRect(cwRectF, Path.Direction.CW);

        //画出路径
        canvas.drawPath(path,paint);

    }

    private void draw1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(10,10);
        path.lineTo(10,100);
        path.lineTo(300,100);
        path.lineTo(500,100);
        path.close();
        canvas.drawPath(path,paint);
    }
}
