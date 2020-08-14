package com.jit.taobaounion.presenter;

import com.jit.taobaounion.base.IBasePresenter;
import com.jit.taobaounion.view.ISearchPageCallback;

public interface ISearchPresenter extends IBasePresenter<ISearchPageCallback> {
    /**
     * 获取搜索历史
     */
    void getHistories();

    /**
     * 删除搜索历史
     */
    void deleteHistories();

    /**
     * 搜索
     *
     * @param keyword
     */
    void doSearch(String keyword);

    /**
     * 重新搜索
     */
    void research();

    /**
     * 获取更多的搜索结果
     */
    void loaderMore();

    /**
     * 获取推荐词
     */
    void getRecommendWords();
}
