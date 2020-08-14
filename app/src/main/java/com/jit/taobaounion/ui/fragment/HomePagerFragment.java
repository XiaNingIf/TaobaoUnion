package com.jit.taobaounion.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseFragment;
import com.jit.taobaounion.model.domain.Categories;
import com.jit.taobaounion.model.domain.HomePagerContent;
import com.jit.taobaounion.presenter.ICategoryPagerPresenter;
import com.jit.taobaounion.presenter.ITicketPresenter;
import com.jit.taobaounion.presenter.impl.CategoryPagePresenterImpl;
import com.jit.taobaounion.presenter.impl.TicketPresenterImpl;
import com.jit.taobaounion.ui.activity.TicketActivity;
import com.jit.taobaounion.ui.adapter.HomePageContentAdapter;
import com.jit.taobaounion.ui.adapter.LooperPagerAdapter;
import com.jit.taobaounion.ui.custom.AutoLoopViewPager;
import com.jit.taobaounion.utils.Constants;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.PresenterManager;
import com.jit.taobaounion.utils.SizeUtils;
import com.jit.taobaounion.utils.TicketUtil;
import com.jit.taobaounion.utils.ToastUtil;
import com.jit.taobaounion.view.ICategoryPagerCallback;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.views.TbNestedScrollView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;

public class HomePagerFragment extends BaseFragment implements ICategoryPagerCallback, HomePageContentAdapter.OnListItemClickListener, LooperPagerAdapter.OnLooperPageItemClickListener {

    @BindView(R.id.home_pager_content_list)
    RecyclerView mContentList;

    @BindView(R.id.looper_pager)
    AutoLoopViewPager looperPager;

    @BindView(R.id.home_pager_title)
    TextView currentCategoryTitleTv;

    @BindView(R.id.looper_point_container)
    LinearLayout looperPointContainer;

    @BindView(R.id.home_pager_refresh)
    TwinklingRefreshLayout twinklingRefreshLayout;

    @BindView(R.id.home_pager_parent)
    LinearLayout homePagerParent;

    @BindView(R.id.home_pager_nested_scroller)
    TbNestedScrollView  homePagerNestedScroller;

    @BindView(R.id.home_pager_container)
    LinearLayout homePagerContainer;

    private ICategoryPagerPresenter mCategoryPagePresenter;
    private int mMaterialId;
    private HomePageContentAdapter mContentAdapter;
    private LooperPagerAdapter mLooperPagerAdapter;

    @NotNull
    public static HomePagerFragment newInstance(@NotNull Categories.DataBean category){
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_HOME_PAGER_TITLE,category.getTitle());
        bundle.putInt(Constants.KEY_HOME_PAGER_MATERIAL_ID,category.getId());
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void onResume() {
        super.onResume();
        //可见的时候我们去调用开始轮播
        looperPager.startLoop();
        looperPager.setDuration(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //不可见的时候我们去暂停轮播
        looperPager.stopLoop();
    }

    @Override
    protected void initListener() {
        mContentAdapter.setOnListItemClickListener(this);
        mLooperPagerAdapter.setOnLooperPageItemClickListener(this);
        homePagerParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (homePagerContainer == null){
                    return;
                }
                int headerHeight = homePagerContainer.getMeasuredHeight();
                //LogUtils.d(HomePagerFragment.this,"headerHeight---->"+headerHeight);
                homePagerNestedScroller.setHeaderHeight(headerHeight);
                int measuredHeight = homePagerParent.getMeasuredHeight();
                //LogUtils.d(HomePagerFragment.this,"measuredHeight----->"+measuredHeight);
                ViewGroup.LayoutParams layoutParams = mContentList.getLayoutParams();
                layoutParams.height = measuredHeight;
                mContentList.setLayoutParams(layoutParams);
                if (measuredHeight != 0){
                    homePagerParent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

        currentCategoryTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int measuredHeight = mContentList.getMeasuredHeight();
                LogUtils.d(HomePagerFragment.this,"measuredHeight------>"+measuredHeight);
            }
        });

        looperPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mLooperPagerAdapter.getDataSize() == 0){
                    return;
                }
                int targetPosition = position % mLooperPagerAdapter.getDataSize();
                //切换指示器
                updateLooperIndicator(targetPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        twinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                LogUtils.d(HomePagerFragment.this,"Loader More....");
                //去加载更多内容
                if (mCategoryPagePresenter != null) {
                    mCategoryPagePresenter.loaderMore(mMaterialId);
                }

            }
        });
    }

    /**
     * 切换指示器
     * @param targetPosition
     */
    private void updateLooperIndicator(int targetPosition) {
        for (int i = 0; i < looperPointContainer.getChildCount(); i++) {
            View point = looperPointContainer.getChildAt(i);
            if ( i == targetPosition ){
                point.setBackgroundResource(R.drawable.shape_indicator_point_selected);
            }else{
                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
            }
        }
    }

    @Override
    protected void initView(View rootView) {
        //设置布局管理器
        mContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        mContentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 8;
                outRect.bottom = 8;
            }
        });
        //创建适配器
        mContentAdapter = new HomePageContentAdapter();
        //设置适配器
        mContentList.setAdapter(mContentAdapter);

        //创建轮播图适配器
        mLooperPagerAdapter = new LooperPagerAdapter();
        //设置适配器
        looperPager.setAdapter(mLooperPagerAdapter);
        //设置Refresh相关属性
        twinklingRefreshLayout.setEnableRefresh(false);
        twinklingRefreshLayout.setEnableLoadmore(true);
        //twinklingRefreshLayout.setBottomView();
    }

    @Override
    protected void initPresenter() {
        mCategoryPagePresenter = PresenterManager.getInstance().getCategoryPagePresenter();
        mCategoryPagePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        String title = arguments.getString(Constants.KEY_HOME_PAGER_TITLE);
        mMaterialId = arguments.getInt(Constants.KEY_HOME_PAGER_MATERIAL_ID);
        LogUtils.e(this,"title---->" + title);
        LogUtils.e(this,"materialId--->" + mMaterialId);
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.getContentByCategoryId(mMaterialId);
        }
        if (currentCategoryTitleTv != null) {
            currentCategoryTitleTv.setText(title);
        }
    }

    @Override
    public void onContentLoaded(List<HomePagerContent.DataBean> contents) {
        //数据类表加载到了
        mContentAdapter.setData(contents);
        setUpstate(State.SUCCESS);
    }

    @Override
    public void onLoading() {
        setUpstate(State.LOADING);
    }

    @Override
    public int getCategoryID() {
        return mMaterialId;
    }

    @Override
    public void onError() {
        //网络错误
        setUpstate(State.ERROR);
    }

    @Override
    public void onEmpty() {
        setUpstate(State.EMPTY);
    }

    @Override
    public void onLoaderMoreError() {
        ToastUtil.showToast("网络异常，请稍后重试");
        if (twinklingRefreshLayout != null) {
            twinklingRefreshLayout.finishLoadmore();
        }
    }

    @Override
    public void onLoaderMoreEmpty() {
        ToastUtil.showToast("没有更多商品");
        if (twinklingRefreshLayout != null) {
            twinklingRefreshLayout.finishLoadmore();
        }
    }

    @Override
    public void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents) {
        //添加到适配器数据的底部
        mContentAdapter.addData(contents);
        if (twinklingRefreshLayout != null) {
            twinklingRefreshLayout.finishLoadmore();
        }
        ToastUtil.showToast("加载了"+contents.size()+"条数据");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onLooperListLoaded(@NotNull List<HomePagerContent.DataBean> contents) {
        LogUtils.d(this,"looper size ---->" + contents.size());
        mLooperPagerAdapter.setData(contents);
        int dx = (Integer.MAX_VALUE / 2) % contents.size();
        int targetCenterPosition = (Integer.MAX_VALUE / 2) - dx;
        //设置到中间点
        looperPager.setCurrentItem(targetCenterPosition);
        looperPointContainer.removeAllViews();
        //添加点
        for (int i = 0; i < contents.size(); i++) {
            View point = new View(getContext());
            int size = SizeUtils.dip2px(getContext(), 8);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size,size);
            layoutParams.leftMargin = SizeUtils.dip2px(getContext(),5);
            layoutParams.rightMargin = SizeUtils.dip2px(getContext(),5);
            point.setLayoutParams(layoutParams);
            if ( i == 0 ){
                point.setBackgroundResource(R.drawable.shape_indicator_point_selected);
            }else{
                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
            }
            looperPointContainer.addView(point);
        }
    }

    @Override
    protected void release() {
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onItemClick(@NotNull HomePagerContent.DataBean item) {
        //列表内容被点击了
        LogUtils.d(this,"item click---->" + item.getTitle());
        handleItemClick(item);
    }

    private void handleItemClick(@NotNull HomePagerContent.DataBean item) {
        TicketUtil.toTicketPage(getContext(),item);
    }

    @Override
    public void onLooperItemClick(@NotNull HomePagerContent.DataBean item) {
        //轮播图内容被点击了
        LogUtils.d(this,"item click---->" + item.getTitle());
        handleItemClick(item);
    }
}
