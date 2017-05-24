package com.wang.customviewpractice.animatePractice;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.customviewpractice.R;

public class AnimatorSetDemo1 extends AppCompatActivity implements View.OnClickListener{
    private TextView tvMenu,tvItem1,tvItem2,tvItem3,tvItem4,tvItem5;
    private boolean isMenuOpen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set_demo1);
        tvMenu= (TextView) findViewById(R.id.tv_menu);
        tvMenu.setOnClickListener(this);
        tvItem1= (TextView) findViewById(R.id.tv_item1);
        tvItem1.setOnClickListener(this);
        tvItem2= (TextView) findViewById(R.id.tv_item2);
        tvItem2.setOnClickListener(this);
        tvItem3= (TextView) findViewById(R.id.tv_item3);
        tvItem3.setOnClickListener(this);
        tvItem4= (TextView) findViewById(R.id.tv_item4);
        tvItem4.setOnClickListener(this);
        tvItem5= (TextView) findViewById(R.id.tv_item5);
        tvItem5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tv_menu){
            if(isMenuOpen){
                isMenuOpen=false;
                doAnimatorClose(tvItem1,0,5,500);
                doAnimatorClose(tvItem2,1,5,500);
                doAnimatorClose(tvItem3,2,5,500);
                doAnimatorClose(tvItem4,3,5,500);
                doAnimatorClose(tvItem5,4,5,500);
            }else{
                isMenuOpen=true;
                doAnimatorOpen(tvItem1,0,5,500);
                doAnimatorOpen(tvItem2,1,5,500);
                doAnimatorOpen(tvItem3,2,5,500);
                doAnimatorOpen(tvItem4,3,5,500);
                doAnimatorOpen(tvItem5,4,5,500);
            }
        }else{
            Toast.makeText(this, "你点击了："+view, Toast.LENGTH_SHORT).show();
        }
    }
    private void doAnimatorClose(View view,int index,int total,int radius){
        double degree = Math.toRadians(90)/(total-1)*index;
        int translationX = -(int) (Math.sin(degree)*radius);
        int translationY = -(int) (Math.cos(degree)*radius);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view,"translationX",translationX,0),
                ObjectAnimator.ofFloat(view,"translationY",translationY,0),
                ObjectAnimator.ofFloat(view,"scaleX",1f,0f),
                ObjectAnimator.ofFloat(view,"scaleY",1f,0f),
                ObjectAnimator.ofFloat(view,"alpha",1f,0f)
        );
        animatorSet.setDuration(1000).start();

    }
    private void doAnimatorOpen(View view,int index,int total,int radius){
        double degree = Math.toRadians(90)/(total-1)*index;
        int translationX = -(int) (Math.sin(degree)*radius);
        int translationY = -(int) (Math.cos(degree)*radius);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view,"translationX",0,translationX),
                ObjectAnimator.ofFloat(view,"translationY",0,translationY),
                ObjectAnimator.ofFloat(view,"scaleX",0f,1f),
                ObjectAnimator.ofFloat(view,"scaleY",0f,1f),
                ObjectAnimator.ofFloat(view,"alpha",0f,1f)
        );
        animatorSet.setDuration(1000).start();
    }

}
