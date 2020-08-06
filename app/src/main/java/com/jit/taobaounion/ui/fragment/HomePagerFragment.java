package com.jit.taobaounion.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseFragment;
import com.jit.taobaounion.model.domain.Categories;
import com.jit.taobaounion.model.domain.HomePagerContent;
import com.jit.taobaounion.presenter.ICategoryPagerPresenter;
import com.jit.taobaounion.presenter.impl.CategoryPagePresenterImpl;
import com.jit.taobaounion.utils.Constants;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.view.ICategoryPagerCallback;

import java.util.List;

public class HomePagerFragment extends BaseFragment implements ICategoryPagerCallback {

    private ICategoryPagerPresenter mCategoryPagePresenter;
    private int mMaterialId;

    public static HomePagerFragment newInstance(Categories.DataBean category){
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
    protected void initView(View rootView) {
        setUpstate(State.SUCCESS);
    }

    @Override
    protected void initPresenter() {
        mCategoryPagePresenter = CategoryPagePresenterImpl.getInstance();
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
    }

    @Override
    public void onContentLoaded(List<HomePagerContent.DataBean> contents,int categoryId) {
        if (mMaterialId!=categoryId) {
            return;
        }
        //数据类表加载到了
        //TODO:更新UI
        setUpstate(State.SUCCESS);
    }

    @Override
    public void onLoading(int categoryId) {
        if (mMaterialId!=categoryId) {
            return;
        }
        setUpstate(State.LOADING);
    }

    @Override
    public void onError(int categoryId) {
        if (mMaterialId!=categoryId) {
            return;
        }
        //网络错误
        setUpstate(State.ERROR);
    }

    @Override
    public void onEmpty(int categoryId) {
        if (mMaterialId!=categoryId) {
            return;
        }
        setUpstate(State.EMPTY);
    }

    @Override
    public void onLoaderMoreError(int categoryId) {

    }

    @Override
    public void onLoaderMoreEmpty(int categoryId) {

    }

    @Override
    public void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents , int categoryId) {

    }

    @Override
    public void onLooperListLoaded(List<HomePagerContent.DataBean> contents, int categoryId) {

    }

    @Override
    protected void release() {
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.unregisterViewCallback(this);
        }
    }
}
