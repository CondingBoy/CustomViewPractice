package com.wang.customviewpractice.animatePractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wang.customviewpractice.R;

public class AnimatePracticeList extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate_practice);
        findViewById(R.id.bt_animation).setOnClickListener(this);
        findViewById(R.id.bt_animator).setOnClickListener(this);
        findViewById(R.id.bt_animator2).setOnClickListener(this);
        findViewById(R.id.bt_animator3).setOnClickListener(this);
        findViewById(R.id.bt_animator4).setOnClickListener(this);
        findViewById(R.id.bt_animator5).setOnClickListener(this);
        findViewById(R.id.bt_animator6).setOnClickListener(this);
        findViewById(R.id.bt_animator7).setOnClickListener(this);
        findViewById(R.id.bt_animator8).setOnClickListener(this);
        findViewById(R.id.bt_animator9).setOnClickListener(this);
        findViewById(R.id.bt_animator10).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_animation:
                startActivity(new Intent(AnimatePracticeList.this,AnimationPractice.class));
                break;
            case R.id.bt_animator:
                startActivity(new Intent(AnimatePracticeList.this,ValueAnimatorPracticeActivity.class));
                break;
            case R.id.bt_animator2:
                startActivity(new Intent(AnimatePracticeList.this,ValueAnimatorPractice2.class));
                break;
            case R.id.bt_animator3:
                startActivity(new Intent(AnimatePracticeList.this,ValueAnimatorpractice3.class));
                break;
            case R.id.bt_animator4:
                startActivity(new Intent(AnimatePracticeList.this,ValueAnimatorPractice4.class));
                break;
            case R.id.bt_animator5:
                startActivity(new Intent(AnimatePracticeList.this,ValueAnimatorPractice5.class));
                break;
            case R.id.bt_animator6:
                startActivity(new Intent(AnimatePracticeList.this,ObjectAnimatorPractice.class));
                break;
            case R.id.bt_animator7:
                startActivity(new Intent(AnimatePracticeList.this,AnimatorSetPractice.class));
                break;
            case R.id.bt_animator8:
                startActivity(new Intent(AnimatePracticeList.this,AnimatorPracticeInXml.class));
                break;
            case R.id.bt_animator9:
                startActivity(new Intent(AnimatePracticeList.this,AnimatorSetDemo1.class));
                break;
            case R.id.bt_animator10:
                startActivity(new Intent(AnimatePracticeList.this,LayoutAnimationPractice.class));
                break;

        }
    }
}
