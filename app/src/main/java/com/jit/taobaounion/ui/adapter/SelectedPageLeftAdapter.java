package com.jit.taobaounion.ui.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.jit.taobaounion.R;
import com.jit.taobaounion.model.domain.SelectedPageCategory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SelectedPageLeftAdapter extends RecyclerView.Adapter<SelectedPageLeftAdapter.InnerHolder> {

    private List<SelectedPageCategory.DataBean> mData = new ArrayList<>();

    private int mCurrentSelectedPosition = 0;
    private onLeftItemClickListener mItemClickListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_page_left, parent, false);
        return new InnerHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        TextView itemTv = holder.itemView.findViewById(R.id.left_category_tv);
        if (mCurrentSelectedPosition == position) {
            itemTv.setBackgroundColor(itemTv.getResources().getColor(R.color.colorEFEEEE,null));
        }else{
            itemTv.setBackgroundColor(itemTv.getResources().getColor(R.color.white,null));
        }
        SelectedPageCategory.DataBean dataBean = mData.get(position);
        itemTv.setText(dataBean.getFavorites_title());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null&&mCurrentSelectedPosition != position) {
                    //修改当前选中的位置
                    mCurrentSelectedPosition = position;
                    mItemClickListener.onLeftItemClick(dataBean);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 设置数据
     * @param categories
     */
    public void setData(@NotNull SelectedPageCategory categories) {
        List<SelectedPageCategory.DataBean> data = categories.getData();
        if (data != null) {
            this.mData.clear();
            this.mData.addAll(data);
            notifyDataSetChanged();
        }
        if (mData.size()>0){
            mItemClickListener.onLeftItemClick(mData.get(mCurrentSelectedPosition));
        }
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setonLeftItemClickListener(onLeftItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface onLeftItemClickListener{
        void onLeftItemClick(SelectedPageCategory.DataBean item);
    }
}
