package com.wang.customviewpractice.animatePractice;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.wang.customviewpractice.R;

import java.util.ArrayList;
import java.util.List;

public class LayoutAnimationPractice extends AppCompatActivity {
    private ListView lvPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation_practice);
        lvPic= (ListView) findViewById(R.id.lv_pic);
        List<Drawable> listData = new ArrayList<>();
        listData.add(getResources().getDrawable(R.mipmap.pic1,null));
        listData.add(getResources().getDrawable(R.mipmap.pic2,null));
        listData.add(getResources().getDrawable(R.mipmap.pic3,null));
        listData.add(getResources().getDrawable(R.mipmap.pic4,null));
        listData.add(getResources().getDrawable(R.mipmap.pic5,null));
        listData.add(getResources().getDrawable(R.mipmap.pic6,null));
        listData.add(getResources().getDrawable(R.mipmap.pic7,null));
        listData.add(getResources().getDrawable(R.mipmap.pic8,null));
        listData.add(getResources().getDrawable(R.mipmap.pic9,null));
        MyAdapter adapter = new MyAdapter(this,listData,lvPic);
        lvPic.setAdapter(adapter);
    }
}
