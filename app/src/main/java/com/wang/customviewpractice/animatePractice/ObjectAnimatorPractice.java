package com.wang.customviewpractice.animatePractice;

import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.wang.customviewpractice.R;

import org.w3c.dom.Text;

/**
 * 练习通过propertyValueholder和Keyfram创创建属性动画的实例
 */
public class ObjectAnimatorPractice extends AppCompatActivity {
    private TextView tvAnimator;
    private TextView tvAnimator2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator_practice);
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator6();
//                doAnimator4();
//                doAnimator3();
//                doAnimator();
            }


        });
        findViewById(R.id.bt_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimator5();
//                doAnimator2();
            }
        });
        tvAnimator= (TextView) findViewById(R.id.tv_animator);
        tvAnimator2= (TextView) findViewById(R.id.tv_animator2);

    }

    private void doAnimator2() {
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofObject("charText", new CharacterEvanuator(), 'A', 'Z');
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvAnimator2, propertyValuesHolder);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
    }

    private void doAnimator() {
        //通过propertyValueHolder来创建实例
        PropertyValuesHolder backgroundColor = PropertyValuesHolder.ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
//        backgroundColor.setEvaluator(new ArgbEvaluator());
        PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("Rotation", 60, -60, 40, -40, 20, -20, 0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvAnimator, backgroundColor, rotation);
        objectAnimator.setDuration(1500);
        objectAnimator.start();

    }
    private void doAnimator3(){
        //通过keyFram来创建实例
        Keyframe keyframe1 = Keyframe.ofFloat(0f, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.1f, 20);
        Keyframe keyframe3 = Keyframe.ofFloat(0.2f, -20);
        Keyframe keyframe4 = Keyframe.ofFloat(0.3f, 20);
        Keyframe keyframe5 = Keyframe.ofFloat(0.4f, -20);
        Keyframe keyframe6 = Keyframe.ofFloat(0.5f, 20);
        Keyframe keyframe7 = Keyframe.ofFloat(0.6f, -20);
        Keyframe keyframe8 = Keyframe.ofFloat(0.7f, 20);
        Keyframe keyframe9 = Keyframe.ofFloat(0.8f, -20);
        Keyframe keyframe10 = Keyframe.ofFloat(0.9f, 20);
        Keyframe keyframe11 = Keyframe.ofFloat(1f, 0);
        PropertyValuesHolder rotation = PropertyValuesHolder.ofKeyframe("rotation", keyframe1, keyframe2, keyframe3, keyframe4, keyframe5, keyframe6
                , keyframe7, keyframe8, keyframe9, keyframe10, keyframe11);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvAnimator, rotation);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
    }
    private void doAnimator4(){
        //给keyfram设置插值器
        Keyframe keyframe = Keyframe.ofFloat(0, 0);
        Keyframe keyframe1 = keyframe.ofFloat(0.5f, 100);
        Keyframe keyframe2 = keyframe.ofFloat(1f, 1);
        keyframe2.setInterpolator(new BounceInterpolator());
        PropertyValuesHolder rotation = PropertyValuesHolder.ofKeyframe("rotation", keyframe, keyframe1, keyframe2);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvAnimator, rotation);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
    }
    private void doAnimator5(){
        Keyframe a = Keyframe.ofObject(0, 'A');
        Keyframe l = Keyframe.ofObject(0.2f, 'L');
        Keyframe z = Keyframe.ofObject(1, 'Z');
        PropertyValuesHolder charText = PropertyValuesHolder.ofKeyframe("CharText", a, l, z);
        charText.setEvaluator(new CharacterEvanuator());
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvAnimator2, charText);
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }
    private void doAnimator6(){
        //通过keyFram来创建实例
        Keyframe keyframe1 = Keyframe.ofFloat(0f, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.1f, 20);
        Keyframe keyframe3 = Keyframe.ofFloat(0.2f, -20);
        Keyframe keyframe4 = Keyframe.ofFloat(0.3f, 20);
        Keyframe keyframe5 = Keyframe.ofFloat(0.4f, -20);
        Keyframe keyframe6 = Keyframe.ofFloat(0.5f, 20);
        Keyframe keyframe7 = Keyframe.ofFloat(0.6f, -20);
        Keyframe keyframe8 = Keyframe.ofFloat(0.7f, 20);
        Keyframe keyframe9 = Keyframe.ofFloat(0.8f, -20);
        Keyframe keyframe10 = Keyframe.ofFloat(0.9f, 20);
        Keyframe keyframe11 = Keyframe.ofFloat(1f, 0);
        PropertyValuesHolder rotation = PropertyValuesHolder.ofKeyframe("rotation", keyframe1, keyframe2, keyframe3, keyframe4, keyframe5, keyframe6
                , keyframe7, keyframe8, keyframe9, keyframe10, keyframe11);

        Keyframe k1 = Keyframe.ofFloat(0f, 1f);
        Keyframe k2 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k3 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k4 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k5 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k6 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k7 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k8 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k9 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k10 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe k11= Keyframe.ofFloat(1f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX", k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY", k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvAnimator, rotation, scaleX, scaleY);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }
    public class CharacterEvanuator implements TypeEvaluator<Character>{

        @Override
        public Character evaluate(float v, Character character, Character t1) {
            int startChar=character;
            int endChar = t1;
            int nowChar = (int) (startChar+(endChar-startChar)*v);
            return Character.valueOf((char) nowChar);
        }
    }
}
