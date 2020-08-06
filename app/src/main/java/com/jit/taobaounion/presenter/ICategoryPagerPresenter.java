package com.jit.taobaounion.presenter;

import com.jit.taobaounion.base.IBasePresenter;
import com.jit.taobaounion.view.ICategoryPagerCallback;

public interface ICategoryPagerPresenter extends IBasePresenter<ICategoryPagerCallback> {
    /**
     * 获取分类内容
     * @param categoryId
     */
    void getContentByCategoryId(int categoryId);

    void loaderMore(int categoryId);

    void reload(int categoryId);

}
