package com.wang.customviewpractice.animatePractice;

import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wang.customviewpractice.R;
import com.wang.customviewpractice.animatePractice.view.MyPointView;

public class ValueAnimatorPractice5 extends AppCompatActivity {

    public ValueAnimator valueAnimator ;
    public TextView tvAnimator;
    public MyPointView myPointView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_practice5);
        tvAnimator = (TextView) findViewById(R.id.tv_animator);
        myPointView= (MyPointView) findViewById(R.id.pointview);
        findViewById(R.id.bt_start2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator13();
            }
        });
        findViewById(R.id.bt_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPointView.doAnimator();
            }
        });
    }

    private void doAnimator1() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tvAnimator, "alpha", 1, 0, 1);
        alpha.setDuration(2000);
        alpha.start();
    }
    private void doAnimator2() {
        ObjectAnimator scale = ObjectAnimator.ofFloat(tvAnimator, "Scale", 0, 3, 1);
        scale.setDuration(2000);
        scale.start();
    }
    private void doAnimator3() {
        ObjectAnimator scale = ObjectAnimator.ofFloat(tvAnimator, "ScaleX", 0, 3, 1);
        scale.setDuration(2000);
        scale.start();
    }
    private void doAnimator4() {
        ObjectAnimator scale = ObjectAnimator.ofFloat(tvAnimator, "ScaleY", 0, 3, 1);
        scale.setDuration(2000);
        scale.start();
    }
    private void doAnimator5() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(tvAnimator, "rotation", 0, 180, 0);
        rotation.setDuration(2000);
        rotation.start();
    }
    private void doAnimator6() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(tvAnimator, "rotationX", 0, 180, 0);
        rotation.setDuration(2000);
        rotation.start();
    }
    private void doAnimator7() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(tvAnimator, "rotationY", 0, 180, 0);
        rotation.setDuration(2000);
        rotation.start();
    }

    private void doAnimator8() {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(tvAnimator, "translationX", 0, 180, 0);
        translationX.setDuration(2000);
        translationX.start();
    }
    private void doAnimator9() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvAnimator, "translationY", 0, 180, 0);
        translationY.setDuration(2000);
        translationY.start();
    }
    private void doAnimator10() {
        ObjectAnimator translationY = ObjectAnimator.ofInt(myPointView, "radius", 100, 300, 200);
        translationY.setDuration(2000);
        translationY.start();
    }
    private void doAnimator11() {
        ObjectAnimator translationY = ObjectAnimator.ofInt(myPointView, "radius", 200);
        translationY.setDuration(2000);
        translationY.start();
    }
    private void doAnimator12() {
        ObjectAnimator translationY = ObjectAnimator.ofObject(myPointView,"PointBean",new PointEvanuator(),new PointBean(200),new PointBean(300));
        translationY.setDuration(2000);
        translationY.start();
    }
    private void doAnimator13() {//若只有一个参数，objectAnimator会通过反射调用对应属性的getXXX方法作为开始值，若没有此方法，则object为null，并在PointEvanuator中调用开始的object值时报空指针异常
        ObjectAnimator translationY = ObjectAnimator.ofObject(myPointView,"PointBean",new PointEvanuator(),new PointBean(200));
        translationY.setDuration(2000);
        translationY.start();
    }
    private class PointEvanuator implements TypeEvaluator<PointBean> {

        @Override
        public PointBean evaluate(float v, PointBean pointBean, PointBean t1) {
            int startRadius = pointBean.getRadius();
            int endRadius= t1.getRadius();
            int nowradius =(int)(startRadius+(endRadius-startRadius)*v);
            return new PointBean(nowradius);
        }
    }

}
