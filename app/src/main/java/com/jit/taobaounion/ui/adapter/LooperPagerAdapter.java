package com.jit.taobaounion.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.jit.taobaounion.model.domain.HomePagerContent;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

public class LooperPagerAdapter extends PagerAdapter {
    private List<HomePagerContent.DataBean> data = new ArrayList<>();
    private OnLooperPageItemClickListener mLooperPageItemListener = null;

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public int getDataSize(){
        return data.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //处理越界问题
        int realPosition = position % data.size();
        int measuredHeight = container.getMeasuredHeight();
        int measuredWidth = container.getMeasuredWidth();
        //LogUtils.d(this,"measuredHeight----->"+measuredHeight+",measuredWidth----->"+measuredWidth);
        int ivSize = (measuredHeight>measuredWidth?measuredHeight:measuredWidth)/2;
        HomePagerContent.DataBean dataBean = data.get(realPosition);
        String coverUrl = UrlUtils.getCoverPath(dataBean.getPict_url(),ivSize);
        ImageView iv = new ImageView(container.getContext());
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLooperPageItemListener != null) {
                    HomePagerContent.DataBean item = data.get(realPosition);
                    mLooperPageItemListener.onLooperItemClick(item);
                }
            }
        });
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(container.getContext()).load(coverUrl).into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setData(List<HomePagerContent.DataBean> contents) {
        data.clear();
        data.addAll(contents);
        notifyDataSetChanged();
    }

    public void setOnLooperPageItemClickListener(LooperPagerAdapter.OnLooperPageItemClickListener listener){
        this.mLooperPageItemListener = listener;
    }

    public interface OnLooperPageItemClickListener{
        void onLooperItemClick(HomePagerContent.DataBean item);
    }
}
