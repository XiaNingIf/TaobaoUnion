package com.jit.taobaounion.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jit.taobaounion.R;
import com.jit.taobaounion.model.domain.SelectedContent;
import com.jit.taobaounion.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectedPageContentAdapter extends RecyclerView.Adapter<SelectedPageContentAdapter.InnerHolder> {

    private List<SelectedContent.DataBean.TbkUatmFavoritesItemGetResponseBean.ResultsBean.UatmTbkItemBean> mData = new ArrayList<>();
    private OnSelectedPageContentItemClickListener mContentItemClickListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_page_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        SelectedContent.DataBean.TbkUatmFavoritesItemGetResponseBean.ResultsBean.UatmTbkItemBean itemData = mData.get(position);
        holder.setData(itemData);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContentItemClickListener != null) {
                    mContentItemClickListener.onContentItemClick(itemData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(@NotNull SelectedContent content) {
        if (content.getCode() == Constants.SUCCESS_CODE){
            List<SelectedContent.DataBean.TbkUatmFavoritesItemGetResponseBean.ResultsBean.UatmTbkItemBean> uatm_tbk_item = content.getData().getTbk_uatm_favorites_item_get_response().getResults().getUatm_tbk_item();
            this.mData.clear();
            this.mData.addAll(uatm_tbk_item);
            notifyDataSetChanged();
        }
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.selected_cover)
        ImageView cover;

        @BindView(R.id.selected_off_prise)
        TextView offPriceTv;

        @BindView(R.id.selected_title)
        TextView title;

        @BindView(R.id.selected_buy_btn)
        TextView buyBtn;

        @BindView(R.id.selected_original_prise)
        TextView originalPriseTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(@NotNull SelectedContent.DataBean.TbkUatmFavoritesItemGetResponseBean.ResultsBean.UatmTbkItemBean itemData) {
            title.setText(itemData.getTitle());
            String pict_url = itemData.getPict_url();
            Glide.with(itemView.getContext()).load(pict_url).into(cover);
            if (TextUtils.isEmpty(itemData.getCoupon_click_url())){
                originalPriseTv.setText("晚啦，没有优惠券了");
                buyBtn.setVisibility(View.GONE);
            }else{
                buyBtn.setVisibility(View.VISIBLE);
                originalPriseTv.setText("原价：" + itemData.getZk_final_price());
            }

            if (TextUtils.isEmpty(itemData.getCoupon_info())) {
                offPriceTv.setVisibility(View.GONE);
            }else{
                offPriceTv.setVisibility(View.VISIBLE);
                offPriceTv.setText(itemData.getCoupon_info());
            }
        }
    }

    public void setOnSelectedPageContentItemClickListener(OnSelectedPageContentItemClickListener listener){
        this.mContentItemClickListener = listener;
    }

    public interface OnSelectedPageContentItemClickListener{
        void onContentItemClick(SelectedContent.DataBean.TbkUatmFavoritesItemGetResponseBean.ResultsBean.UatmTbkItemBean item);
    }
}
