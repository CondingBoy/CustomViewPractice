package com.wang.customviewpractice.animatePractice;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.customviewpractice.R;

public class ValueAnimatorPracticeActivity extends AppCompatActivity {
    public TextView tvAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_practice);
        tvAnimator = (TextView) findViewById(R.id.tv_animator);
        tvAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ValueAnimatorPracticeActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator();
            }
        });
    }

    private void doAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,600);
        valueAnimator.setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentV = (int) valueAnimator.getAnimatedValue();

//                Toast.makeText(ValueAnimatorPracticeActivity.this, "currentValue:"+currentV, Toast.LENGTH_SHORT).show();
//                Log.e("currentValue:",currentV+"");
                tvAnimator.layout(currentV,currentV,currentV+tvAnimator.getWidth(),currentV+tvAnimator.getHeight());
                Log.e("Translation:","TranslationX:"+tvAnimator.getTranslationX()+",Left:"+tvAnimator.getLeft());

            }
        });
        valueAnimator.start();
    }
}
