package com.wang.customviewpractice.drawingofview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wang.customviewpractice.R;

/**
 * Created by Administrator on 2017/3/13.
 */
public class WaveView extends View {

    private float mWaveLength;
    private float mWaveHeight;
    private int mWaveColor;
    private Path mWavePath;
    private int currentWidth;
    private int currentHeight;
    private int mOrignY;
    private Paint mWavePaint,dscPaint,mPaint;
    private int mDx,maxProgress,currentProgress;
    private ValueAnimator heightAnimator;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }


    private void init(Context context, AttributeSet attrs) {
        mWavePaint = new Paint();
        mWavePaint.setAntiAlias(true);
        dscPaint = new Paint();
        dscPaint.setAntiAlias(true);
        dscPaint.setColor(Color.WHITE);
        dscPaint.setStyle(Paint.Style.FILL);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        //填充内部和描边
        mWavePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWavePath = new Path();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        if (typedArray != null) {
            mWaveLength = typedArray.getDimension(R.styleable.WaveView_waveLength, 0);
            mWaveHeight = typedArray.getDimension(R.styleable.WaveView_waveHeight, 0);
            mWaveColor = typedArray.getColor(R.styleable.WaveView_waveColor, Color.BLUE);
            mWavePaint.setColor(mWaveColor);
            maxProgress=typedArray.getInteger(R.styleable.WaveView_maxProgress,100);
            currentProgress=typedArray.getInteger(R.styleable.WaveView_currentProgress,0);
            typedArray.recycle();
        }
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                measureWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                measureWidth = (int) mWaveLength;
                break;
            default:
                measureWidth = (int) mWaveLength;
        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                measureHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                measureHeight = (int) (2 * mWaveHeight);
                break;
            default:
                measureHeight = (int) (2 * mWaveHeight);
        }
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        currentWidth = w;
        currentHeight = h;
        mOrignY = getRadius();
        refreshHeightAnimate();
        mDx = 0;
        Log.e("TAG", "width:" + currentWidth + ":" + "height:" + currentHeight + ":" + "mOrignY:" + mOrignY);
    }
    //刷新动画
    private void refreshHeightAnimate() {

        float ratio=currentProgress*1.0f/maxProgress;
//        int toHeight = (int) ((currentHeight - getPaddingBottom() - getPaddingTop()) * ratio);
        int toHeight= (int) (getRadius()*ratio);

        startHeightAnimate(toHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layer = canvas.saveLayer(new RectF(getPaddingLeft(), getPaddingTop(), currentWidth - getPaddingRight(), currentHeight - getPaddingBottom()), mWavePaint, Canvas.ALL_SAVE_FLAG);
        //圆形半径
        int radius = getRadius();
        Bitmap dscBmp = getDscLayerBmp(radius,radius);
        int left = getPaddingLeft()+(currentWidth-getPaddingLeft()-getPaddingRight()-radius)/2;
        int top = getPaddingTop()+(currentHeight-getPaddingTop()-getPaddingBottom()-radius)/2;
        canvas.drawBitmap(dscBmp,left,top,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Bitmap srcBmp =getSrcLayerBmp();
        canvas.drawBitmap(srcBmp,0,top,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layer);
    }

    /**
     * 获取dsc层的图片,图片是一个正方形的内切圆
     * @return
     */
    private Bitmap getDscLayerBmp(int width,int height) {
        Bitmap dscBm = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(dscBm);
        canvas.drawCircle(width/2,width/2,width/2,dscPaint);
        return dscBm;
    }
    private Bitmap getSrcLayerBmp(){
        Bitmap srcBmp =Bitmap.createBitmap(currentWidth,getRadius(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(srcBmp);
        mWavePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //重置path
        mWavePath.reset();
        int halfWaveLength = (int) (mWaveLength / 2);
        mWavePath.moveTo(-mWaveLength + mDx, mOrignY);
        for (int i = (int) -mWaveLength; i <= currentWidth + mWaveLength; i += mWaveLength) {
            mWavePath.rQuadTo(halfWaveLength / 2, mWaveHeight, halfWaveLength, 0);
            mWavePath.rQuadTo(halfWaveLength / 2, -mWaveHeight, halfWaveLength, 0);
        }
        mWavePath.lineTo(currentWidth, currentHeight - getPaddingBottom());
        mWavePath.lineTo(0, currentHeight - getPaddingBottom());
        mWavePath.close();
        canvas.drawPath(mWavePath, mWavePaint);
        return srcBmp;
    }

    /**
     * 获取空间的半径
     * @return
     */
    private int getRadius(){
        return Math.min(currentWidth-getPaddingLeft()-getPaddingRight(),currentHeight-getPaddingBottom()-getPaddingTop());
    }

    public void startAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) mWaveLength);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mDx = (int) valueAnimator.getAnimatedValue();
//                Log.e("TAG","mDx:"+mDx);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    public void startHeightAnimate(int height) {
        if(heightAnimator!=null&&heightAnimator.isRunning()){
            heightAnimator.cancel();
        }
        heightAnimator = ValueAnimator.ofInt(height);
        heightAnimator.setDuration(3500);
        heightAnimator.setStartDelay(500);
        heightAnimator.setInterpolator(new LinearInterpolator());
        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int current = (int) valueAnimator.getAnimatedValue();

                mOrignY = getRadius()-current;
                postInvalidate();
            }
        });
        heightAnimator.start();
    }
    public void setMaxProgress(int maxProgress){
        this.maxProgress=maxProgress;
        refreshHeightAnimate();
    }
    public void setCurrentProgress(int currentProgress){
        this.currentProgress=currentProgress;
        refreshHeightAnimate();
    }
}
