package com.wang.customviewpractice.animatePractice;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.wang.customviewpractice.R;

public class AnimatorSetPractice extends AppCompatActivity {
    private TextView tvAnimator1,tvAnimator2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set_practice);
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator15();
            }
        });
        tvAnimator1= (TextView) findViewById(R.id.tv_animator1);
        tvAnimator2= (TextView) findViewById(R.id.tv_animator2);
    }

    /**
     * 动画顺序激活
     */
    private void doAnimator() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(backgroundColor,translationY,translationY2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * 动画同时激活
     */
    private void doAnimator2() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(backgroundColor,translationY,translationY2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * 设置一个动画无限重复，同时激活
     */
    private void doAnimator3() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        backgroundColor.setStartDelay(2000);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        //无限循环
        translationY.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(backgroundColor,translationY,translationY2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    /**
     * 设置一个动画无限重复，顺序激活
     */
    private void doAnimator4() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        backgroundColor.setStartDelay(2000);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        translationY.setRepeatCount(ValueAnimator.INFINITE);
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(backgroundColor,translationY,translationY2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    /**
     * 设置动画无限重复，同时激活
     */
    private void doAnimator5() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        backgroundColor.setRepeatCount(ValueAnimator.INFINITE);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        //无限循环
        translationY.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        translationY2.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(backgroundColor,translationY,translationY2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator6() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(backgroundColor);
        builder.with(translationY);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator7() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(backgroundColor);
        builder.with(translationY).after(translationY2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator8() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(translationY);
        builder.with(translationY2).before(backgroundColor);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator9() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        translationY.setDuration(500000000);
        translationY.setInterpolator(new BounceInterpolator());
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        translationY2.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(translationY);
        builder.with(translationY2);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator10() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(backgroundColor);
        builder.with(translationY2);
        animatorSet.setTarget(tvAnimator1);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * 使用builder
     */
    private void doAnimator11() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        translationY2.setStartDelay(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(translationY);
        builder.with(translationY2);
        animatorSet.setStartDelay(2000);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator12() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        translationY2.setStartDelay(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(translationY2);
        builder.with(translationY);
        animatorSet.setStartDelay(2000);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator13() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        translationY.setStartDelay(2000);
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        translationY2.setStartDelay(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(translationY2);
        builder.with(translationY);
        animatorSet.setStartDelay(2000);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator14() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        translationY2.setStartDelay(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(translationY2);
        builder.after(translationY);
        animatorSet.setStartDelay(2000);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
    /**
     * 使用builder
     */
    private void doAnimator15() {
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(tvAnimator2, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator2, "translationY", 0, 300, 0);
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(tvAnimator1, "translationY", 0, 400, 0);
        translationY2.setStartDelay(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(translationY);
        builder.after(translationY2);
        animatorSet.setStartDelay(2000);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
}
