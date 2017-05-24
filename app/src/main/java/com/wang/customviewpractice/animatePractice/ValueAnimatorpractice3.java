package com.wang.customviewpractice.animatePractice;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.wang.customviewpractice.R;

public class ValueAnimatorpractice3 extends AppCompatActivity {

    public ValueAnimator valueAnimator ;
    public TextView tvAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animatorpractice3);
        tvAnimator = (TextView) findViewById(R.id.tv_animator);
        findViewById(R.id.bt_start2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator2();
            }
        });
        findViewById(R.id.bt_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancleAnimator();
            }
        });
    }

    private void cancleAnimator() {
        valueAnimator.cancel();
    }

    private void doAnimator() {
        valueAnimator=ValueAnimator.ofInt(0,400);
        valueAnimator.setDuration(1000);

        valueAnimator.setInterpolator(new MyInterpolator());
        valueAnimator.setEvaluator(new MyEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentV = (int) valueAnimator.getAnimatedValue();
                tvAnimator.layout(tvAnimator.getLeft(),currentV,tvAnimator.getRight(),currentV+tvAnimator.getHeight());
            }
        });
        valueAnimator.start();
    }
    private void doAnimator2() {
        //颜色动画
        valueAnimator=ValueAnimator.ofInt(0xffffff00,0xff0000ff);
        valueAnimator.setDuration(3000);

        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentV = (int) valueAnimator.getAnimatedValue();
                tvAnimator.setBackgroundColor(currentV);
            }
        });
        valueAnimator.start();
    }
    private class MyInterpolator implements Interpolator {



        @Override
        public float getInterpolation(float v) {
            //这里的v是根据动画设置的时间，计算出这段时间各时间点的数值，在0-1之间，线性变化，不会受其他参数影响，只与时间有关
            //返回值根据特定的效果计算出来，可以在0-1之外

            //这样写会让动画在原区间的2倍上执行，例如0->400,会变成400->800
            return 1+v;

        }
    }
    private class MyEvaluator implements TypeEvaluator<Integer> {

        @Override
        public Integer evaluate(float v, Integer integer, Integer t1) {
            //这里的v就是插值器返回的数值,integer是动画的初始值，t1是动画的结束值
            v=v-1;//这样做可以去除MyInterpolator的效果
            int value = (int) (integer+(t1-integer)*v);
            return value;

        }
    }
}
