package com.jit.taobaounion.utils;

import com.jit.taobaounion.presenter.ICategoryPagerPresenter;
import com.jit.taobaounion.presenter.IHomePresenter;
import com.jit.taobaounion.presenter.IOnSellPagePresenter;
import com.jit.taobaounion.presenter.ISelectedPagePresenter;
import com.jit.taobaounion.presenter.ITicketPresenter;
import com.jit.taobaounion.presenter.impl.CategoryPagePresenterImpl;
import com.jit.taobaounion.presenter.impl.HomePresenterImpl;
import com.jit.taobaounion.presenter.impl.OnSellPagePresenterImpl;
import com.jit.taobaounion.presenter.impl.SelectedPagePresenterImpl;
import com.jit.taobaounion.presenter.impl.TicketPresenterImpl;

public class PresenterManager {
    private static final PresenterManager ourInstance = new PresenterManager();
    private final ICategoryPagerPresenter mCategoryPagePresenter;
    private final IHomePresenter mHomePresenter;
    private final ITicketPresenter mTicketPresenter;
    private final ISelectedPagePresenter mSelectedPagePresenter;
    private final IOnSellPagePresenter mOnSellPagePresenter;

    public static PresenterManager getInstance(){
        return ourInstance;
    }

    public ITicketPresenter getTicketPresenter() {
        return mTicketPresenter;
    }

    private PresenterManager(){
        mCategoryPagePresenter = new CategoryPagePresenterImpl();
        mHomePresenter = new HomePresenterImpl();
        mTicketPresenter = new TicketPresenterImpl();
        mSelectedPagePresenter = new SelectedPagePresenterImpl();
        mOnSellPagePresenter = new OnSellPagePresenterImpl();
    }

    public IOnSellPagePresenter getOnSellPagePresenter() {
        return mOnSellPagePresenter;
    }

    public ISelectedPagePresenter getSelectedPagePresenter() {
        return mSelectedPagePresenter;
    }

    public IHomePresenter getHomePresenter() {
        return mHomePresenter;
    }

    public ICategoryPagerPresenter getCategoryPagePresenter(){
        return mCategoryPagePresenter;
    }
}
