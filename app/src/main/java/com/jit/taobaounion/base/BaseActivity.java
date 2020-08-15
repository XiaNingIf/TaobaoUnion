package com.jit.taobaounion.base;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.jit.taobaounion.utils.PresenterManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        //ColorMatrix cm =new ColorMatrix();
        //cm.setSaturation(0);
        //Paint paint = new Paint();
        //paint.setColorFilter(new ColorMatrixColorFilter(cm));
        //View contentContainer = getWindow().getDecorView();
        //contentContainer.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        mBind = ButterKnife.bind(this);
        initView();
        initEvent();
        initPresenter();
    }

    protected abstract void initPresenter();

    protected void initEvent() {
    }

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    /**
     * 子类需要就复写
     */
    protected void release() {
    }

    protected abstract int getLayoutResId();
}
