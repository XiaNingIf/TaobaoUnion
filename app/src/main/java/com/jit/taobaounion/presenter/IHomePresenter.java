package com.jit.taobaounion.presenter;

import com.jit.taobaounion.base.IBasePresenter;
import com.jit.taobaounion.view.IHomeCallback;


public interface IHomePresenter extends IBasePresenter<IHomeCallback> {
    /**
     * 获取商品分类
     */
    void getCategories();


}
