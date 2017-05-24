package com.wang.customviewpractice.animatePractice;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wang.customviewpractice.R;

public class ValueAnimatorPractice2 extends AppCompatActivity {
    public ValueAnimator valueAnimator ;
    public TextView tvAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_practice2);
        tvAnimator = (TextView) findViewById(R.id.tv_animator);
        findViewById(R.id.bt_start2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator();
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
        valueAnimator.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentV = (int) valueAnimator.getAnimatedValue();
                tvAnimator.layout(tvAnimator.getLeft(),currentV,tvAnimator.getRight(),currentV+tvAnimator.getHeight());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.e("methed:","start");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.e("methed:","end");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.e("methed:","cancle");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.e("methed:","repeat");
            }
        });
        valueAnimator.start();
    }
}
