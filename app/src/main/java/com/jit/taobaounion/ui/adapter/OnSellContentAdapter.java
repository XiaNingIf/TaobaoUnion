package com.jit.taobaounion.ui.adapter;

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
import com.jit.taobaounion.model.domain.OnSellContent;
import com.jit.taobaounion.utils.UrlUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.reflect.KVariance;

public class OnSellContentAdapter extends RecyclerView.Adapter<OnSellContentAdapter.InnerHolder> {
    private List<OnSellContent.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> mData = new ArrayList<>();
    private OnSellPageItemClickListener mContentItemClickListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_on_sell_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //绑定数据
        OnSellContent.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean mapDataBean = mData.get(position);
        holder.setData(mapDataBean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContentItemClickListener.onSellItemClick(mapDataBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(@NotNull OnSellContent result) {
        this.mData.clear();
        this.mData.addAll(result.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data());
        notifyDataSetChanged();
    }

    /**
     * 加载更多的内容
     *
     * @param moreResult
     */
    public void onMoreLoaded(@NotNull OnSellContent moreResult) {
        List<OnSellContent.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> moreData = moreResult.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data();
        //原来数据的长度
        int oldDataSize = this.mData.size();
        this.mData.addAll(moreData);
        notifyItemRangeChanged(oldDataSize-1,moreData.size());
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.on_sell_cover)
        public ImageView cover;

        @BindView(R.id.on_sell_content_title_tv)
        public TextView titleTv;

        @BindView(R.id.on_sell_origin_prise_tv)
        public TextView originalPriseTv;

        @BindView(R.id.on_sell_off_prise_tv)
        public TextView offPriseTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(@NotNull OnSellContent.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean data){
            titleTv.setText(data.getTitle());
            String coverPath = UrlUtils.getCoverPath(data.getPict_url());
            Glide.with(cover.getContext()).load(coverPath).into(cover);
            String originalPrise = data.getZk_final_price();
            originalPriseTv.setText("￥" + originalPrise + " ");
            originalPriseTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            int couponAmount = data.getCoupon_amount();
            float originalPriseFloat = Float.parseFloat(originalPrise);
            float finalPrise = originalPriseFloat -couponAmount;
            offPriseTv.setText("券后价：" + String.format("%.2f",finalPrise));
        }
    }

    public void setOnSellPageItemClickListener(OnSellPageItemClickListener listener){
        this.mContentItemClickListener = listener;
    }

    public interface OnSellPageItemClickListener{
        void onSellItemClick(OnSellContent.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean data);
    }
}
