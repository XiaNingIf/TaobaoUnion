package com.jit.taobaounion.presenter;

import com.jit.taobaounion.base.IBasePresenter;
import com.jit.taobaounion.model.domain.SelectedPageCategory;
import com.jit.taobaounion.view.ISelectedPageCallback;

public interface ISelectedPagePresenter extends IBasePresenter<ISelectedPageCallback> {
    /**
     * 获取分类
     */
    void getCategories();


    /**
     * 根据分类获取分类内容
     * @param item
     */
    void getContentByCategory(SelectedPageCategory.DataBean item);

    /**
     * 重新加载内容
     */
    void reloadContent();
}
