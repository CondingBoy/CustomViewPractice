package com.wang.customviewpractice.animatePractice.view;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.wang.customviewpractice.animatePractice.PointBean;

/**
 * Created by wangdachui on 2017/4/25.
 */

public class MyPointView extends View {
    private PointBean pointBean=new PointBean(100);
    public MyPointView(Context context) {
        super(context);
    }

    public MyPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(pointBean!=null){
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            canvas.drawCircle(500,500,pointBean.getRadius(),paint);
        }
    }

    public void doAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvanuator(), new PointBean(200), new PointBean(300));
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointBean pb = (PointBean) valueAnimator.getAnimatedValue();
                pointBean=pb;
                invalidate();
            }
        });
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();
    }
    private class PointEvanuator implements TypeEvaluator<PointBean>{

        @Override
        public PointBean evaluate(float v, PointBean pointBean, PointBean t1) {
            int startRadius = pointBean.getRadius();
            int endRadius= t1.getRadius();
            int nowradius =(int)(startRadius+(endRadius-startRadius)*v);
            return new PointBean(nowradius);
        }
    }

    public void setRadius(int radius){
        pointBean.setRadius(radius);
        invalidate();
    }
    public int getRadius(){
        return 50;
    }
    public void setPointBean(PointBean pointBean){
        this.pointBean=pointBean;
        invalidate();
    }
    public PointBean getPointBean(){
        return new PointBean(50);
    }
}
