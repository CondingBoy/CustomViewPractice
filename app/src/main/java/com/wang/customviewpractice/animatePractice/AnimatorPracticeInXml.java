package com.wang.customviewpractice.animatePractice;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wang.customviewpractice.R;

public class AnimatorPracticeInXml extends AppCompatActivity {
    private TextView tvAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_practice_in_xml);
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator();
            }
        });
        tvAnimator= (TextView) findViewById(R.id.tv_animator);
    }

    private void doAnimator() {
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.translation_animator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int offset = (int) valueAnimator.getAnimatedValue();
                tvAnimator.layout(offset,offset,tvAnimator.getWidth()+offset,tvAnimator.getHeight()+offset);
            }
        });
        animator.start();

    }
    private void doAnimator2(){
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.transation_objanimator);
        objectAnimator.setTarget(tvAnimator);
        objectAnimator.start();
    }
    private void doAnimator3(){
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.color_animator);
        objectAnimator.setTarget(tvAnimator);
        objectAnimator.start();
    }
    private void doAnimator4(){
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.translation_set);
        animatorSet.setTarget(tvAnimator);
        animatorSet.start();
    }
    private void doAnimator5(){
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.translation_set2);
        animatorSet.setTarget(tvAnimator);
        animatorSet.start();
    }
}
