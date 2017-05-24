package com.wang.customviewpractice.graphicsPracitce.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.wang.customviewpractice.R;

/**
 * Created by wangdachui on 2017/5/18.
 */

public class ColorMetricsView extends View {
    private Bitmap originBt;
    private Paint mPaint;

    public ColorMetricsView(Context context) {
        this(context,null);
    }

    public ColorMetricsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorMetricsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        originBt = BitmapFactory.decodeResource(context.getResources(), R.mipmap.dog);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw8(canvas);
//        draw7(canvas);
//        draw6(canvas);
//        draw5(canvas);
//        draw4(canvas);
//        draw3(canvas);
//        draw2(canvas);
//        draw1(canvas);

    }

    /**
     * 直接使用colormatrics的方法设置色相
     * @param canvas
     */
    private void draw8(Canvas canvas) {
        mPaint.setColorFilter(null);
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //画布平移
        canvas.translate(510,0);
        ColorMatrix colorMatrix = new ColorMatrix();
        //0红色，1：绿色，2：蓝色
        colorMatrix.setRotate(0,20);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
    }

    /**
     * 直接使用colormatrics的方法设置饱和度
     * @param canvas
     */
    private void draw7(Canvas canvas) {
        mPaint.setColorFilter(null);
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //画布平移
        canvas.translate(510,0);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(5);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
    }

    /**
     * 直接使用colorMatrics的方法缩放
     * @param canvas
     */
    private void draw6(Canvas canvas) {
        mPaint.setColorFilter(null);
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //画布平移
        canvas.translate(510,0);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setScale(1,1.3f,1,1);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
    }

    /**
     * 色彩缩放，对单个颜色缩放，效果是颜色的单通道输出
     * @param canvas
     */
    private void draw5(Canvas canvas) {
        mPaint.setColorFilter(null);
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //画布平移
        canvas.translate(510,0);
        //红色通道
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1.0f,0,0,0,0,
                0,0,0,0,0,
                0,0,0,0,0,
                0,0,0,1f,0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //  平移画布
        canvas.translate(0,500*originBt.getHeight()/originBt.getWidth()+20);
        //绿色通道
        mPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{
                0,0,0,0,0,
                0,1,0,0,0,
                0,0,0,0,0,
                0,0,0,1f,0
        }));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        canvas.translate(-510,0);
        //蓝色通道
        mPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{
                0,0,0,0,0,
                0,0,0,0,0,
                0,0,1,0,0,
                0,0,0,1f,0
        }));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
    }

    /**
     * 色彩缩放，对argb进行同样的缩放，效果是提升亮度
     * @param canvas
     */
    private void draw4(Canvas canvas) {
        mPaint.setColorFilter(null);
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //画布平移
        canvas.translate(510,0);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1.2f,0,0,0,0,
                0,1.2f,0,0,0,
                0,0,1.2f,0,0,
                0,0,0,1.2f,0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
    }

    /**
     * 色彩反转
     * @param canvas
     */
    private void draw3(Canvas canvas) {
        mPaint.setColorFilter(null);
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //画布平移
        canvas.translate(510,0);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                -1,0,0,0,255,
                0,-1,0,0,255,
                0,0,-1,0,255,
                0,0,0,1,0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
    }

    /**
     * 色彩平移，增加颜色饱和度
     * @param canvas
     */
    private void draw2(Canvas canvas) {
        mPaint.setColorFilter(null);
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //画布平移
        canvas.translate(510,0);
        //色彩矩阵,单通道颜色输出，这里是蓝色
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1,0,0,0,0, //R
                0,1,0,0,50,//G
                0,0,1,0,0,//B
                0,0,0,1,0//A
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
    }

    /**
     * 单通道色彩输出
     * @param canvas
     */
    private void draw1(Canvas canvas) {
        mPaint.setColorFilter(null);
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
        //画布平移
        canvas.translate(510,0);
        //色彩矩阵,单通道颜色输出，这里是蓝色
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0,0,0,0,0, //R
                0,0,0,0,0,//G
                0,0,1,0,0,//B
                0,0,0,1,0//A
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originBt,null,new RectF(0,0,500,500*originBt.getHeight()/originBt.getWidth()),mPaint);
    }
}
