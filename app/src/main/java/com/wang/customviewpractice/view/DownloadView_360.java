package com.wang.customviewpractice.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wang.customviewpractice.R;


/**
 * Created by wangdachui on 2017/5/20.
 * 360下载view练习
 */

public class DownloadView_360 extends View {
    private static final int cicleRotateDuration=600;
    private static final int[] mRadius=new int[]{15,6,8,10};
    private Status mStatus;
    private int mBackgroundColor,mProgress,mTextColor;
    private float mTextSize;
    private int mStartDuration,mPreduration,mExpandDuration,mPointTranslationDuration,mPointRotateDuration;
    private Paint mBackPaint,mProPaint,mTextPaint,mCirclePaint;
    private int mWidth,mHeight,mCurrentWidth,mCurrentHeight;
    private RectF mViewBound;
    private String mNormalText,mCompleteText;
    private float mRotateDegree;
    private int MAX_PROGRESS,currentProgress;
    private int mCurrentPointX;
    private ValueAnimator pointTransationAnimator;
    private ValueAnimator loadAnimator;
    private boolean isStop=false;
    public DownloadView_360(Context context) {
        this(context,null);
    }

    public DownloadView_360(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DownloadView_360(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mStatus=Status.Normal;
        MAX_PROGRESS=100;
        currentProgress=20;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DownloadView_360);
        if(typedArray!=null){
            mProgress=typedArray.getColor(R.styleable.DownloadView_360_progress_color, Color.parseColor("#1AC587"));
            mBackgroundColor=typedArray.getColor(R.styleable.DownloadView_360_m_background, Color.parseColor("#81BDAB"));
            mTextColor=typedArray.getColor(R.styleable.DownloadView_360_m_textColor,Color.WHITE);
            mTextSize= typedArray.getDimensionPixelSize(R.styleable.DownloadView_360_m_textSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,15,context.getResources().getDisplayMetrics()));
            mStartDuration=typedArray.getInteger(R.styleable.DownloadView_360_start_duration,500);
            mPreduration=typedArray.getInteger(R.styleable.DownloadView_360_pre_duration,1100);
            mExpandDuration=typedArray.getInteger(R.styleable.DownloadView_360_expand_duration,500);
            mPointTranslationDuration=typedArray.getInteger(R.styleable.DownloadView_360_point_translation_duration,1500);
            mPointRotateDuration=typedArray.getInteger(R.styleable.DownloadView_360_point_rotate_duration,1000);
            mNormalText=typedArray.getString(R.styleable.DownloadView_360_normal_text);
            mCompleteText=typedArray.getString(R.styleable.DownloadView_360_complete_text);
            MAX_PROGRESS=typedArray.getInteger(R.styleable.DownloadView_360_max_progress,100);
            currentProgress=typedArray.getInteger(R.styleable.DownloadView_360_m_progress,0);
            typedArray.recycle();
        }
        mBackPaint=new Paint();
        mBackPaint.setColor(mBackgroundColor);
        mBackPaint.setStyle(Paint.Style.FILL);
        mProPaint=new Paint();
        mProPaint.setColor(mProgress);
        mProPaint.setStyle(Paint.Style.FILL);
        mTextPaint=new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mCirclePaint=new Paint();
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setStyle(Paint.Style.FILL);



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode==MeasureSpec.EXACTLY&&heightMode== MeasureSpec.EXACTLY){
            setMeasuredDimension(widthSize,heightSize);
        }else{
            throw new RuntimeException("you must give a Exactly Dimension!");
        }
        mViewBound = new RectF(0,0,mWidth,mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mCurrentWidth=w;
        mHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mStatus==Status.Normal){
            canvas.drawRoundRect(mViewBound,mHeight/2,mHeight/2,mProPaint);
            //画文字，计算baseline
            Rect textBound = new Rect();
            mTextPaint.getTextBounds(mNormalText,0,mNormalText.length(),textBound);
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            int halfViewHeight=mHeight/2;
            int textHeight = textBound.bottom-textBound.top;
            int baseLine = halfViewHeight-(textBound.top+textHeight/2);
            canvas.drawText(mNormalText,mWidth/2,baseLine,mTextPaint);
        }else if(mStatus== Status.Start){
            float startX= (float) (mWidth*1.0/2-mCurrentWidth*1.0/2);
            canvas.drawRoundRect(startX,0,startX+mCurrentWidth,mHeight,mHeight/2,mHeight/2,mProPaint);
        }else if(mStatus==Status.Pre){
            canvas.save();
            canvas.drawCircle(mWidth/2,mHeight/2,mHeight/2,mProPaint);
            canvas.rotate(mRotateDegree,mWidth/2,mHeight/2);
            drawCircleMethod(canvas,mWidth/2);


            canvas.restore();
        }else if(mStatus==Status.Expand){
            float startX= (float) (mWidth*1.0/2-mCurrentWidth*1.0/2);
            canvas.drawRoundRect(startX,0,startX+mCurrentWidth,mHeight,mHeight/2,mHeight/2,mProPaint);
            drawCircleMethod(canvas, (int) (mWidth-startX-mHeight/2));
        }else if(mStatus==Status.Load){
            canvas.drawRoundRect(mViewBound,mHeight/2,mHeight/2,mBackPaint);

            int pointXInterval=0;
            //画点
            for (int i = 0; i < 4; i++) {
                if(mCurrentPointX-pointXInterval<0){
                    continue;
                }
                int pointDegree= (int) (-(mCurrentPointX-pointXInterval)*1.0/(mWidth-mHeight)*540);
                int pointOffsetY= (int) (Math.sin(Math.toRadians(pointDegree))*(mHeight/2-12));
                canvas.drawCircle(mWidth-mHeight-mCurrentPointX+pointXInterval,mHeight/2-pointOffsetY,mRadius[i],mCirclePaint);
                pointXInterval+=50;
            }
            //画进度
            float percent= (float) (currentProgress*1.0/MAX_PROGRESS);
            canvas.save();
            canvas.clipRect(0,0,mWidth*percent,mHeight);
            canvas.drawRoundRect(mViewBound,mHeight/2,mHeight/2,mProPaint);
            canvas.restore();
            //画圆和旋转的点
            canvas.drawCircle(mWidth-mHeight/2,mHeight/2,mHeight/2,mProPaint);
            canvas.save();
            canvas.rotate(mRotateDegree,mWidth-mHeight/2,mHeight/2);
            //旋转围绕的圆的半径
            int radius=mHeight/2/5*4;

            float degree=0f;
            for (int i = 0; i < 5; i++) {

                float xOffset= (float) (Math.sin(Math.toRadians(degree))*radius);
                float yOffset= (float) (Math.cos(Math.toRadians(degree))*radius);
                canvas.drawCircle(mWidth-mHeight/2+xOffset,mHeight/2-yOffset, (float) (i*1.5+4),mCirclePaint);
                degree= (float) (degree+22.5);
            }
            canvas.restore();
            //画文字，计算baseline
            Rect textBound = new Rect();
            String progressStr=currentProgress*100/MAX_PROGRESS+"%";
            mTextPaint.getTextBounds(progressStr,0,progressStr.length(),textBound);
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            int halfViewHeight=mHeight/2;
            int textHeight = textBound.bottom-textBound.top;
            int baseLine = halfViewHeight-(textBound.top+textHeight/2);
            canvas.drawText(progressStr,mWidth/2,baseLine,mTextPaint);
        }else if(mStatus==Status.Complete){
            canvas.drawRoundRect(mViewBound,mHeight/2,mHeight/2,mProPaint);
            //画文字，计算baseline
            Rect textBound = new Rect();

            mTextPaint.getTextBounds(mCompleteText,0,mCompleteText.length(),textBound);
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            int halfViewHeight=mHeight/2;
            int textHeight = textBound.bottom-textBound.top;
            int baseLine = halfViewHeight-(textBound.top+textHeight/2);
            canvas.drawText(mCompleteText,mWidth/2,baseLine,mTextPaint);
        }
    }

    /**
     * 画园
     * @param canvas
     * @param cx 中间稍大的圆的圆心x值
     */
    private void drawCircleMethod(Canvas canvas,int cx) {
        //中间圆点稍大的直径为view高度的三分之一，
        float radius1= (float) (mHeight*1.0/3/2);
        //稍小圆点的半径
        float radius2= (float) (radius1/1.5);

        canvas.drawCircle(cx,mHeight/2,radius1,mCirclePaint);
        canvas.drawCircle(cx,mHeight/2-radius1,radius2,mCirclePaint);
        float xOffset= (float) (Math.cos(Math.toRadians(30))*radius1);
        float yOffset = (float) (Math.sin(Math.toRadians(30))*radius1);
        canvas.drawCircle(cx-xOffset,mHeight/2+yOffset,radius2,mCirclePaint);
        canvas.drawCircle(cx+xOffset,mHeight/2+yOffset,radius2,mCirclePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mStatus==Status.Normal){
            if(event.getAction()==MotionEvent.ACTION_UP){

                //执行开始状态的动画
                goToStartAnimator();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void goToStartAnimator(){
        mStatus=Status.Start;
        ValueAnimator startAnimator = ValueAnimator.ofInt(mWidth, mHeight);
        startAnimator.setDuration(mStartDuration);
        startAnimator.setInterpolator(new LinearInterpolator());
        startAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentWidth= (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        startAnimator.start();
        startAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //切换到pre状态，开始动画
                goToPreAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }
    private void goToPreAnimator(){
        mStatus=Status.Pre;
        int repeatCount=mPreduration/cicleRotateDuration;
        int i = mPreduration%cicleRotateDuration;
        if(i!=0){
            repeatCount++;
        }
        ValueAnimator preAnimator = ValueAnimator.ofFloat(0, 1.0f);
        preAnimator.setDuration(cicleRotateDuration).setRepeatMode(ValueAnimator.RESTART);
        preAnimator.setRepeatCount(repeatCount);
        preAnimator.setInterpolator(new LinearInterpolator());
        preAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mRotateDegree=(float)valueAnimator.getAnimatedValue()*360;
                invalidate();
            }
        });
        preAnimator.start();
        preAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                goToExpandAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
    private void goToExpandAnimator(){
        mStatus=Status.Expand;
        ValueAnimator expandAnimator = ValueAnimator.ofInt(mHeight, mWidth);
        expandAnimator.setDuration(mExpandDuration)
                .setInterpolator(new LinearInterpolator());
        expandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentWidth= (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        expandAnimator.start();
        expandAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                goToLoadAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
    private void goToLoadAnimator(){
        mStatus=Status.Load;
        loadAnimator = ValueAnimator.ofInt(0, 60);
        loadAnimator.setDuration(mPointRotateDuration).setRepeatMode(ValueAnimator.RESTART);
        loadAnimator.setRepeatCount(ValueAnimator.INFINITE);
        loadAnimator.setInterpolator(new LinearInterpolator());
        loadAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mRotateDegree=(int)valueAnimator.getAnimatedValue()*6;
                invalidate();
            }
        });
        loadAnimator.start();
        pointTransationAnimator = ValueAnimator.ofInt(0, mWidth - mHeight);
        pointTransationAnimator.setDuration(mPointTranslationDuration).setRepeatMode(ValueAnimator.RESTART);
        pointTransationAnimator.setRepeatCount(ValueAnimator.INFINITE);
        pointTransationAnimator.setInterpolator(new LinearInterpolator());
        pointTransationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentPointX= (int) valueAnimator.getAnimatedValue();
            }
        });
        pointTransationAnimator.start();

    }
    public void setProgress(int progress){
        if(mStatus!=Status.Load){
            throw new RuntimeException("you can't call setProgress method unless current status is load!");
        }
        if(currentProgress==progress){
            return;
        }
        if(isStop==true){
            Log.w("DownloadView_360","you can't call setProgress method after you call pause method!");
            return;
        }
        currentProgress=progress;
        if(currentProgress==MAX_PROGRESS){
            if(pointTransationAnimator!=null&&pointTransationAnimator.isRunning()){
                pointTransationAnimator.cancel();
            }
            if(loadAnimator!=null&&loadAnimator.isRunning()){
                loadAnimator.cancel();
            }
            mStatus= Status.Complete;
            invalidate();
        }
    }
    //暂停
    public void pause(){
        isStop=true;
        if(mStatus==Status.Load){
            if(pointTransationAnimator!=null&&pointTransationAnimator.isRunning()){
                pointTransationAnimator.pause();
            }
            if(loadAnimator!=null&&loadAnimator.isRunning()){
                loadAnimator.pause();
            }
        }
    }
    public void reStart(){
        isStop=false;
        if(mStatus==Status.Load){
            if(pointTransationAnimator!=null&&pointTransationAnimator.isPaused()){
                pointTransationAnimator.start();
            }
            if(loadAnimator!=null&&loadAnimator.isRunning()){
                loadAnimator.start();
            }
        }
    }

    //状态
    public enum Status{
        Normal,Start,Pre,Expand,Load,Complete;
    }
}
