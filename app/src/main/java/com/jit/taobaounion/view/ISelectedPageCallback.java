package com.jit.taobaounion.view;

import com.jit.taobaounion.base.IBaseCallback;
import com.jit.taobaounion.model.domain.SelectedContent;
import com.jit.taobaounion.model.domain.SelectedPageCategory;

public interface ISelectedPageCallback extends IBaseCallback {

    /**
     * 分类内容结果
     *
     * @param categories
     */
    void onCategoriesLoaded(SelectedPageCategory categories);

    /**
     * 内容
     *
     * @param content
     */
    void onContentLoaded(SelectedContent content);
}
