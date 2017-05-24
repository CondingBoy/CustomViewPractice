package com.wang.customviewpractice.graphicsPracitce.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wangdachui on 2017/5/16.
 */

public class MovePathView extends View {
    private Path path = new Path();
    private float preX,preY;
    public MovePathView(Context context) {
        super(context);
    }

    public MovePathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovePathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //返回true，接收后续事件
                path.moveTo(event.getX(),event.getY());
                preX=event.getX();
                preY=event.getY();
                return true;
            default:
//                path.lineTo(event.getX(),event.getY());
                float endX = (preX+event.getX())/2;
                float endY = (preY+event.getY())/2;
                path.quadTo(preX,preY,endX,endY);
                preX=event.getX();
                preY=event.getY();
        }
        invalidate();
        return super.onTouchEvent(event);
    }
    public void reset(){
        path.reset();
        invalidate();
    }
}
