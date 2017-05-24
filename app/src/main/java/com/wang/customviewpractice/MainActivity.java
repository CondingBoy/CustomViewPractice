package com.wang.customviewpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wang.customviewpractice.animatePractice.AnimatePracticeList;
import com.wang.customviewpractice.drawPractice.DrawTextTest;
import com.wang.customviewpractice.drawingofview.WaveView;
import com.wang.customviewpractice.graphicsPracitce.GraphicsListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WaveView waveView = (WaveView) findViewById(R.id.wv);
        waveView.startAnimator();
        Button bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GraphicsListActivity.class));
            }
        });
    }
}
