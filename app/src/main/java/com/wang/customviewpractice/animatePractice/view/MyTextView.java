package com.wang.customviewpractice.animatePractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by wangdachui on 2017/4/27.
 */

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setCharText(Character charText){
        setText(charText.toString());
    }
}
