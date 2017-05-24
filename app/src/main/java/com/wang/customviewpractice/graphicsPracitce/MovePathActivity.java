package com.wang.customviewpractice.graphicsPracitce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wang.customviewpractice.R;
import com.wang.customviewpractice.graphicsPracitce.view.MovePathView;

public class MovePathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_path);
        final MovePathView view1 = (MovePathView) findViewById(R.id.mv_1);
        findViewById(R.id.bt_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.reset();
            }
        });
    }
}
