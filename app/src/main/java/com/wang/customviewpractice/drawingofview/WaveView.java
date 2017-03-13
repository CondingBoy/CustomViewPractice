package com.wang.customviewpractice.drawingofview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    private Paint mWavePaint;
    private int mDx;

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
        //填充内部和描边
        mWavePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWavePath = new Path();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        if (typedArray != null) {
            mWaveLength = typedArray.getDimension(R.styleable.WaveView_waveLength, 0);
            mWaveHeight = typedArray.getDimension(R.styleable.WaveView_waveHeight, 0);
            mWaveColor = typedArray.getColor(R.styleable.WaveView_waveColor, Color.BLUE);
            mWavePaint.setColor(mWaveColor);
            typedArray.recycle();
        }
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
        mOrignY = currentHeight - getPaddingBottom();
        int toHeight = (int) ((h - getPaddingBottom() - getPaddingTop()) * 0.5f);
        mDx = 0;
        startHeightAnimate(toHeight);
        Log.e("TAG", "width:" + currentWidth + ":" + "height:" + currentHeight + ":" + "mOrignY:" + mOrignY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
        ValueAnimator valueAnimator = ValueAnimator.ofInt(height);
        valueAnimator.setDuration(3500);
        valueAnimator.setStartDelay(500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int current = (int) valueAnimator.getAnimatedValue();
                mOrignY = currentHeight - getPaddingBottom() - current;
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
