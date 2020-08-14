package com.jit.taobaounion.presenter;

import com.jit.taobaounion.base.IBasePresenter;
import com.jit.taobaounion.view.IOnSellPageCallback;

public interface IOnSellPagePresenter extends IBasePresenter<IOnSellPageCallback> {

    /**
     * 加载特惠内容
     */
    void getOnSellContent();

    /**
     * 重新加载内容
     */
    void reload();

    /**
     * 加载更多
     */
    void loaderMore();
}
