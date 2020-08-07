package com.jit.taobaounion.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jit.taobaounion.R;
import com.jit.taobaounion.model.domain.HomePagerContent;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageContentAdapter extends RecyclerView.Adapter<HomePageContentAdapter.InnerHolder> {

    List<HomePagerContent.DataBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LogUtils.d(this,"onCreateViewHolder....");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_pager_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //LogUtils.d(this,"onBindViewHolder...."+position);
        HomePagerContent.DataBean dataBean = mData.get(position);
        holder.setData(dataBean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<HomePagerContent.DataBean> contents) {
        mData.clear();
        mData.addAll(contents);
        notifyDataSetChanged();
    }

    public void addData(List<HomePagerContent.DataBean> contents) {
        //添加之前拿到原来的size
        int olderSize = mData.size();
        mData.addAll(contents);
        //更新UI
        notifyItemRangeChanged(olderSize,contents.size());
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_cover)
        ImageView cover;

        @BindView(R.id.goods_title)
        TextView title;

        @BindView(R.id.goods_off_prise)
        TextView offPriseTv;

        @BindView(R.id.goods_after_off_prise)
        TextView finalPriseTv;

        @BindView(R.id.goods_original_prise)
        TextView originalPriseTv;

        @BindView(R.id.sales_count)
        TextView salesCountTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(HomePagerContent.DataBean dataBean) {
            Context context = itemView.getContext();
            title.setText(dataBean.getTitle());
            ViewGroup.LayoutParams layoutParams = cover.getLayoutParams();
            int width = layoutParams.width;
            int height = layoutParams.height;
            int coverSize = (width>height?width:height);
            //LogUtils.d(HomePageContentAdapter.this,"width---->"+width+",height----->"+height);
            //LogUtils.d(this,"Url====>"+dataBean.getPict_url());
            Glide.with(itemView.getContext()).load(UrlUtils.getCoverPath(dataBean.getPict_url(),coverSize)).into(cover);
            String finalPrice = dataBean.getZk_final_price();
            long couponAmount = dataBean.getCoupon_amount();
            //LogUtils.d(this,"final prise====>" + finalPrice);
            float resultPrise = Float.parseFloat(finalPrice) - couponAmount;
            //LogUtils.d(this,"resultPrise====>" + resultPrise);
            finalPriseTv.setText(String.format("%.2f",resultPrise));
            offPriseTv.setText(String.format(context.getString(R.string.text_goods_off_prise),couponAmount));
            originalPriseTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            originalPriseTv.setText(String.format(context.getString(R.string.text_goods_original_prise),finalPrice));
            salesCountTv.setText(String.format(context.getString(R.string.text_sales_count),dataBean.getVolume()));
        }
    }
}
