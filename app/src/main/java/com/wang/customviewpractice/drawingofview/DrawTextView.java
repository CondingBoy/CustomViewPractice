package com.wang.customviewpractice.drawingofview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.nio.MappedByteBuffer;
import java.util.MissingFormatArgumentException;

/**
 * Created by wangdachui on 2017/3/30.
 * 学习确定drawText的基线
 */

public class DrawTextView extends View {
    private int mBaseLine,mFromX,mScreenWidth;
    private Paint mTextPaint,mTopPaint,mAscentPaint,mBasePaint,mDescentPaint,mBootomPaint,textBoundsPaint;
    private String text = "WangDachui!";
    private String text2 = "WangWei";
    public DrawTextView(Context context) {
        this(context,null);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBaseLine=300;
        mFromX=20;

        //文字范围画笔
        textBoundsPaint=new Paint();
        textBoundsPaint.setColor(Color.WHITE);


        //文字画笔
        mTextPaint = new Paint();
        mTextPaint.setTextSize(120);
        mTextPaint.setColor(Color.GREEN);
        //Top线画笔
        mTopPaint = new Paint();
        mTopPaint.setTextSize(30);
        mTopPaint.setStrokeWidth(3);
        mTopPaint.setColor(Color.parseColor("#128003"));
        //Ascent线画笔
        mAscentPaint = new Paint();
        mAscentPaint.setTextSize(30);
        mAscentPaint.setStrokeWidth(3);
        mAscentPaint.setColor(Color.parseColor("#FE9124"));
        //Base线画笔
        mBasePaint = new Paint();
        mBasePaint.setTextSize(30);
        mBasePaint.setStrokeWidth(3);
        mBasePaint.setColor(Color.parseColor("#FF3D44"));
        //Descent线画笔
        mDescentPaint = new Paint();
        mDescentPaint.setTextSize(30);
        mDescentPaint.setStrokeWidth(3);
        mDescentPaint.setColor(Color.parseColor("#FF42A0"));
        //Bootom线画笔
        mBootomPaint = new Paint();
        mBootomPaint.setTextSize(30);
        mBootomPaint.setStrokeWidth(3);
        mBootomPaint.setColor(Color.parseColor("#810004"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenWidth=w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect textRect = new Rect();
        mTextPaint.getTextBounds(text,0,text.length(),textRect);
        textRect.offset(mFromX,mBaseLine);
//        textRect.offsetTo(mFromX,mBaseLine);
        canvas.drawRect(textRect,textBoundsPaint);
//        Log.e("Rect:","TOP:"+textRect.top+" BOTTOM:"+textRect.bottom+" LEFT:"+textRect.left+" RIGHT:"+textRect.right);

        canvas.drawText(text,0,text.length(),mFromX,mBaseLine,mTextPaint);
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int topY = fontMetricsInt.top+mBaseLine;
        int ascentY = fontMetricsInt.ascent+mBaseLine;
        int descentY = fontMetricsInt.descent+mBaseLine;
        int bootomY = fontMetricsInt.bottom+mBaseLine;
        String str1 = "可绘制最顶线（Top）";
        String str2 = "当前绘制顶线（Ascent）";
        String str3 = "基线（BaseLine）";
        String str4 = "当前绘制底线（Descent）";
        String str5 = "可绘制最底线（Bottom）";

        //绘制Top线
        canvas.drawLine(0,topY,mScreenWidth,topY,mTopPaint);
        canvas.drawText(str1,0,str1.length(),mScreenWidth-mTopPaint.measureText(str1),topY-20,mTopPaint);
        //绘制Ascent线
        Rect rect = new Rect();
        mAscentPaint.getTextBounds(str2,0,str2.length(),rect);
            //str2以ascent为中线绘制，给定中心线位置，计算baseline
        Paint.FontMetricsInt ascentFontMetrics = mAscentPaint.getFontMetricsInt();
        //baseline相对中心线的距离
        int dy=(ascentFontMetrics.bottom-ascentFontMetrics.top)/2-ascentFontMetrics.bottom;
        canvas.drawLine(0,ascentY,mScreenWidth,ascentY,mAscentPaint);
        canvas.drawText(str2,0,str2.length(),mScreenWidth-mAscentPaint.measureText(str2),ascentY+dy,mAscentPaint);
        //绘制Baselien线
        canvas.drawLine(0,mBaseLine,mScreenWidth,mBaseLine,mBasePaint);
        canvas.drawText(str3,0,str3.length(),mScreenWidth-mBasePaint.measureText(str3),mBaseLine-20,mBasePaint);
        //绘制Descent线
        canvas.drawLine(0,descentY,mScreenWidth,descentY,mDescentPaint);
        canvas.drawText(str4,0,str4.length(),mScreenWidth-mDescentPaint.measureText(str4),descentY+5,mDescentPaint);
        //绘制Bottom线
        canvas.drawLine(0,bootomY,mScreenWidth,bootomY,mBootomPaint);
        mBootomPaint.getTextBounds(str5,0,str5.length(),rect);
        canvas.drawText(str5,0,str5.length(),mScreenWidth-mBootomPaint.measureText(str5),bootomY+(rect.bottom-rect.top),mBootomPaint);


        //已知中间线原点为（20，600）,用rect确定baseline
        int centerX=20,centerY=600;
        Rect text2Rect = new Rect();
        mTextPaint.getTextBounds(text2,0,text2.length(),text2Rect);
        Log.e("rect:","top:"+text2Rect.top+",bottom:"+text2Rect.bottom+",left:"+text2Rect.left+",right:"+text2Rect.right);
        //计算baseline,top为负值，要加括号
        int baseline2 = centerY-(text2Rect.top+(text2Rect.bottom-text2Rect.top)/2);
        Log.e("baseline::",baseline2+"");

        //绘出文字范围，得到的rect是以（0，0）为原点测得的，所以要平移到正确的位置
        text2Rect.offset(centerX,baseline2);
        canvas.drawRect(text2Rect,textBoundsPaint);
        canvas.drawText(text2,centerX,baseline2,mTextPaint);
        //绘制处baseline
        Paint linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(3);
        canvas.drawLine(0,centerY,mScreenWidth,centerY,linePaint);
        canvas.drawLine(0,baseline2,mScreenWidth,baseline2,linePaint);
        linePaint.setColor(Color.BLUE);
        canvas.drawLine(0,text2Rect.top,mScreenWidth,text2Rect.top,linePaint);
        canvas.drawLine(0,text2Rect.bottom,mScreenWidth,text2Rect.bottom,linePaint);

        linePaint.setColor(Color.GREEN);
//        canvas.drawLine(0,baseline3,mScreenWidth,baseline3,linePaint);
        //绘出文字
        canvas.drawText(text2,centerX,baseline2,mTextPaint);

        //给定中线，以metric算baseline
        int cx=20,cy = 900;
        //用metrics计算baseline
        Paint.FontMetricsInt metricsInt = mTextPaint.getFontMetricsInt();
        int baseline3 = cy+((metricsInt.bottom-metricsInt.top)/2-metricsInt.bottom);

        Log.e("metrics:","top:"+metricsInt.top+",bottom:"+metricsInt.bottom);
        Log.e("baseline3:",baseline3+"");

        Rect text3Rect = new Rect();
        mTextPaint.getTextBounds(text2,0,text2.length(),text3Rect);
        text3Rect.offset(cx,baseline3);
        canvas.drawRect(text3Rect,textBoundsPaint);

        canvas.drawText(text2,cx,baseline3,mTextPaint);
        canvas.drawLine(0,metricsInt.ascent+baseline3,mScreenWidth,metricsInt.ascent+baseline3,mAscentPaint);
        canvas.drawLine(0,metricsInt.top+baseline3,mScreenWidth,metricsInt.top+baseline3,mTopPaint);
        canvas.drawLine(0,metricsInt.descent+baseline3,mScreenWidth,metricsInt.descent+baseline3,mDescentPaint);
        canvas.drawLine(0,metricsInt.bottom+baseline3,mScreenWidth,metricsInt.bottom+baseline3,mBootomPaint);
        canvas.drawLine(0,baseline3,mScreenWidth,baseline3,mBasePaint);
        canvas.drawLine(cx,cy,mScreenWidth,cy,linePaint);


    }
}
