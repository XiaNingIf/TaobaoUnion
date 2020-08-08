package com.jit.taobaounion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseFragment;
import com.jit.taobaounion.model.domain.Categories;
import com.jit.taobaounion.presenter.IHomePresenter;
import com.jit.taobaounion.presenter.impl.HomePresenterImpl;
import com.jit.taobaounion.ui.adapter.HomePagerAdapter;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.PresenterManager;
import com.jit.taobaounion.view.IHomeCallback;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    @BindView(R.id.home_indicator)
    TabLayout mTabLayout;

    private IHomePresenter mHomePresenter;

    @BindView(R.id.home_pager)
    ViewPager homePager;
    private HomePagerAdapter mHomePagerAdapter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.e(this,"onCreateView....");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.e(this,"onDestroyView....");
    }

    @Override
    protected void initView(View rootView) {
        mTabLayout.setupWithViewPager(homePager);
        //给ViewPager设置适配器
        mHomePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        homePager.setAdapter(mHomePagerAdapter);
    }

    @Override
    protected void initPresenter() {
        //创建presenter
        mHomePresenter = PresenterManager.getInstance().getHomePresenter();
        mHomePresenter.registerViewCallback(this);
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_home_fragment_layout,container,false);
    }

    @Override
    protected void loadData() {
        //加载数据
        mHomePresenter.getCategories();
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        setUpstate(State.SUCCESS);
        LogUtils.e(this,"onCategoriesLoaded.....");
        //加载的数据就会从这里回来
        if (mHomePagerAdapter != null) {
            //homePager.setOffscreenPageLimit(categories.getData().size());
            mHomePagerAdapter.setCategories(categories);
        }
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
    protected void release() {
        //取消数据
        if (mHomePresenter!=null) {
            mHomePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected void onRetryClick() {
        //网络错误点击重试
        //重新加载分类内容
        if (mHomePresenter != null) {
            mHomePresenter.getCategories();
        }
    }
}
