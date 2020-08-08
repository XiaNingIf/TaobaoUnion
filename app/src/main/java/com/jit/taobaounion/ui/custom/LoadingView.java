package com.jit.taobaounion.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.jit.taobaounion.R;

public class LoadingView extends AppCompatImageView {
    private float mDegree = 0;
    private boolean mNeedRotate = true;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageResource(R.mipmap.loading);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startRotate();
    }

    private void startRotate() {
        mNeedRotate = true;
        post(new Runnable() {
            @Override
            public void run() {
                mDegree += 10;
                if (mDegree>360) {
                    mDegree=0;
                }
                invalidate();
                //判断是否要继续循环
                //如果不可见或者已经DetachedFromWindow就不再转动
                if (getVisibility() != VISIBLE && !mNeedRotate) {
                    removeCallbacks(this);
                }else{
                    postDelayed(this,10);
                }
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopRotate();
    }

    private void stopRotate() {
        mNeedRotate = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(mDegree,getWidth()/2,getHeight()/2);
        super.onDraw(canvas);
    }
}
