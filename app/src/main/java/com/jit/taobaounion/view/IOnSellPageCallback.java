package com.jit.taobaounion.view;

import com.jit.taobaounion.base.IBaseCallback;
import com.jit.taobaounion.model.domain.OnSellContent;

public interface IOnSellPageCallback extends IBaseCallback {

    /**
     * 特惠内容加载
     *
     * @param result
     */
    void onContentLoadedSuccess(OnSellContent result);

    void onMoreLoaded(OnSellContent moreResult);

    /**
     * 加载更多失败
     */
    void onMoreLoadedError();

    /**
     * 没有更多内容
     */
    void onMoreLoadedEmpty();
}
