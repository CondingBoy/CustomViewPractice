package com.wang.customviewpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wang.customviewpractice.drawingofview.WaveView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WaveView waveView = (WaveView) findViewById(R.id.wv);
        waveView.startAnimator();
        Toast.makeText(MainActivity.this, "abc2", Toast.LENGTH_SHORT).show();
    }
}
