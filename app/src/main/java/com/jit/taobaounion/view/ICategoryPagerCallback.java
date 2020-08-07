package com.jit.taobaounion.view;

import com.jit.taobaounion.base.IBaseCallback;
import com.jit.taobaounion.model.domain.HomePagerContent;

import java.util.List;

public interface ICategoryPagerCallback extends IBaseCallback {
    /**
     * 数据加载回来
     *
     * @param contents
     */
    void onContentLoaded(List<HomePagerContent.DataBean> contents);


    int getCategoryID();


    /**
     * 加载更多网络错误
     *
     */
    void onLoaderMoreError();

    /**
     * 没有更多内容
     *
     */
    void onLoaderMoreEmpty();

    /**
     * 加载了更多内容
     *
     * @param contents
     */
    void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents);

    /**
     * 轮播图
     * @param contents
     */
    void onLooperListLoaded(List<HomePagerContent.DataBean> contents);
}
