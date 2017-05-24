package com.wang.customviewpractice.animatePractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.wang.customviewpractice.R;

public class AnimationPractice extends AppCompatActivity implements View.OnClickListener{
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_practice);
        findViewById(R.id.bt_scale_anim).setOnClickListener(this);
        findViewById(R.id.bt_rotate_anim).setOnClickListener(this);
        findViewById(R.id.bt_translate_anim).setOnClickListener(this);
        findViewById(R.id.bt_alpha_anim).setOnClickListener(this);
        findViewById(R.id.bt_set_anim).setOnClickListener(this);
        textView= (TextView) findViewById(R.id.tv_anim);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_scale_anim:
                    start(R.anim.scale_anim);
                break;
            case R.id.bt_rotate_anim:
                start(R.anim.rotate_anim);
                break;
            case R.id.bt_translate_anim:
                start(R.anim.translate_anim);
                break;
            case R.id.bt_alpha_anim:
                start(R.anim.alpha_anim);
                break;
            case R.id.bt_set_anim:
                start(R.anim.set_anim);
                break;
        }
    }
    private void start(int animationId){
        Animation animation = AnimationUtils.loadAnimation(this, animationId);
        textView.startAnimation(animation);

    }
}
