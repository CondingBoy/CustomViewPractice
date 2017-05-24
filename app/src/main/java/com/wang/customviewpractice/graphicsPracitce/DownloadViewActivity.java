package com.wang.customviewpractice.graphicsPracitce;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;
import android.widget.Toast;

import com.wang.customviewpractice.R;
import com.wang.customviewpractice.view.DownloadView_360;

public class DownloadViewActivity extends AppCompatActivity {

    private DownloadView_360 downloadView_360;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_view);
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        downloadView_360= (DownloadView_360) findViewById(R.id.dl_360);
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProgress();
            }
        });
        findViewById(R.id.tv_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadView_360.pause();
            }
        });
        findViewById(R.id.tv_restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadView_360.reStart();
            }
        });

    }

    public void startProgress() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(downloadView_360, "progress", 0, 100);
        objectAnimator.setDuration(20000).setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }
}
