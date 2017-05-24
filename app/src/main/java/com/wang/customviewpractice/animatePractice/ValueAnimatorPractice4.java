package com.wang.customviewpractice.animatePractice;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.wang.customviewpractice.R;
import com.wang.customviewpractice.animatePractice.view.MyPointView;

public class ValueAnimatorPractice4 extends AppCompatActivity {

    public ValueAnimator valueAnimator ;
    public TextView tvAnimator;
    public MyPointView myPointView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_practice4);
        tvAnimator = (TextView) findViewById(R.id.tv_animator);
        myPointView= (MyPointView) findViewById(R.id.pointview);
        findViewById(R.id.bt_start2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator();
            }
        });
        findViewById(R.id.bt_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPointView.doAnimator();
            }
        });
    }

    private void cancleAnimator() {

    }

    private void doAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvanuate(), new Character('A'), new Character('Z'));
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                char c = (char) valueAnimator.getAnimatedValue();
                tvAnimator.setText(String.valueOf(c));
            }
        });
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.start();
    }
    private class CharEvanuate implements TypeEvaluator<Character>{

        @Override
        public Character evaluate(float v, Character character, Character t1) {
            int startInt = (int)character;
            int endInt = (int)t1;
            char c = (char) (startInt+(endInt-startInt)*v);
            return c;
        }
    }
}
