package com.jit.taobaounion.view;

import com.jit.taobaounion.model.domain.HomePagerContent;

import java.util.List;

public interface ICategoryPagerCallback {
    /**
     * 数据加载回来
     *
     * @param contents
     */
    void onContentLoaded(List<HomePagerContent.DataBean> contents,int categoryId);

    /**
     * 加载中
     *
     * @param categoryId
     */
    void onLoading(int categoryId);

    /**
     * 加载错误
     *
     * @param categoryId
     */
    void onError(int categoryId);

    /**
     * 加载为空
     *
     * @param categoryId
     */
    void onEmpty(int categoryId);

    /**
     * 加载更多网络错误
     *
     * @param categoryId
     */
    void onLoaderMoreError(int categoryId);

    /**
     * 没有更多内容
     *
     * @param categoryId
     */
    void onLoaderMoreEmpty(int categoryId);

    /**
     * 加载了更多内容
     *
     * @param contents
     */
    void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents,int categoryId);

    /**
     * 轮播图
     * @param contents
     */
    void onLooperListLoaded(List<HomePagerContent.DataBean> contents,int categoryId);
}
