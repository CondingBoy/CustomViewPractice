package com.wang.customviewpractice.graphicsPracitce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.wang.customviewpractice.R;
import com.wang.customviewpractice.animatePractice.AnimatePracticeList;
import com.wang.customviewpractice.animatePractice.AnimationPractice;

public class GraphicsListActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics_list);
        findViewById(R.id.bt_1).setOnClickListener(this);
        findViewById(R.id.bt_2).setOnClickListener(this);
        findViewById(R.id.bt_3).setOnClickListener(this);
        findViewById(R.id.bt_4).setOnClickListener(this);
        findViewById(R.id.bt_5).setOnClickListener(this);
        findViewById(R.id.bt_6).setOnClickListener(this);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                startActivity(new Intent(GraphicsListActivity.this,BasicGraphicsPractice.class));
                break;
            case R.id.bt_2:
                startActivity(new Intent(GraphicsListActivity.this,PathAndTextPractice.class));
                break;
            case R.id.bt_3:
                startActivity(new Intent(GraphicsListActivity.this,MovePathActivity.class));
                break;
            case R.id.bt_4:
                startActivity(new Intent(GraphicsListActivity.this,PaintMethod.class));
                break;
            case R.id.bt_5:
                startActivity(new Intent(GraphicsListActivity.this,ColorMetricsActivity.class));
                break;
            case R.id.bt_6:
                startActivity(new Intent(GraphicsListActivity.this,DownloadViewActivity.class));

                break;
        }
    }
}
