package com.jit.taobaounion.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseFragment;
import com.jit.taobaounion.model.domain.OnSellContent;
import com.jit.taobaounion.presenter.IOnSellPagePresenter;
import com.jit.taobaounion.presenter.ITicketPresenter;
import com.jit.taobaounion.ui.activity.TicketActivity;
import com.jit.taobaounion.ui.adapter.OnSellContentAdapter;
import com.jit.taobaounion.utils.PresenterManager;
import com.jit.taobaounion.utils.SizeUtils;
import com.jit.taobaounion.utils.TicketUtil;
import com.jit.taobaounion.utils.ToastUtil;
import com.jit.taobaounion.view.IOnSellPageCallback;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;

public class OnSellFragment extends BaseFragment implements IOnSellPageCallback, OnSellContentAdapter.OnSellPageItemClickListener {

    private IOnSellPagePresenter mOnSellPagePresenter;

    public static final int DEFAULT_SPAN_COUNT = 2;

    @BindView(R.id.on_sell_content_list)
    RecyclerView mContentRv;

    @BindView(R.id.on_sell_refresh_layout)
    TwinklingRefreshLayout mTwinklingRefreshLayout;

    @BindView(R.id.fragment_bar_title_tv)
    TextView barTitleTv;

    private OnSellContentAdapter mOnSellContentAdapter;

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mOnSellPagePresenter = PresenterManager.getInstance().getOnSellPagePresenter();
        mOnSellPagePresenter.registerViewCallback(this);
        mOnSellPagePresenter.getOnSellContent();
    }

    @Override
    protected void release() {
        super.release();
        if (mOnSellPagePresenter != null) {
            mOnSellPagePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_with_bar_layout,container,false);
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_on_sell;
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTwinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                //去加载更多
                if (mOnSellPagePresenter != null) {
                    mOnSellPagePresenter.loaderMore();
                }
            }
        });
        mOnSellContentAdapter.setOnSellPageItemClickListener(this);
    }

    @Override
    protected void initView(View rootView) {
        mOnSellContentAdapter = new OnSellContentAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),DEFAULT_SPAN_COUNT);
        mContentRv.setLayoutManager(gridLayoutManager);
        mContentRv.setAdapter(mOnSellContentAdapter);
        mContentRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(getContext(),2.5f);
                outRect.bottom = SizeUtils.dip2px(getContext(),2.5f);
                outRect.right = SizeUtils.dip2px(getContext(),2.5f);
                outRect.left = SizeUtils.dip2px(getContext(),2.5f);
            }
        });
        barTitleTv.setText("特惠宝贝");

        mTwinklingRefreshLayout.setEnableLoadmore(true);
        mTwinklingRefreshLayout.setEnableRefresh(false);
        mTwinklingRefreshLayout.setEnableOverScroll(true);
    }

    @Override
    public void onContentLoadedSuccess(OnSellContent result) {
        //数据回来了
        setUpstate(State.SUCCESS);
        //更新UI
        mOnSellContentAdapter.setData(result);
    }

    @Override
    public void onMoreLoaded(OnSellContent moreResult) {
        mTwinklingRefreshLayout.finishLoadmore();
        int size = moreResult.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data().size();
        ToastUtil.showToast("加载了" + size + "个宝贝");
        //添加内容到适配器里
        mOnSellContentAdapter.onMoreLoaded(moreResult);
    }

    @Override
    public void onMoreLoadedError() {
        mTwinklingRefreshLayout.finishLoadmore();
        ToastUtil.showToast("网络异常，请稍后重试...");
    }

    @Override
    public void onMoreLoadedEmpty() {
        mTwinklingRefreshLayout.finishLoadmore();
        ToastUtil.showToast("没有更多内容...");
    }

    @Override
    public void onError() {
        setUpstate(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpstate(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpstate(State.EMPTY);
    }

    @Override
    public void onSellItemClick(OnSellContent.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean item) {
        TicketUtil.toTicketPage(getContext(),item);
    }
}
