package com.wang.customviewpractice.animatePractice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.customviewpractice.R;

import java.util.List;

/**
 * Created by wangdachui on 2017/5/7.
 */

public class MyAdapter extends BaseAdapter {
    private List<Drawable> mData;
    private LayoutInflater mInflater;
    private ListView lv;
    private Animation animation ;
    private boolean isScrollUp;
    private int mFirstVisiableItem,mTop;
    public MyAdapter(Context context, List<Drawable> data,ListView listView){
       mInflater= LayoutInflater.from(context);
        mData = data;
        lv=listView;
        animation = AnimationUtils.loadAnimation(context, R.anim.item_animation);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                View childAt = absListView.getChildAt(0);
                if(childAt==null){
                    return;
                }
                int top = childAt.getTop();
                isScrollUp=i>mFirstVisiableItem||mTop>top;
                mFirstVisiableItem=i;
                mTop=top;
            }
        });
    }
    @Override
    public int getCount() {
        return 200;
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i%mData.size());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder= null;
        if(view==null){
          view = mInflater.inflate(R.layout.list_item,viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
            view.clearAnimation();//清楚掉复用view的动画
        }
        Drawable item = (Drawable) getItem(i);
        viewHolder.textView.setText(i+"");
        viewHolder.imageView.setImageDrawable(item);
        for (int j = 0; j < viewGroup.getChildCount(); j++) {
            View childAt = viewGroup.getChildAt(j);
            childAt.clearAnimation();
        }
        if(isScrollUp){

        view.startAnimation(animation);
        }
        return view;
    }

     private static class ViewHolder{
         public View mRootView;
         public ImageView imageView;
         public TextView textView;
         public ViewHolder(View rootView){
            imageView= (ImageView) rootView.findViewById(R.id.iv_pic);
            textView= (TextView) rootView.findViewById(R.id.tv_index);
            mRootView=rootView;
        }
    }

}
